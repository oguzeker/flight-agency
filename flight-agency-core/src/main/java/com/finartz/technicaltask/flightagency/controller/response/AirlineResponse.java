package com.finartz.technicaltask.flightagency.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Airline dto for responses")
public class AirlineResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2)
    private String shortCode;

    @ApiModelProperty(position = 3)
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("airline")
    @ApiModelProperty(position = 4, value = "Active Flights of the airline")
    private List<RouteResponse> routes;

}
