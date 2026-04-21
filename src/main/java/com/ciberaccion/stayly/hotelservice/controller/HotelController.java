package com.ciberaccion.stayly.hotelservice.controller;

import com.ciberaccion.stayly.hotelservice.dto.request.HotelRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelResponse;
import com.ciberaccion.stayly.hotelservice.dto.response.HotelSummaryResponse;
import com.ciberaccion.stayly.hotelservice.service.HotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> create(@Valid @RequestBody HotelRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<HotelSummaryResponse>> findAll(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer minStars) {

        if (city != null && minStars != null) {
            return ResponseEntity.ok(hotelService.findByCityAndMinStars(city, minStars));
        }
        if (city != null) {
            return ResponseEntity.ok(hotelService.findByCity(city));
        }
        return ResponseEntity.ok(hotelService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody HotelRequest request) {
        return ResponseEntity.ok(hotelService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        hotelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}