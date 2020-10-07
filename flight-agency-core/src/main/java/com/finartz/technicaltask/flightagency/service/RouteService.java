package com.finartz.technicaltask.flightagency.service;

import com.finartz.technicaltask.flightagency.controller.request.RouteRequest;
import com.finartz.technicaltask.flightagency.controller.response.RouteResponse;
import com.finartz.technicaltask.flightagency.entity.Route;
import org.springframework.data.domain.Page;

public interface RouteService {

    RouteResponse createRoute(RouteRequest request);

    RouteResponse updateRoute(Long id, RouteRequest request);

    void deleteRoute(Long id);

    Page<RouteResponse> getAll(int pageIndex, int pageSize);

    RouteResponse getRoute(Long id);

    Route getRouteEntity(Long id);

    RouteResponse getRouteByShortCode(String shortCode);

}
