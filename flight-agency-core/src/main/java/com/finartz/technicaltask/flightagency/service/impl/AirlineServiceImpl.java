package com.finartz.technicaltask.flightagency.service.impl;

import com.finartz.technicaltask.flightagency.controller.request.AirlineRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirlineResponse;
import com.finartz.technicaltask.flightagency.entity.Airline;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.repository.AirlineRepository;
import com.finartz.technicaltask.flightagency.service.AirlineService;
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
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository repository;
    private final ModelMapper mapper;

    public AirlineResponse createAirline(AirlineRequest request) {
        log.info("createAirline-begin {}", kv("request", request));

        Airline airline = repository.save(mapper.map(request, Airline.class));
        AirlineResponse response = mapper.map(airline, AirlineResponse.class);

        log.info("createAirline-end {}", kv("response", request));
        return response;
    }

    public AirlineResponse updateAirline(Long id, AirlineRequest request) {
        log.info("updateAirline-begin {}", kv("request", request));

        Airline entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        entity.setName(request.getName());
        entity.setShortCode(request.getShortCode());
        AirlineResponse response = mapper.map(repository.save(entity), AirlineResponse.class);

        log.info("updateAirline-end {}", kv("response", response));
        return response;
    }

    public void deleteAirline(Long id) {
        log.info("deleteAirline-begin {}", kv("id", id));
        
        repository.deleteById(id);
        
        log.info("deleteAirline-end {}", kv("id", id));
    }

    public Page<AirlineResponse> getAll(int pageIndex, int pageSize) {
        log.info("getAll-begin {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));

        Page<AirlineResponse> response = repository.findAll(PageRequest.of(pageIndex, pageSize))
                .map(airline -> mapper.map(airline, AirlineResponse.class));
                
        log.info("getAll-end {}", kv("response", response));
        return response;
    }

    public AirlineResponse getAirline(Long id) {
        log.info("getAirline-begin {}", kv("id", id));

        AirlineResponse response = repository.findById(id)
                .map(airline -> mapper.map(airline, AirlineResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getAirline-end {}", kv("response", response));
        return response;
    }

    public Airline getAirlineEntity(Long id) {
        log.info("getAirlineEntity-begin {}", kv("id", id));

        Airline entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getAirlineEntity-end {}", kv("entity", entity));
        return entity;
    }
    
    public AirlineResponse getAirlineByShortCode(String shortCode) {
        log.info("getAirlineByShortCode-begin {}", kv("shortCode", shortCode));

        AirlineResponse response = repository.findByShortCode(shortCode)
                .map(airline -> mapper.map(airline, AirlineResponse.class))
                .orElseThrow(
                        () -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, shortCode));

        log.info("getAirlineByShortCode-end {}", kv("response", response));
        return response;
    }

}
