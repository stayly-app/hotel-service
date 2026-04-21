package com.ciberaccion.stayly.hotelservice.repository;

import com.ciberaccion.stayly.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {

    List<Hotel> findByCity(String city);

    List<Hotel> findByCountry(String country);

    List<Hotel> findByCityAndStarsGreaterThanEqual(String city, Integer stars);
}