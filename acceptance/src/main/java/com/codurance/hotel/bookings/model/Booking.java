package com.codurance.hotel.bookings.model;

import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.hotels.model.Hotel;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {

  private Integer fred;
  private Integer barney;

  @Id
  private Integer employeeId;

  @Version
  private Integer version;

  @Enumerated
  private RoomTypes roomType;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  private LocalDateTime checkIn;
  private LocalDateTime checkOut;
  private Integer uuid;

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public RoomTypes getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomTypes roomType) {
    this.roomType = roomType;
  }

  public LocalDateTime getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(LocalDateTime checkIn) {
    this.checkIn = checkIn;
  }

  public LocalDateTime getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(LocalDateTime _checkOut) {
    this.checkOut = _checkOut;
  }

  public Integer getUuid() {
    return uuid;
  }

  public void setUuid(Integer uuid) {
    this.uuid = uuid;
  }
}
