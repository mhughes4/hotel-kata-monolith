package com.codurance.hotel.hotels.exception;

public class HotelNotExistsException extends RuntimeException {

  public HotelNotExistsException() {
    super("A hotel with given id does not exist in the system.");
  }

}
