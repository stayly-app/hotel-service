package com.ciberaccion.stayly.hotelservice.service.impl;

import com.ciberaccion.stayly.hotelservice.dto.request.RoomRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.RoomResponse;
import com.ciberaccion.stayly.hotelservice.exception.ResourceNotFoundException;
import com.ciberaccion.stayly.hotelservice.model.Hotel;
import com.ciberaccion.stayly.hotelservice.model.Room;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;
import com.ciberaccion.stayly.hotelservice.repository.HotelRepository;
import com.ciberaccion.stayly.hotelservice.repository.RoomRepository;
import com.ciberaccion.stayly.hotelservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public RoomResponse create(UUID hotelId, RoomRequest request) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + hotelId));

        if (roomRepository.existsByHotelIdAndRoomNumber(hotelId, request.getRoomNumber())) {
            throw new IllegalArgumentException("Room number " + request.getRoomNumber() + " already exists in this hotel");
        }

        Room room = Room.builder()
                .hotel(hotel)
                .roomNumber(request.getRoomNumber())
                .type(request.getType())
                .capacity(request.getCapacity())
                .pricePerNight(request.getPricePerNight())
                .amenities(request.getAmenities())
                .status(request.getStatus())
                .build();

        return toResponse(roomRepository.save(room));
    }

    @Override
    @Transactional(readOnly = true)
    public RoomResponse findById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

        return toResponse(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> findByHotelId(UUID hotelId) {
        return roomRepository.findByHotelId(hotelId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> findByHotelIdAndStatus(UUID hotelId, RoomStatus status) {
        return roomRepository.findByHotelIdAndStatus(hotelId, status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomResponse> findByHotelIdAndType(UUID hotelId, RoomType type) {
        return roomRepository.findByHotelIdAndType(hotelId, type)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public RoomResponse update(UUID id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

        room.setRoomNumber(request.getRoomNumber());
        room.setType(request.getType());
        room.setCapacity(request.getCapacity());
        room.setPricePerNight(request.getPricePerNight());
        room.setAmenities(request.getAmenities());
        room.setStatus(request.getStatus());

        return toResponse(roomRepository.save(room));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));

        roomRepository.delete(room);
    }

    // ---- Mapper ----

    private RoomResponse toResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .hotelId(room.getHotel().getId())
                .roomNumber(room.getRoomNumber())
                .type(room.getType())
                .capacity(room.getCapacity())
                .pricePerNight(room.getPricePerNight())
                .amenities(room.getAmenities())
                .status(room.getStatus())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }
}