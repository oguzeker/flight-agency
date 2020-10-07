package com.finartz.technicaltask.flightagency.repository;

import com.finartz.technicaltask.flightagency.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    /*
       Native SQL query
     */
    @Query(value = "select * from flight f inner join route r on f.route_id = r.id " +
            " where r.from_airport_id = :departureAirportId", nativeQuery = true)
    List<Flight> findByDepartureAirportId(@Param("departureAirportId") Long departureAirportId);

    /*
        HQL Query
     */
    @Query(value = "from Flight f join f.route r join r.toAirport a where a.id = :arrivalAirportId")
    List<Flight> findByArrivalAirportId(@Param("arrivalAirportId") Long arrivalAirportId);

}
