package com.ciberaccion.stayly.hotelservice.dto.request;

import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequest {

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType type;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal pricePerNight;

    private String amenities;

    @NotNull(message = "Status is required")
    private RoomStatus status;
}