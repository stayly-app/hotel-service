package com.ciberaccion.stayly.hotelservice.service.impl;

import com.ciberaccion.stayly.hotelservice.dto.request.HotelRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelResponse;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelSummaryResponse;
import com.ciberaccion.stayly.hotelservice.exception.ResourceNotFoundException;
import com.ciberaccion.stayly.hotelservice.model.Hotel;
import com.ciberaccion.stayly.hotelservice.repository.HotelRepository;
import com.ciberaccion.stayly.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    @Transactional
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = Hotel.builder()
                .name(request.getName())
                .country(request.getCountry())
                .city(request.getCity())
                .address(request.getAddress())
                .stars(request.getStars())
                .build();

        Hotel saved = hotelRepository.save(hotel);
        hotelRepository.flush();
        return toResponse(hotelRepository.findById(saved.getId()).orElseThrow());
    }

    @Override
    @Transactional(readOnly = true)
    public HotelResponse findById(UUID id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        return toResponse(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelSummaryResponse> findAll() {
        return hotelRepository.findAll()
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelSummaryResponse> findByCity(String city) {
        return hotelRepository.findByCity(city)
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelSummaryResponse> findByCityAndMinStars(String city, Integer stars) {
        return hotelRepository.findByCityAndStarsGreaterThanEqual(city, stars)
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    @Override
    @Transactional
    public HotelResponse update(UUID id, HotelRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        hotel.setName(request.getName());
        hotel.setCountry(request.getCountry());
        hotel.setCity(request.getCity());
        hotel.setAddress(request.getAddress());
        hotel.setStars(request.getStars());

        return toResponse(hotelRepository.save(hotel));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));

        hotelRepository.delete(hotel);
    }

    // ---- Mappers ----

    private HotelResponse toResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .country(hotel.getCountry())
                .city(hotel.getCity())
                .address(hotel.getAddress())
                .stars(hotel.getStars())
                .createdAt(hotel.getCreatedAt())
                .updatedAt(hotel.getUpdatedAt())
                .build();
    }

    private HotelSummaryResponse toSummaryResponse(Hotel hotel) {
        return HotelSummaryResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .country(hotel.getCountry())
                .city(hotel.getCity())
                .stars(hotel.getStars())
                .build();
    }
}