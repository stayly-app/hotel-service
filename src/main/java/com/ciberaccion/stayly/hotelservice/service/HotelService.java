package com.ciberaccion.stayly.hotelservice.service;

import com.ciberaccion.stayly.hotelservice.dto.request.HotelRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelResponse;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelSummaryResponse;

import java.util.List;
import java.util.UUID;

public interface HotelService {

    HotelResponse create(HotelRequest request);

    HotelResponse findById(UUID id);

    List<HotelSummaryResponse> findAll();

    List<HotelSummaryResponse> findByCity(String city);

    List<HotelSummaryResponse> findByCityAndMinStars(String city, Integer stars);

    HotelResponse update(UUID id, HotelRequest request);

    void delete(UUID id);
}