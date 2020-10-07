package com.finartz.technicaltask.flightagency.controller;

import com.finartz.technicaltask.flightagency.config.SwaggerConfig;
import com.finartz.technicaltask.flightagency.controller.request.AirportRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirportResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = {
        SwaggerConfig.TAG_AIRPORT
})
public interface AirportController {

    @ApiOperation(value = "Create Airport", notes = "This endpoint creates airport entity")
    ResponseEntity<AirportResponse> createAirport(
            @ApiParam(value = "Airport entity to create") @RequestBody AirportRequest request);

    @ApiOperation(value = "Update Airport", notes = "This endpoint updates airport entity")
    ResponseEntity<AirportResponse> updateAirport(
            @ApiParam(value = "Id of the airport entity to update") @PathVariable Long id,
            @ApiParam(value = "Details of the airport entity to update") @RequestBody AirportRequest request);

    @ApiOperation(value = "Delete Airport", notes = "This endpoint deletes airport entity with the given id")
    ResponseEntity<String> deleteAirport(
            @ApiParam(value = "Id of the airport entity to delete") @PathVariable Long id);

    @ApiOperation(value = "Get All Airports", notes = "This endpoint fetches airport entities (with Pagination)")
    ResponseEntity<Page<AirportResponse>> getAllAirports(
            @ApiParam(value = "Index of the requested page of bulk results", example = "0") @RequestParam int pageIndex,
            @ApiParam(value = "Size of the requested page of bulk results", example = "10") @RequestParam int pageSize);

    @ApiOperation(value = "Get Airport By Id", notes = "This endpoint fetches airport entity with the given id")
    ResponseEntity<AirportResponse> getAirportById(
            @ApiParam(value = "Id of the airport entity to fetch") @PathVariable Long id);

}
