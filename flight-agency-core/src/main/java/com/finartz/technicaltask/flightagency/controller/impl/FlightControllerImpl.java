package com.finartz.technicaltask.flightagency.controller.impl;

import com.finartz.technicaltask.flightagency.controller.FlightController;
import com.finartz.technicaltask.flightagency.controller.request.FlightRequest;
import com.finartz.technicaltask.flightagency.controller.response.FlightResponse;
import com.finartz.technicaltask.flightagency.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("flight")
public class FlightControllerImpl implements FlightController {

    private final FlightService service;

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest request) {
        return ResponseEntity.ok(service.createFlight(request));
    }

    @PutMapping("{id}/route/{route-id}")
    public ResponseEntity<FlightResponse> reassignFlight(@PathVariable Long id,
                                                         @PathVariable(value = "route-id") Long routeId) {
        return ResponseEntity.ok(service.reassignFlight(id, routeId));
    }

    @PutMapping("{id}")
    public ResponseEntity<FlightResponse> updateFlight(@PathVariable Long id,
                                                       @RequestBody FlightRequest request) {
        return ResponseEntity.ok(service.updateFlight(id, request));
    }

    @DeleteMapping("{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity deleteFlight(@PathVariable Long id) {
        service.deleteFlight(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<FlightResponse>> getAllFlights(@RequestParam int pageIndex,
                                                              @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getFlight(id));
    }

    @GetMapping("departure-airport/{departure-airport-id}")
    public ResponseEntity<List<FlightResponse>> getFlightsByDepartureAirport(
            @PathVariable(value = "departure-airport-id") Long departureAirportId) {
        return ResponseEntity.ok(service.getFlightsByDepartureAirport(departureAirportId));
    }

    @GetMapping("arrival-airport/{arrival-airport-id}")
    public ResponseEntity<List<FlightResponse>> getFlightsByArrivalAirport(
            @PathVariable(value = "arrival-airport-id") Long arrivalAirportId) {
        return ResponseEntity.ok(service.getFlightsByArrivalAirport(arrivalAirportId));
    }

}
