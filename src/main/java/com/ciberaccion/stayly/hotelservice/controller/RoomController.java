package com.ciberaccion.stayly.hotelservice.controller;

import com.ciberaccion.stayly.hotelservice.dto.request.RoomRequest;
import com.ciberaccion.stayly.hotelservice.dto.response.RoomResponse;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;
import com.ciberaccion.stayly.hotelservice.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> create(
            @PathVariable UUID hotelId,
            @Valid @RequestBody RoomRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.create(hotelId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> findById(
            @PathVariable UUID hotelId,
            @PathVariable UUID id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> findByHotelId(
            @PathVariable UUID hotelId,
            @RequestParam(required = false) RoomStatus status,
            @RequestParam(required = false) RoomType type) {

        if (status != null) {
            return ResponseEntity.ok(roomService.findByHotelIdAndStatus(hotelId, status));
        }
        if (type != null) {
            return ResponseEntity.ok(roomService.findByHotelIdAndType(hotelId, type));
        }
        return ResponseEntity.ok(roomService.findByHotelId(hotelId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> update(
            @PathVariable UUID hotelId,
            @PathVariable UUID id,
            @Valid @RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID hotelId,
            @PathVariable UUID id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}