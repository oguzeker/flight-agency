package com.finartz.technicaltask.flightagency.repository;

import com.finartz.technicaltask.flightagency.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

    Optional<Airline> findByShortCode(String shortCode);

}
