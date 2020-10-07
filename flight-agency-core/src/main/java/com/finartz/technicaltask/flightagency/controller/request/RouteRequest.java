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
@ApiModel(description = "Route dto for Post/Update requests")
public class RouteRequest {

    @ApiModelProperty(example = "TK1852", position = 1)
    private String shortCode;

    @ApiModelProperty(example = "1", position = 2)
    private Long airlineId;

    @ApiModelProperty(example = "1", position = 3)
    private Long fromAirportId;

    @ApiModelProperty(example = "2", position = 4)
    private Long toAirportId;

}
