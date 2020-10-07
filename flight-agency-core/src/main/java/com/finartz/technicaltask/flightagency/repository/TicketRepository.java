package com.finartz.technicaltask.flightagency.repository;

import com.finartz.technicaltask.flightagency.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByPnr(String pnr);

    Long countByFlightIdAndActive(Long flightId, boolean active);

}
