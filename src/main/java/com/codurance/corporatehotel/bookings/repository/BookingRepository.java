package com.codurance.corporatehotel.bookings.repository;

import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Room;
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
