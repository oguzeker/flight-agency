package com.finartz.technicaltask.flightagency.service;

import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import com.finartz.technicaltask.flightagency.entity.Ticket;
import org.springframework.data.domain.Page;

public interface TicketService {

    TicketResponse createTicket(TicketRequest request);

    TicketResponse cancelTicketById(Long id);

    TicketResponse cancelTicketByPnr(String pnr);

    Page<TicketResponse> getAll(int pageIndex, int pageSize);

    TicketResponse getTicket(Long id);

    Ticket getTicketEntity(Long id);

    TicketResponse getTicketByPnr(String pnr);

    Ticket getTicketEntityByPnr(String pnr);

    Long getActiveTicketCountOfFlight(Long flightId);

}
