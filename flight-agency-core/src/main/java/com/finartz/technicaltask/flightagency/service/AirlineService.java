package com.finartz.technicaltask.flightagency.service;

import com.finartz.technicaltask.flightagency.controller.request.AirlineRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirlineResponse;
import com.finartz.technicaltask.flightagency.entity.Airline;
import org.springframework.data.domain.Page;

public interface AirlineService {

    AirlineResponse createAirline(AirlineRequest airlineReqDto);

    AirlineResponse updateAirline(Long id, AirlineRequest airlineReqDto);

    void deleteAirline(Long id);

    Page<AirlineResponse> getAll(int pageIndex, int pageSize);

    AirlineResponse getAirline(Long id);

    Airline getAirlineEntity(Long id);

    AirlineResponse getAirlineByShortCode(String shortCode);

}
