package com.codurance.corporatehotel.bookings.resource;

import com.codurance.corporatehotel.bookings.model.Booking;
import com.codurance.corporatehotel.bookings.service.BookingService;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.codurance.corporatehotel.hotels.service.HotelService;
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