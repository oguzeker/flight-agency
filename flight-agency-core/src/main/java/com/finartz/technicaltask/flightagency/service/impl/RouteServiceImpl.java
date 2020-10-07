package com.finartz.technicaltask.flightagency.service.impl;

import com.finartz.technicaltask.flightagency.controller.request.RouteRequest;
import com.finartz.technicaltask.flightagency.controller.response.RouteResponse;
import com.finartz.technicaltask.flightagency.entity.Route;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.repository.RouteRepository;
import com.finartz.technicaltask.flightagency.service.AirportService;
import com.finartz.technicaltask.flightagency.service.RouteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private static final String CHAR_DASH = "-";
    private static final int PNR_MAX_WIDTH = 5;

    private final RouteRepository repository;
    private final AirportService airportService;
    private final ModelMapper mapper;

    public RouteResponse createRoute(RouteRequest request) {
        log.info("createRoute-begin {}", kv("request", request));

        Route route = mapper.map(request, Route.class);
        route.setShortCode(request.getShortCode());
        route.setFromAirport(airportService.getAirportEntity(request.getFromAirportId()));
        route.setToAirport(airportService.getAirportEntity(request.getToAirportId()));

        route = repository.save(route);

        RouteResponse response = mapper.map(route, RouteResponse.class);

        log.info("createRoute-end {}", kv("response", request));
        return response;
    }

    public RouteResponse updateRoute(Long id, RouteRequest request) {
        log.info("updateRoute-begin {}", kv("request", request));

        Route entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        entity.setShortCode(request.getShortCode());
        entity.setFromAirport(airportService.getAirportEntity(request.getFromAirportId()));
        entity.setToAirport(airportService.getAirportEntity(request.getToAirportId()));

        RouteResponse response = mapper.map(repository.save(entity), RouteResponse.class);

        log.info("updateRoute-end {}", kv("response", response));
        return response;
    }

    public void deleteRoute(Long id) {
        log.info("deleteRoute-begin {}", kv("id", id));

        repository.deleteById(id);

        log.info("deleteRoute-end {}", kv("id", id));
    }

    public Page<RouteResponse> getAll(int pageIndex, int pageSize) {
        log.info("getAll-begin {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));

        Page<RouteResponse> response = repository.findAll(PageRequest.of(pageIndex, pageSize))
                .map(airport -> mapper.map(airport, RouteResponse.class));

        log.info("getAll-end {}", kv("response", response));
        return response;
    }

    public RouteResponse getRoute(Long id) {
        log.info("getRoute-begin {}", kv("id", id));

        RouteResponse response = repository.findById(id)
                .map(airline -> mapper.map(airline, RouteResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getRoute-end {}", kv("response", response));
        return response;
    }

    public Route getRouteEntity(Long id) {
        log.info("getRouteEntity-begin {}", kv("id", id));

        Route response = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getRouteEntity-end {}", kv("response", response));
        return response;
    }

    public RouteResponse getRouteByShortCode(String shortCode) {
        log.info("getRouteByShortCode-begin {}", kv("shortCode", shortCode));

        RouteResponse response = repository.findByShortCode(shortCode)
                .map(route -> mapper.map(route, RouteResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, shortCode));

        log.info("getRouteByShortCode-end {}", kv("response", response));
        return response;
    }

}
