package com.ciberaccion.stayly.hotelservice.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelSummaryResponse {

    private UUID id;
    private String name;
    private String country;
    private String city;
    private Integer stars;
}