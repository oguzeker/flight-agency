package com.finartz.technicaltask.flightagency.service.impl;

import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import com.finartz.technicaltask.flightagency.entity.Ticket;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreError;
import com.finartz.technicaltask.flightagency.exception.FlightAgencyCoreException;
import com.finartz.technicaltask.flightagency.repository.TicketRepository;
import com.finartz.technicaltask.flightagency.service.FlightService;
import com.finartz.technicaltask.flightagency.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

    private static final String CHAR_DASH = "-";
    private static final int PNR_MAX_WIDTH = 5;

    private final TicketRepository repository;
    private final FlightService flightService;
    private final ModelMapper mapper;

    public TicketResponse createTicket(TicketRequest request) {
        log.info("createTicket-begin {}", kv("request", request));
        Long flightId = request.getFlightId();
        BigDecimal cost = flightService.getCalculatedPriceForFlight(flightId, getActiveTicketCountOfFlight(flightId));

        Ticket ticket = mapper.map(request, Ticket.class);
        ticket.setFlight(flightService.getFlightEntity(flightId));
        ticket.setPnr(generatePnr());
        ticket.setCost(cost);
        ticket.setActive(Boolean.TRUE);

        ticket = repository.save(ticket);

        TicketResponse response = mapper.map(ticket, TicketResponse.class);

        log.info("createTicket-end {}", kv("response", request));
        return response;
    }

    public TicketResponse cancelTicketById(Long id) {
        log.info("cancelTicketById-begin {}", kv("id", id));

        Ticket entity = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));
        entity.setActive(false);

        TicketResponse response = mapper.map(repository.save(entity), TicketResponse.class);

        log.info("cancelTicketById-end {}", kv("response", response));
        return response;
    }

    public TicketResponse cancelTicketByPnr(String pnr) {
        log.info("cancelTicketById-begin {}", kv("pnr", pnr));

        Ticket entity = getTicketEntityByPnr(pnr);
        entity.setActive(false);

        TicketResponse response = mapper.map(repository.save(entity), TicketResponse.class);

        log.info("cancelTicketById-end {}", kv("response", response));
        return response;
    }

    public Page<TicketResponse> getAll(int pageIndex, int pageSize) {
        log.info("getAll-begin {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));

        Page<TicketResponse> response = repository.findAll(PageRequest.of(pageIndex, pageSize))
                .map(airport -> mapper.map(airport, TicketResponse.class));

        log.info("getAll-end {}", kv("response", response));
        return response;
    }

    public TicketResponse getTicket(Long id) {
        log.info("getTicket-begin {}", kv("id", id));

        TicketResponse response = repository.findById(id)
                .map(airline -> mapper.map(airline, TicketResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getTicket-end {}", kv("response", response));
        return response;
    }

    public Ticket getTicketEntity(Long id) {
        log.info("getTicketEntity-begin {}", kv("id", id));

        Ticket response = repository.findById(id)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, id));

        log.info("getTicketEntity-end {}", kv("response", response));
        return response;
    }

    public TicketResponse getTicketByPnr(String pnr) {
        log.info("getTicketByPnr-begin {}", kv("pnr", pnr));

        TicketResponse response = repository.findByPnr(pnr)
                .map(airline -> mapper.map(airline, TicketResponse.class))
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, pnr));

        log.info("getTicketByPnr-end {}", kv("response", response));
        return response;
    }

    public Ticket getTicketEntityByPnr(String pnr) {
        log.info("getTicketEntityByPnr-begin {}", kv("pnr", pnr));

        Ticket response = repository.findByPnr(pnr)
                .orElseThrow(() -> new FlightAgencyCoreException(FlightAgencyCoreError.ENTITY_NOT_FOUND_VALUE, pnr));

        log.info("getTicketEntityByPnr-end {}", kv("response", response));
        return response;
    }

    public Long getActiveTicketCountOfFlight(Long flightId) {
        log.info("getActiveTicketCountOfFlight-begin {}", kv("flightId", flightId));

        Long activeTicketCountByFlightId = repository.countByFlightIdAndActive(flightId, Boolean.TRUE);

        log.info("getActiveTicketCountOfFlight-end {}", kv("activeTicketCountByFlightId", activeTicketCountByFlightId));
        return activeTicketCountByFlightId;
    }

    private static String generatePnr() {
        return StringUtils.truncate(UUID.randomUUID().toString().toUpperCase().replace(CHAR_DASH, StringUtils.EMPTY),
                PNR_MAX_WIDTH);
    }

}
