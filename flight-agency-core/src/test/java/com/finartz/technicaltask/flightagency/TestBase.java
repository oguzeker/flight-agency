package com.finartz.technicaltask.flightagency;

import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.FlightResponse;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import com.finartz.technicaltask.flightagency.entity.Flight;
import com.finartz.technicaltask.flightagency.entity.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestBase {

    public static final Long ID = 1L;
    public static final Long CAPACITY = 10L;
    public static final LocalDateTime DATE_TIME = LocalDateTime.of(2011,11,11,11,11);
    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    public static final BigDecimal PERCENTILE = BigDecimal.TEN;
    public static final String CREDIT_CARD_NUMBER = "1234-1234-1234-1234";
    public static final String PNR = "F9A26";

    public TicketRequest createTicketRequest() {
        return TicketRequest.builder()
                .creditCardNumber(CREDIT_CARD_NUMBER)
                .flightId(ID)
                .build();
    }

    public Ticket createTicket() {
        return Ticket.builder()
                .id(ID)
                .active(Boolean.TRUE)
                .cost(AMOUNT)
                .createdOn(DATE_TIME)
                .creditCardNumber(CREDIT_CARD_NUMBER)
                .flight(createFlight())
                .pnr(PNR)
                .build();
    }

    public Flight createFlight() {
        return Flight.builder()
                .id(ID)
                .arrivalDate(DATE_TIME)
                .capacity(CAPACITY)
                .createdOn(DATE_TIME)
                .currentPercentileOfFlightCapacity(PERCENTILE)
                .currentPrice(AMOUNT)
                .departureDate(DATE_TIME)
                .route(null)
                .unitPrice(AMOUNT)
                .updatedOn(DATE_TIME)
                .build();
    }

    public TicketResponse createTicketResponse() {
        return TicketResponse.builder()
                .id(ID)
                .active(Boolean.TRUE)
                .cost(AMOUNT)
                .createdOn(DATE_TIME)
                .creditCardNumber(CREDIT_CARD_NUMBER)
                .flight(createFlightResponse())
                .pnr(PNR)
                .build();
    }

    public FlightResponse createFlightResponse() {
        return FlightResponse.builder()
                .id(ID)
                .arrivalDate(DATE_TIME)
                .capacity(CAPACITY)
                .createdOn(DATE_TIME)
                .currentPercentileOfFlightCapacity(PERCENTILE)
                .currentPrice(AMOUNT)
                .departureDate(DATE_TIME)
                .route(null)
                .unitPrice(AMOUNT)
                .updatedOn(DATE_TIME)
                .build();
    }

}
