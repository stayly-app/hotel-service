package com.ciberaccion.stayly.hotelservice.service;

import com.ciberaccion.stayly.hotelservice.dto.request.RoomRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.RoomResponse;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    RoomResponse create(UUID hotelId, RoomRequest request);

    RoomResponse findById(UUID id);

    List<RoomResponse> findByHotelId(UUID hotelId);

    List<RoomResponse> findByHotelIdAndStatus(UUID hotelId, RoomStatus status);

    List<RoomResponse> findByHotelIdAndType(UUID hotelId, RoomType type);

    RoomResponse update(UUID id, RoomRequest request);

    void delete(UUID id);
}