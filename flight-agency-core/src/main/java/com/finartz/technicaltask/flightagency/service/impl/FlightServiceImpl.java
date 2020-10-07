package com.finartz.technicaltask.flightagency.service.impl;

import com.finartz.technicaltask.flightagency.config.ApplicationProperties;
import com.finartz.technicaltask.flightagency.controller.request.FlightRequest;
import com.finartz.technicaltask.flightagency.controller.response.FlightResponse;
import com.finartz.technicaltask.flightagency.entity.Flight;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.repository.FlightRepository;
import com.finartz.technicaltask.flightagency.service.FlightService;
import com.finartz.technicaltask.flightagency.service.RouteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private static final int NUMBER_CONVERTER_FOR_NEXT_TICKET = 1;
    private static final BigDecimal BIGDECIMAL_HUNDRED = BigDecimal.valueOf(100.00);

    private final FlightRepository repository;
    private final RouteService routeService;
    private final ApplicationProperties properties;
    private final ModelMapper mapper;

    public FlightResponse createFlight(FlightRequest request) {
        log.info("createFlight-begin {}", kv("request", request));

        Flight flight = mapper.map(request, Flight.class);
        flight.setRoute(routeService.getRouteEntity(request.getRouteId()));
        flight.setCurrentPrice(request.getUnitPrice());
        flight.setCurrentPercentileOfFlightCapacity(properties.getCapacityPercentileIncrement());
        flight = repository.save(flight);

        FlightResponse response = mapper.map(flight, FlightResponse.class);

        log.info("createFlight-end {}", kv("response", request));
        return response;
    }

    public FlightResponse reassignFlight(Long id, Long routeId) {
        log.info("reassignFlight-begin {}", kv("routeId", routeId));

        Flight entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        entity.setRoute(routeService.getRouteEntity(routeId));

        FlightResponse response = mapper.map(repository.save(entity), FlightResponse.class);

        log.info("reassignFlight-end {}", kv("response", response));
        return response;
    }

    public FlightResponse updateFlight(Long id, FlightRequest request) {
        log.info("updateFlight-begin {}", kv("request", request));

        Flight entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        entity.setRoute(routeService.getRouteEntity(request.getRouteId()));
        entity.setArrivalDate(request.getArrivalDate());
        entity.setDepartureDate(request.getDepartureDate());
        entity.setCapacity(request.getCapacity());
        entity.setUnitPrice(request.getUnitPrice());

        FlightResponse response = mapper.map(repository.save(entity), FlightResponse.class);

        log.info("updateFlight-end {}", kv("response", response));
        return response;
    }

    public void deleteFlight(Long id) {
        log.info("deleteFlight-begin {}", kv("id", id));

        repository.deleteById(id);

        log.info("deleteFlight-end {}", kv("id", id));
    }

    public Page<FlightResponse> getAll(int pageIndex, int pageSize) {
        log.info("getAll-begin {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));

        Page<FlightResponse> response = repository.findAll(PageRequest.of(pageIndex, pageSize))
                .map(flight -> mapper.map(flight, FlightResponse.class));

        log.info("getAll-end {}", kv("response", response));
        return response;
    }

    public FlightResponse getFlight(Long id) {
        log.info("getFlight-begin {}", kv("id", id));

        FlightResponse response = repository.findById(id)
                .map(flight -> mapper.map(flight, FlightResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getFlight-end {}", kv("response", response));
        return response;
    }

    public List<FlightResponse> getFlightsByDepartureAirport(Long departureAirportId) {
        log.info("getFlightsByDepartureAirport-begin {}", kv("departureAirportId", departureAirportId));

        List<FlightResponse> list = repository.findByDepartureAirportId(departureAirportId).stream()
                .map(flight -> mapper.map(flight, FlightResponse.class))
                .collect(Collectors.toList());

        log.info("getFlightsByDepartureAirport-end {}", kv("list", list));
        return list;
    }

    public List<FlightResponse> getFlightsByArrivalAirport(Long arrivalAirportId) {
        log.info("getFlightsByArrivalAirport-begin {}", kv("arrivalAirportId", arrivalAirportId));

        List<FlightResponse> list = repository.findByArrivalAirportId(arrivalAirportId).stream()
                .map(flight -> mapper.map(flight, FlightResponse.class))
                .collect(Collectors.toList());

        log.info("getFlightsByArrivalAirport-end {}", kv("list", list));
        return list;
    }

    public Flight getFlightEntity(Long id) {
        log.info("getFlightEntity-begin {}", kv("id", id));

        Flight response = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getFlightEntity-end {}", kv("response", response));
        return response;
    }

    public BigDecimal getCalculatedPriceForFlight(Long flightId, Long activeTicketCountOfFlight) {
        log.info("getCalculatedPriceForFlight-begin {}", kv("flightId", flightId));

        Flight flight = getFlightEntity(flightId);
        long capacity = flight.getCapacity();
        BigDecimal currentPrice = flight.getCurrentPrice();

        if (capacity == activeTicketCountOfFlight) {
            throw new FlightAgencyCoreException(FlightAgencyCoreError.CAPACITY_FULL, capacity);
        }

        BigDecimal result = determinePrice(flight, capacity, currentPrice, activeTicketCountOfFlight);

        log.info("getCalculatedPriceForFlight-end {}", kv("result", result));
        return result;
    }

    private BigDecimal determinePrice(Flight flight, long capacity, BigDecimal currentPrice,
                                      Long activeTicketCountOfFlight) {
        long nextTicketNumber = activeTicketCountOfFlight + NUMBER_CONVERTER_FOR_NEXT_TICKET;
        BigDecimal currentPercentileOfFlightCapacity = flight.getCurrentPercentileOfFlightCapacity();

        return nextTicketNumber > lastNumberOfPercentile(currentPercentileOfFlightCapacity, capacity) ?
                increasePrice(flight, currentPrice, currentPercentileOfFlightCapacity) : currentPrice;
    }

    private BigDecimal increasePrice(Flight flight, BigDecimal currentPrice,
                                     BigDecimal currentPercentileOfFlightCapacity) {
        int scale = properties.getScale();
        BigDecimal result = currentPrice.add(currentPrice.multiply(
                properties.getPricePercentage().divide(BIGDECIMAL_HUNDRED, scale, RoundingMode.UP)))
                .setScale(scale, RoundingMode.UP);

        flight.setCurrentPrice(result);
        flight.setCurrentPercentileOfFlightCapacity(currentPercentileOfFlightCapacity.add(
                properties.getCapacityPercentileIncrement()));
        return result;
    }

    private long lastNumberOfPercentile(BigDecimal percentile, long capacity) {

        int scale = properties.getScale();
        return percentile.divide(BIGDECIMAL_HUNDRED, scale, RoundingMode.UP)
                .multiply(BigDecimal.valueOf(capacity))
                .setScale(scale, RoundingMode.UP).longValue();
    }

}