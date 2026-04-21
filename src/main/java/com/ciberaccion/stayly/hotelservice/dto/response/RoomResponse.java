package com.ciberaccion.stayly.hotelservice.dto.response;

import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomResponse {

    private UUID id;
    private UUID hotelId;
    private String roomNumber;
    private RoomType type;
    private Integer capacity;
    private BigDecimal pricePerNight;
    private String amenities;
    private RoomStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}