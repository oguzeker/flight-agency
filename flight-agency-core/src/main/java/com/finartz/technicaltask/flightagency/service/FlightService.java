package com.finartz.technicaltask.flightagency.service;

import com.finartz.technicaltask.flightagency.controller.request.FlightRequest;
import com.finartz.technicaltask.flightagency.controller.response.FlightResponse;
import com.finartz.technicaltask.flightagency.entity.Flight;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface FlightService {

    FlightResponse createFlight(FlightRequest request);

    FlightResponse reassignFlight(Long id, Long airlineId);

    FlightResponse updateFlight(Long id, FlightRequest request);

    void deleteFlight(Long id);

    Page<FlightResponse> getAll(int pageIndex, int pageSize);

    FlightResponse getFlight(Long id);

    Flight getFlightEntity(Long id);

    BigDecimal getCalculatedPriceForFlight(Long flightId, Long activeTicketCountOfFlight);

    List<FlightResponse> getFlightsByDepartureAirport(Long departureAirportId);

    List<FlightResponse> getFlightsByArrivalAirport(Long arrivalAirportId);

}
