package com.finartz.technicaltask.flightagency.service.impl;

import com.finartz.technicaltask.flightagency.controller.request.AirportRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirportResponse;
import com.finartz.technicaltask.flightagency.entity.Airport;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.repository.AirportRepository;
import com.finartz.technicaltask.flightagency.service.AirportService;
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
public class AirportServiceImpl implements AirportService {

    private final AirportRepository repository;
    private final ModelMapper mapper;

    public AirportResponse createAirport(AirportRequest request) {
        log.info("createAirport-begin {}", kv("request", request));

        Airport airport = repository.save(mapper.map(request, Airport.class));
        AirportResponse response = mapper.map(airport, AirportResponse.class);

        log.info("createAirport-end {}", kv("response", request));
        return response;
    }

    public AirportResponse updateAirport(Long id, AirportRequest request) {
        log.info("updateAirport-begin {}", kv("request", request));

        Airport entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        entity.setName(request.getName());
        entity.setShortCode(request.getShortCode());
        entity.setCity(request.getCity());
        AirportResponse response = mapper.map(repository.save(entity), AirportResponse.class);

        log.info("updateAirport-end {}", kv("response", response));
        return response;
    }

    public void deleteAirport(Long id) {
        log.info("deleteAirport-begin {}", kv("id", id));

        repository.deleteById(id);

        log.info("deleteAirport-end {}", kv("id", id));
    }

    public Page<AirportResponse> getAll(int pageIndex, int pageSize) {
        log.info("getAll-begin {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));

        Page<AirportResponse> response = repository.findAll(PageRequest.of(pageIndex, pageSize))
                .map(airport -> mapper.map(airport, AirportResponse.class));

        log.info("getAll-end {}", kv("response", response));
        return response;
    }

    public AirportResponse getAirport(Long id) {
        log.info("getAirport-begin {}", kv("id", id));

        AirportResponse response = repository.findById(id)
                .map(airline -> mapper.map(airline, AirportResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getAirport-end {}", kv("response", response));
        return response;
    }

    public Airport getAirportEntity(Long id) {
        log.info("getAirportEntity-begin {}", kv("id", id));

        Airport response = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getAirportEntity-end {}", kv("response", response));
        return response;
    }

}
