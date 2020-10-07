package com.finartz.technicaltask.flightagency.controller;

import com.finartz.technicaltask.flightagency.config.SwaggerConfig;
import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = {
        SwaggerConfig.TAG_TICKET
})
public interface TicketController {

    @ApiOperation(value = "Create Ticket", notes = "This endpoint creates ticket entity")
    ResponseEntity<TicketResponse> createTicket(
            @ApiParam(value = "Ticket entity to create") @RequestBody TicketRequest request);

    @ApiOperation(value = "Cancel Ticket By Id", notes = "This endpoint cancels ticket entity by id")
    ResponseEntity<TicketResponse> cancelTicketById(
            @ApiParam(value = "Id of the ticket entity to update") @PathVariable Long id);

    @ApiOperation(value = "Cancel Ticket By Pnr", notes = "This endpoint cancels ticket entityby pnr")
    ResponseEntity<TicketResponse> cancelTicketByPnr(
            @ApiParam(value = "Pnr of the ticket entity to update") @PathVariable String pnr);

    @ApiOperation(value = "Get All Tickets", notes = "This endpoint fetches ticket entities (with Pagination)")
    ResponseEntity<Page<TicketResponse>> getAllTickets(
            @ApiParam(value = "Index of the requested page of bulk results", example = "0") @RequestParam int pageIndex,
            @ApiParam(value = "Size of the requested page of bulk results", example = "10") @RequestParam int pageSize);

    @ApiOperation(value = "Get Ticket By Id", notes = "This endpoint fetches ticket entity with the given id")
    ResponseEntity<TicketResponse> getTicketById(
            @ApiParam(value = "Id of the ticket entity to fetch") @PathVariable Long id);

    @ApiOperation(value = "Get Ticket By Pnr", notes = "This endpoint fetches ticket entity with the given pnr")
    ResponseEntity<TicketResponse> getTicketByPnr(
            @ApiParam(value = "Pnr of the ticket entity to fetch") @PathVariable String pnr);
    
}
