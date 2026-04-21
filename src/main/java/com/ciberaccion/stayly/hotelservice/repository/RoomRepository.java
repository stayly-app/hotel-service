package com.ciberaccion.stayly.hotelservice.repository;

import com.ciberaccion.stayly.hotelservice.model.Room;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomStatus;
import com.ciberaccion.stayly.hotelservice.model.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    List<Room> findByHotelId(UUID hotelId);

    List<Room> findByHotelIdAndStatus(UUID hotelId, RoomStatus status);

    List<Room> findByHotelIdAndType(UUID hotelId, RoomType type);

    boolean existsByHotelIdAndRoomNumber(UUID hotelId, String roomNumber);
}