package com.codurance.corporatehotel.hotels.service;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.codurance.corporatehotel.hotels.repository.HotelRepository;
import com.codurance.corporatehotel.hotels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasicHotelService implements HotelService {

  private final HotelRepository hotelRepository;
  private final RoomRepository roomRepository;

  @Autowired
  public BasicHotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
    this.hotelRepository = hotelRepository;
    this.roomRepository = roomRepository;
  }

  @Transactional
  public void addHotel(Integer hotelId, String hotelName) {
    Hotel hotel = new Hotel();
    hotel.setId(hotelId);
    hotel.setName(hotelName);

    this.hotelRepository.save(hotel);
  }

  @Transactional(readOnly = true)
  public void setRoom(Integer hotelId, Integer roomNumber, RoomTypes roomType) {
    this.hotelRepository.findById(hotelId).ifPresent(hotel -> {
      this.roomRepository.findByHotelIdAndRoomNumber(hotelId, roomNumber).ifPresentOrElse(room -> {
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
      }, () ->{
        Room room = new Room();
        room.setRoomNumber(roomNumber);
        room.setRoomType(roomType);
        room.setHotel(hotelRepository.findById(hotelId).get());

        this.roomRepository.save(room);
      });
    });
  }

  public Hotel findHotelById(Integer hotelId) {

    return this.hotelRepository.findById(hotelId).get();
  }
}
