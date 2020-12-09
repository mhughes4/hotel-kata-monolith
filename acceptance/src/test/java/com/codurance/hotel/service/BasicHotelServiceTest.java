package com.codurance.hotel.hotels.service;

import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.exception.HotelExistsException;
import com.codurance.hotel.hotels.model.Hotel;
import com.codurance.hotel.hotels.model.Room;
import com.codurance.hotel.hotels.repository.HotelRepository;
import com.codurance.hotel.hotels.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class BasicHotelServiceTest {

  @InjectMocks
  private BasicHotelService hotelService;

  @Mock
  private HotelRepository hotelRepositoryStub;

  @Mock
  private RoomRepository roomRepositoryStub;

  private final Integer roomNumber = 1;
  private final RoomTypes roomType = RoomTypes.STANDARD;
  private final Integer hotelId = 1;
  private final String hotelName = "Marriott - London";

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldAddHotel() throws Exception {
    // when
    hotelService.addHotel(hotelId, hotelName);

    // then
    verify(hotelRepositoryStub).save(any(Hotel.class));
  }

  @Test
  public void shouldNotAddHotel() throws Exception {
    // given
    doThrow(HotelExistsException.class).when(hotelRepositoryStub).save(any(Hotel.class));

    // when
    // then
    assertThrows(HotelExistsException.class, () -> hotelService.addHotel(hotelId, hotelName));

  }

  @Test
  public void shouldSetRoom() throws Exception {
    // given
    given(hotelRepositoryStub.findById(hotelId)).willReturn(Optional.of(new Hotel()));

    // when
    hotelService.setRoom(hotelId, roomNumber, roomType);

    // then
    verify(roomRepositoryStub).save(any(Room.class));
  }

  @Test
  public void shouldUpdateRoom() {
    // given
    given(hotelRepositoryStub.findById(hotelId)).willReturn(Optional.of(new Hotel()));
    given(roomRepositoryStub.findByHotelIdAndRoomNumber(hotelId, roomNumber)).willReturn(Optional.of(new Room()));

    // when
    hotelService.setRoom(hotelId, roomNumber, roomType);

    // then
    verify(roomRepositoryStub, times(0)).save(any(Room.class));
  }

}
