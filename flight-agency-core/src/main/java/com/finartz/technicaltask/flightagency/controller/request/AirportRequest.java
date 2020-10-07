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
@ApiModel(description = "Airport dto for Post/Update requests")
public class AirportRequest {

    @ApiModelProperty(value = "IATA standard airport code", example = "SAW", position = 1)
    private String shortCode;

    @ApiModelProperty(example = "Sabiha Gokcen Havalimani", position = 2)
    private String name;

    @ApiModelProperty(example = "Istanbul", position = 3)
    private String city;

}
