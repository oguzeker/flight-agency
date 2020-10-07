package com.finartz.technicaltask.flightagency.controller.impl;

import com.finartz.technicaltask.flightagency.controller.AirportController;
import com.finartz.technicaltask.flightagency.controller.request.AirportRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirportResponse;
import com.finartz.technicaltask.flightagency.service.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("airport")
public class AirportControllerImpl implements AirportController {

    private final AirportService service;

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@RequestBody AirportRequest request) {
        return ResponseEntity.ok(service.createAirport(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id,
                                                         @RequestBody AirportRequest request) {
        return ResponseEntity.ok(service.updateAirport(id, request));
    }

    @DeleteMapping("{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity deleteAirport(@PathVariable Long id) {
        service.deleteAirport(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<AirportResponse>> getAllAirports(@RequestParam int pageIndex,
                                                                @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAirport(id));
    }

}
