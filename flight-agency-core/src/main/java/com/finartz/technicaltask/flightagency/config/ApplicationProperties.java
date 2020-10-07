package com.finartz.technicaltask.flightagency.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Data
@Getter
@ConfigurationProperties(prefix = "flight-agency")
public class ApplicationProperties {

    private BigDecimal capacityPercentileIncrement;
    private BigDecimal pricePercentage;
    private int scale;

}
