package com.finartz.technicaltask.flightagency.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Flight dto for Post/Update requests")
public class FlightRequest {

    @ApiModelProperty(example = "2", position = 1)
    private Long routeId;

    @ApiModelProperty(example = "11-12-2020 19:30", position = 2)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime departureDate;

    @ApiModelProperty(example = "12-12-2020 08:45", position = 3)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime arrivalDate;

    @ApiModelProperty(example = "150", position = 4)
    private long capacity;

    @ApiModelProperty(example = "200", position = 5)
    private BigDecimal unitPrice;

}
