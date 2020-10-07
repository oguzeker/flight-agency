package com.finartz.technicaltask.flightagency.controller;

import com.finartz.technicaltask.flightagency.config.SwaggerConfig;
import com.finartz.technicaltask.flightagency.controller.request.AirlineRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirlineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = {
        SwaggerConfig.TAG_AIRLINE
})
public interface AirlineController {

    @ApiOperation(value = "Create Airline", notes = "This endpoint creates airline entity")
    ResponseEntity<AirlineResponse> createAirline(
            @ApiParam(value = "Airline entity to create") @RequestBody AirlineRequest request);

    @ApiOperation(value = "Update Airline", notes = "This endpoint updates airline entity")
    ResponseEntity<AirlineResponse> updateAirline(
            @ApiParam(value = "Id of the airline entity to update", example = "1") @PathVariable Long id,
            @ApiParam(value = "Details of the airline entity to update") @RequestBody AirlineRequest request);

    @ApiOperation(value = "Delete Airline", notes = "This endpoint deletes airline entity with the given id")
    ResponseEntity<String> deleteAirline(
            @ApiParam(value = "Id of the airline entity to delete", example = "1") @PathVariable Long id);

    @ApiOperation(value = "Get All Airlines", notes = "This endpoint fetches airline entities (with Pagination)")
    ResponseEntity<Page<AirlineResponse>> getAllAirlines(
            @ApiParam(value = "Index of the requested page of bulk results", example = "0") @RequestParam int pageIndex,
            @ApiParam(value = "Size of the requested page of bulk results", example = "10") @RequestParam int pageSize);

    @ApiOperation(value = "Get Airline By Id", notes = "This endpoint fetches airline entity with the given id")
    ResponseEntity<AirlineResponse> getAirlineById(
            @ApiParam(value = "Id of the airline entity to fetch", example = "1") @PathVariable Long id);

    @ApiOperation(value = "Get Airline By ShortCode",
            notes = "This endpoint fetches airline entities with the given ShortCode")
    ResponseEntity<AirlineResponse> getAirlineByShortCode(
            @ApiParam(value = "ShortCode of the airline entity to fetch") @PathVariable String shortCode);

}
