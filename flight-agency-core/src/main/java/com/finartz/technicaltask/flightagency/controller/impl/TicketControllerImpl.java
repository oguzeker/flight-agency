package com.finartz.technicaltask.flightagency.controller.impl;

import com.finartz.technicaltask.flightagency.controller.TicketController;
import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import com.finartz.technicaltask.flightagency.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("ticket")
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<TicketResponse> cancelTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.cancelTicketById(id));
    }

    @PutMapping("pnr/{pnr}")
    public ResponseEntity<TicketResponse> cancelTicketByPnr(@PathVariable String pnr) {
        return ResponseEntity.ok(ticketService.cancelTicketByPnr(pnr));
    }

    @GetMapping
    public ResponseEntity<Page<TicketResponse>> getAllTickets(@RequestParam int pageIndex,
                                                              @RequestParam int pageSize) {
        return ResponseEntity.ok(ticketService.getAll(pageIndex, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicket(id));
    }

    @GetMapping("pnr/{pnr}")
    public ResponseEntity<TicketResponse> getTicketByPnr(@PathVariable String pnr) {
        return ResponseEntity.ok(ticketService.getTicketByPnr(pnr));
    }
    
}
