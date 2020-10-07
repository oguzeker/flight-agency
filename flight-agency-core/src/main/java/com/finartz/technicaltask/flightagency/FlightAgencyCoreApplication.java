package com.finartz.technicaltask.flightagency;

import com.finartz.technicaltask.flightagency.config.ApplicationProperties;
import com.finartz.technicaltask.flightagency.config.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@EnableSwagger2
@EnableJpaAuditing
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({
		ApplicationProperties.class,
		SwaggerProperties.class
})
public class FlightAgencyCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightAgencyCoreApplication.class, args);
	}
}
