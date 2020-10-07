package com.finartz.technicaltask.flightagency.repository;

import com.finartz.technicaltask.flightagency.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
