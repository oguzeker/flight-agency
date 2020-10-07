package com.finartz.technicaltask.flightagency.controller.impl;

import com.finartz.technicaltask.flightagency.controller.RouteController;
import com.finartz.technicaltask.flightagency.controller.request.RouteRequest;
import com.finartz.technicaltask.flightagency.controller.response.RouteResponse;
import com.finartz.technicaltask.flightagency.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("route")
public class RouteControllerImpl implements RouteController {
    
    private final RouteService routeService;

    @PostMapping
    public ResponseEntity<RouteResponse> saveRoute(@RequestBody RouteRequest routeReqDto) {
        return ResponseEntity.ok(routeService.createRoute(routeReqDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<RouteResponse> updateRoute(@PathVariable Long id,
                                                     @RequestBody RouteRequest routeReqDto) {
        return ResponseEntity.ok(routeService.updateRoute(id, routeReqDto));
    }

    @DeleteMapping("{id}")
    @SuppressWarnings("rawtypes")
    public ResponseEntity<String> deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<RouteResponse>> getAllRoutes(@RequestParam int pageIndex,
                                                            @RequestParam int pageSize) {
        return ResponseEntity.ok(routeService.getAll(pageIndex, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<RouteResponse> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getRoute(id));
    }

}
