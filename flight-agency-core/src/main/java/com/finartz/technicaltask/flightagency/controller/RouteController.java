package com.finartz.technicaltask.flightagency.controller;

import com.finartz.technicaltask.flightagency.config.SwaggerConfig;
import com.finartz.technicaltask.flightagency.controller.request.RouteRequest;
import com.finartz.technicaltask.flightagency.controller.response.RouteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = {
        SwaggerConfig.TAG_ROUTE
})
public interface RouteController {

    @ApiOperation(value = "Create Route",notes = "This endpoint creates route entity")
    ResponseEntity<RouteResponse> saveRoute(
            @ApiParam(value = "Route entity to create") @RequestBody RouteRequest routeReqDto);

    @ApiOperation(value = "Update Route",notes = "This endpoint updates route entity")
    ResponseEntity<RouteResponse> updateRoute(
            @ApiParam(value = "Id of the route entity to update") @PathVariable Long id,
            @ApiParam(value = "Details of the route entity to update") @RequestBody RouteRequest routeReqDto);

    @ApiOperation(value = "Delete Route",notes = "This endpoint deletes route entity with the given id")
    ResponseEntity<String> deleteRoute(
            @ApiParam(value = "Id of the route entity to delete") @PathVariable Long id);

    @ApiOperation(value = "Get All Routes",notes = "This endpoint fetches route entities (with Pagination)")
    ResponseEntity<Page<RouteResponse>> getAllRoutes(
            @ApiParam(value = "Index of the requested page of bulk results", example = "0") @RequestParam int pageIndex,
            @ApiParam(value = "Size of the requested page of bulk results", example = "10") @RequestParam int pageSize);

    @ApiOperation(value = "Get Route By Id",notes = "This endpoint fetches route entity with the given id")
    ResponseEntity<RouteResponse> getRouteById(
            @ApiParam(value = "Id of the route entity to fetch") @PathVariable Long id);

}
