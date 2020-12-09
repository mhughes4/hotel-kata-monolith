package com.codurance.hotel.bookings.service;

import com.codurance.hotel.bookings.exception.InsufficientPolicyException;
import com.codurance.hotel.bookings.exception.NoRoomAvailableException;
import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.bookings.repository.BookingRepository;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.exception.HotelNotExistsException;
import com.codurance.hotel.hotels.service.HotelService;
import com.codurance.hotel.policies.service.PolicyService;
import com.codurance.hotel.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BasicBookingService implements BookingService {

  private IdGenerator idGenerator = new IdGenerator();

  @Autowired
  private BookingRepository bookingRepository;

  @Autowired
  private PolicyService policyService;

  @Autowired
  private HotelService hotelService;

  public Booking book(Integer employeeId, Integer hotelId, RoomTypes roomType,
      LocalDateTime checkIn, LocalDateTime checkOut) {
    this.validate(employeeId, hotelId, roomType, checkIn, checkOut);

    Booking booking = new Booking();

    booking.setUuid(this.idGenerator.generate());

    booking.setEmployeeId(employeeId);
    booking.setHotel(this.hotelService.findHotelById(hotelId));
    booking.setRoomType(roomType);
    booking.setCheckIn(checkIn);
    booking.setCheckOut(checkOut);

    this.bookingRepository.save(booking);

    return booking;
  }

  private void validate(Integer employeeId, Integer hotelId, RoomTypes roomType,
      LocalDateTime checkIn, LocalDateTime checkOut) {
    if (this.hotelService.findHotelById(hotelId) == null) {
      throw new HotelNotExistsException();
    }

    if (!this.policyService.isBookingAllowed(employeeId, roomType)) {
      throw new InsufficientPolicyException();
    }

    if (this.bookingRepository.findAvailableRooms(roomType, checkIn, checkOut) == null) {
      throw new NoRoomAvailableException();
    }
  }
}
