package com.finartz.technicaltask.flightagency.repository;

import com.finartz.technicaltask.flightagency.entity.Airline;
import com.finartz.technicaltask.flightagency.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Airline> findByShortCode(String shortCode);

}
