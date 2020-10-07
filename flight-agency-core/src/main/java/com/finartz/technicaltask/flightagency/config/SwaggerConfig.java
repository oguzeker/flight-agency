package com.finartz.technicaltask.flightagency.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@AllArgsConstructor
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    public static final String TAG_AIRLINE = "Airline Management Screen";
    public static final String TAG_AIRPORT = "Airport Management Screen";
    public static final String TAG_FLIGHT = "Flight Management Screen";
    public static final String TAG_ROUTE = "Route Management Screen";
    public static final String TAG_TICKET = "Ticket Management Screen";

    public static final String DESC_AIRLINE = "Search and manage airlines";
    public static final String DESC_AIRPORT = "Search and manage airports";
    public static final String DESC_FLIGHT = "Search and manage flights";
    public static final String DESC_ROUTE = "Search and manage routes";
    public static final String DESC_TICKET = "Search and manage tickets";

    private final SwaggerProperties properties;

    @Bean
    public Docket api(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo)
                .tags(new Tag(TAG_AIRLINE, DESC_AIRLINE))
                .tags(new Tag(TAG_AIRPORT, DESC_AIRPORT))
                .tags(new Tag(TAG_FLIGHT, DESC_FLIGHT))
                .tags(new Tag(TAG_ROUTE, DESC_ROUTE))
                .tags(new Tag(TAG_TICKET, DESC_TICKET));
    }

    @Bean
    public ApiInfo apiInfo() {
        SwaggerProperties.Contact contact = properties.getContact();
        List<SwaggerProperties.VendorExtension> vendorExtensions = properties.getVendorExtensions();

        return new ApiInfo(
                properties.getTitle(),
                properties.getDescription(),
                properties.getVersion(),
                properties.getTermsOfServiceUrl(),
                new Contact(contact.getName(), contact.getUrl(), contact.getEmail()),
                properties.getLicense(),
                properties.getLicenseUrl(),
                vendorExtensions.stream()
                        .map(extension -> new StringVendorExtension(extension.getName(), extension.getValue()))
                        .collect(Collectors.toList()));
    }

}
