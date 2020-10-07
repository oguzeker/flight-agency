package com.finartz.technicaltask.flightagency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "route", uniqueConstraints = @UniqueConstraint(name = "uq_q", columnNames = {
        "short_code",
        "from_airport_id",
        "to_airport_id"
}))
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_code")
    private String shortCode;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "from_airport_id")
    private Airport fromAirport;

    @ManyToOne
    @JoinColumn(name = "to_airport_id")
    private Airport toAirport;

}
