package com.codurance.hotel.bookings.service;

import com.codurance.hotel.bookings.exception.InsufficientPolicyException;
import com.codurance.hotel.bookings.exception.NoRoomAvailableException;
import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.bookings.repository.BookingRepository;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.exception.HotelNotExistsException;
import com.codurance.hotel.hotels.model.Hotel;
import com.codurance.hotel.hotels.service.HotelService;
import com.codurance.hotel.policies.service.PolicyService;
import com.codurance.hotel.utils.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BasicBookingServiceTest {

  @InjectMocks
  private BasicBookingService bookingService;

  @Mock
  private IdGenerator idGenerator;

  @Mock
  private BookingRepository bookingRepositoryStub;

  @Mock
  private PolicyService policyServiceStub;

  @Mock
  private HotelService hotelServiceStub;

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  private final Integer employeeId = 1;
  private final Integer hotelId = 1;
  private final RoomTypes roomType = RoomTypes.STANDARD;
  private final LocalDateTime checkIn = LocalDateTime
      .parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
  private final LocalDateTime checkOut = LocalDateTime
      .parse("2020-01-10 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


  @Test
  public void shouldPersistBooking() throws Exception {
    // given
    given(hotelServiceStub.findHotelById(hotelId)).willReturn(new Hotel());
    given(policyServiceStub.isBookingAllowed(employeeId, roomType)).willReturn(true);
    given(bookingRepositoryStub.findAvailableRooms(roomType, checkIn, checkOut))
        .willReturn(new ArrayList<>());

    // when
    bookingService.book(employeeId, hotelId, roomType, checkIn, checkOut);

    // then
    verify(bookingRepositoryStub).save(any(Booking.class));
  }

  @Test
  public void shouldNotBook_givenInsufficientPolicies() throws Exception {
    // given
    given(hotelServiceStub.findHotelById(hotelId)).willReturn(new Hotel());
    given(policyServiceStub.isBookingAllowed(employeeId, roomType)).willReturn(false);

    // when
    // then
    assertThatThrownBy(() -> bookingService.book(
        employeeId, hotelId, roomType, checkIn, checkOut))
        .isInstanceOf(InsufficientPolicyException.class);

    verify(bookingRepositoryStub, times(0)).save(any(Booking.class));
  }

  @Test
  public void shouldNotBook_givenRoomTakenInDateRange() throws Exception {
    // given
    given(hotelServiceStub.findHotelById(hotelId)).willReturn(new Hotel());
    given(policyServiceStub.isBookingAllowed(employeeId, roomType)).willReturn(true);
    given(bookingRepositoryStub.findAvailableRooms(roomType, checkIn, checkOut)).willReturn(null);

    // when
    // then
    assertThatThrownBy(() -> bookingService.book(
        employeeId, hotelId, roomType, checkIn, checkOut))
        .isInstanceOf(NoRoomAvailableException.class);

    verify(bookingRepositoryStub, times(0)).save(any(Booking.class));
  }

  @Test
  public void shouldNotBook_givenHotelDoesNotExist() throws Exception {
    // given
    given(hotelServiceStub.findHotelById(hotelId)).willReturn(null);

    // when
    // then
    assertThatThrownBy(() -> bookingService.book(
        employeeId, hotelId, roomType, checkIn, checkOut))
        .isInstanceOf(HotelNotExistsException.class);
    verify(bookingRepositoryStub, times(0)).save(any(Booking.class));
  }
}
