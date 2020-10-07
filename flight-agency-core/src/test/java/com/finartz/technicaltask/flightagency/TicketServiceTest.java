package com.finartz.technicaltask.flightagency;

import com.finartz.technicaltask.flightagency.controller.request.TicketRequest;
import com.finartz.technicaltask.flightagency.controller.response.TicketResponse;
import com.finartz.technicaltask.flightagency.entity.Flight;
import com.finartz.technicaltask.flightagency.entity.Ticket;
import com.finartz.technicaltask.flightagency.repository.TicketRepository;
import com.finartz.technicaltask.flightagency.service.FlightService;
import com.finartz.technicaltask.flightagency.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


@CoreTest
public class TicketServiceTest extends TestBase {

    @InjectMocks
    TicketServiceImpl subject;
    @Mock
    TicketRepository repository;
    @Mock
    FlightService flightService;
    @Mock
    ModelMapper mapper;
    @Captor
    ArgumentCaptor<Ticket> captor;

    @Test
    public void createTicket_Test() {
        TicketRequest request = createTicketRequest();
        BigDecimal cost = BigDecimal.TEN;
        Long activeTicketCountByFlightId = 0L;
        Ticket ticket = createTicket();
        Flight flight = createFlight();
        TicketResponse response = createTicketResponse();

        when(repository.countByFlightIdAndActive(anyLong(), anyBoolean())).thenReturn(activeTicketCountByFlightId);
        when(flightService.getCalculatedPriceForFlight(anyLong(), anyLong())).thenReturn(cost);
        when(mapper.map(request, Ticket.class)).thenReturn(ticket);
        when(flightService.getFlightEntity(anyLong())).thenReturn(flight);
        when(repository.save(ticket)).thenReturn(ticket);
        when(mapper.map(ticket, TicketResponse.class)).thenReturn(response);

        TicketResponse subjectResponse = subject.createTicket(request);

        assert subjectResponse != null;
        assert subjectResponse.isActive();
        assert subjectResponse.getCost().equals(AMOUNT);
        assert subjectResponse.getCreditCardNumber().equals(CREDIT_CARD_NUMBER);

        verify(repository).save(captor.capture());
        Ticket capturedTicket = captor.getValue();

        assert capturedTicket != null;
        assert capturedTicket.isActive();
        assert capturedTicket.getCost().equals(cost);
        assert capturedTicket.getCreditCardNumber().equals(CREDIT_CARD_NUMBER);

        verify(mapper, times(1)).map(request, Ticket.class);
        verify(mapper, times(1)).map(ticket, TicketResponse.class);
        verify(repository, times(1)).save(ticket);

        verifyNoMoreInteractions(mapper);
        verifyNoMoreInteractions(repository);

    }

}