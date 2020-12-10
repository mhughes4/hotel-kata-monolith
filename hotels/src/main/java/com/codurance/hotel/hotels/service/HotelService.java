package com.codurance.hotel.hotels.service;

import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.model.Hotel;

public interface HotelService {

  void addHotel(Integer hotelId, String hotelName);

  void setRoom(Integer hotelId, Integer roomNumber, RoomTypes roomType);

  Hotel findHotelById(Integer hotelId);
}
