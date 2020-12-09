package com.codurance.hotel.bookings.repository;

import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {

  // Fixme
  @Query("from Room")
  List<Room> findAvailableRooms(RoomTypes roomType, LocalDateTime checkIn, LocalDateTime checkOut);
}
