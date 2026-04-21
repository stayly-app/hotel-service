package com.ciberaccion.stayly.hotelservice.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelResponse {

    private UUID id;
    private String name;
    private String country;
    private String city;
    private String address;
    private Integer stars;
    private List<RoomResponse> rooms;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}