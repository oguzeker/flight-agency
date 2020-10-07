package com.finartz.technicaltask.flightagency.service;

import com.finartz.technicaltask.flightagency.controller.request.AirportRequest;
import com.finartz.technicaltask.flightagency.controller.response.AirportResponse;
import com.finartz.technicaltask.flightagency.entity.Airport;
import org.springframework.data.domain.Page;

public interface AirportService {

    AirportResponse createAirport(AirportRequest request);

    AirportResponse updateAirport(Long id, AirportRequest request);

    void deleteAirport(Long id);

    Page<AirportResponse> getAll(int pageIndex, int pageSize);

    AirportResponse getAirport(Long id);

    Airport getAirportEntity(Long id);

}
