package com.finartz.technicaltask.flightagency.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Route dto for the responses")
public class RouteResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(example = "TK1852", position = 2)
    private String shortCode;

    @ApiModelProperty(position = 3)
    @JsonIgnoreProperties("routes")
    private AirlineResponse airline;

    @ApiModelProperty(example = "2", position = 4)
    private AirportResponse fromAirport;

    @ApiModelProperty(example = "1", position = 5)
    private AirportResponse toAirport;

}
