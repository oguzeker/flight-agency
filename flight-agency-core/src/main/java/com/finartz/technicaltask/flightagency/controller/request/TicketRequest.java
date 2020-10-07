package com.finartz.technicaltask.flightagency.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.finartz.technicaltask.flightagency.deserializer.CreditCardNumberDeserializer;
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
@ApiModel(description = "Ticket dto for Post/Update requests")
public class TicketRequest {

    @ApiModelProperty(example = "2", position = 1)
    private Long flightId;

    @JsonDeserialize(using = CreditCardNumberDeserializer.class)
    @ApiModelProperty(value = "Credit card number (any format)", example = "1234-1234-1234-1234", position = 2)
    private String creditCardNumber;

}
