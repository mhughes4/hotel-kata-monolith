package com.codurance.hotel.bookings.service;

import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.common.model.RoomTypes;
import java.time.LocalDateTime;

public interface BookingService {

  Booking book(Integer employeeId, Integer hotelId, RoomTypes roomType,
      LocalDateTime checkIn, LocalDateTime checkOut);
}
