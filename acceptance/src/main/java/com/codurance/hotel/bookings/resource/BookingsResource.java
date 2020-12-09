package com.codurance.hotel.bookings.resource;

import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.bookings.service.BookingService;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.model.Hotel;
import com.codurance.hotel.hotels.model.Room;
import com.codurance.hotel.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingsResource {

  private final BookingService bookingService;

  @Autowired
  public BookingsResource(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public ResponseEntity<Booking> book(@RequestBody @Valid Booking booking) {
    Booking book = bookingService.book(booking.getEmployeeId()
            , booking.getHotel().getId()
            , booking.getRoomType()
            , booking.getCheckIn()
            , booking.getCheckOut());

    return new ResponseEntity<>(book, HttpStatus.CREATED);
  }

}
