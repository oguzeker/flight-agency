package com.finartz.technicaltask.flightagency.controller.response;

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
@ApiModel(description = "Airport dto for responses")
public class AirportResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(value = "IATA standard airport code", example = "SAW", position = 2)
    private String shortCode;

    @ApiModelProperty(position = 3)
    private String name;

    @ApiModelProperty(position = 4)
    private String city;

}
