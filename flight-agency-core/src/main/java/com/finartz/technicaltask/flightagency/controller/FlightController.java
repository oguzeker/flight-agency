package com.finartz.technicaltask.flightagency.controller;

import com.finartz.technicaltask.flightagency.config.SwaggerConfig;
import com.finartz.technicaltask.flightagency.controller.request.FlightRequest;
import com.finartz.technicaltask.flightagency.controller.response.FlightResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(tags = {
        SwaggerConfig.TAG_FLIGHT
})
public interface FlightController {

    @ApiOperation(value = "Create Flight", notes = "This endpoint creates flight entity")
    ResponseEntity<FlightResponse> createFlight(
            @ApiParam(value = "Flight entity to create") @RequestBody FlightRequest request);

    @ApiOperation(value = "Reassign Flight To Route",
            notes = "This endpoint reassigns flight entity to airline entity")
    ResponseEntity<FlightResponse> reassignFlight(
            @ApiParam(value = "Id of the airline entity to assign") @PathVariable Long id,
            @ApiParam(value = "Id of the route entity to assign to") @PathVariable Long routeId);

    @ApiOperation(value = "Update Flight", notes = "This endpoint updates flight entity")
    ResponseEntity<FlightResponse> updateFlight(
            @ApiParam(value = "Id of the flight entity to be updated") @PathVariable Long id,
            @ApiParam(value = "Details of the flight entity to update") @RequestBody FlightRequest request);

    @ApiOperation(value = "Delete Flight", notes = "This endpoint deletes flight entity with the given id")
    ResponseEntity<String> deleteFlight(
            @ApiParam(value = "Id of the flight entity to delete") @PathVariable Long id);

    @ApiOperation(value = "Get All Flights", notes = "This endpoint fetches flight entities (with Pagination)")
    ResponseEntity<Page<FlightResponse>> getAllFlights(
            @ApiParam(value = "Index of the requested page of bulk results", example = "0") @RequestParam int pageIndex,
            @ApiParam(value = "Size of the requested page of bulk results", example = "10") @RequestParam int pageSize);

    @ApiOperation(value = "Get Flight By Id", notes = "This endpoint fetches flight entity with the given id")
    ResponseEntity<FlightResponse> getFlightById(
            @ApiParam(value = "Id of the flight entity to fetch") @PathVariable Long id);

    @ApiOperation(value = "Get Flight By Departure Airport Id",
            notes = "This endpoint fetches flight entity with the given Departure Airport id")
    ResponseEntity<List<FlightResponse>> getFlightsByDepartureAirport(
            @ApiParam(value = "Id of the airport entity to query for") @PathVariable Long departureAirportId);

    @ApiOperation(value = "Get Flight By Arrival Airport Id",
            notes = "This endpoint fetches flight entity with the given Arrival Airport id")
    ResponseEntity<List<FlightResponse>> getFlightsByArrivalAirport(
            @ApiParam(value = "Id of the airport entity to query for") @PathVariable Long arrivalAirportId);

}
