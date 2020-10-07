package com.finartz.technicaltask.flightagency.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Flight dto for Responses")
public class FlightResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2, value = "Departure and Arrival airport info")
    private RouteResponse route;

    @ApiModelProperty(position = 3)
    @DateTimeFormat
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime departureDate;

    @ApiModelProperty(position = 4)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime arrivalDate;

    @ApiModelProperty(position = 5)
    private Long capacity;

    @ApiModelProperty(position = 6)
    private BigDecimal unitPrice;

    @ApiModelProperty(position = 7)
    private BigDecimal currentPrice;

    @ApiModelProperty(example = "10.00", position = 8)
    private BigDecimal currentPercentileOfFlightCapacity;

    @ApiModelProperty(position = 9)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;

    @ApiModelProperty(position = 10)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime updatedOn;

}
