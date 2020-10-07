package com.finartz.technicaltask.flightagency.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.finartz.technicaltask.flightagency.deserializer.CreditCardNumberDeserializer;
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
@ApiModel(description = "Ticket dto for responses")
public class TicketResponse {

    @ApiModelProperty(position = 1)
    private Long id;

    @ApiModelProperty(position = 2, example = "F9A26")
    private String pnr;

    @ApiModelProperty(example = "2", position = 3)
    private FlightResponse flight;

    @ApiModelProperty(example = "250", position = 4)
    private BigDecimal cost;

    @JsonDeserialize(using = CreditCardNumberDeserializer.class)
    @ApiModelProperty(value = "Card No with any seperate characters", example = "1234-1234-1234-1234", position = 5)
    private String creditCardNumber;

    @ApiModelProperty(position = 6)
    @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;

    @ApiModelProperty(position = 7)
    private boolean active;

}
