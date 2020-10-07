package com.finartz.technicaltask.flightagency.controller.request;

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
@ApiModel(description = "Airline dto for Post/Update requests")
public class AirlineRequest {

    @ApiModelProperty(value = "IATA standard airline code", example = "LH", position = 1)
    private String shortCode;

    @ApiModelProperty(example = "Lufthansa Airlines", position = 2)
    private String name;

}
