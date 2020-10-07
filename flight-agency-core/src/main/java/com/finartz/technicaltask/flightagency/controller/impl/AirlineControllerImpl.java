package com.finartz.technicaltask.flightagency.controller.impl;

import com.finartz.technicaltask.flightagency.controller.AirlineController;
import com.finartz.technicaltask.flightagency.controller.request.AirlineRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirlineResponse;
import com.finartz.technicaltask.flightagency.service.AirlineService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("airline")
public class AirlineControllerImpl implements AirlineController {

    private final AirlineService service;

    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(@RequestBody AirlineRequest request) {
        return ResponseEntity.ok(service.createAirline(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<AirlineResponse> updateAirline(@PathVariable Long id,
                                                         @RequestBody AirlineRequest request) {
        return ResponseEntity.ok(service.updateAirline(id, request));
    }

    @DeleteMapping("{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity deleteAirline(@PathVariable Long id) {
        service.deleteAirline(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(@RequestParam int pageIndex,
                                                                @RequestParam int pageSize) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<AirlineResponse> getAirlineById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAirline(id));
    }

    @GetMapping("short-code/{short-code}")
    public ResponseEntity<AirlineResponse> getAirlineByShortCode(@PathVariable(value = "short-code") String shortCode) {
        return ResponseEntity.ok(service.getAirlineByShortCode(shortCode));
    }

}
