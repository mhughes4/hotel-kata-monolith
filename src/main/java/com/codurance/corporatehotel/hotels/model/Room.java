package com.codurance.corporatehotel.hotels.model;

import com.codurance.corporatehotel.common.model.RoomTypes;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Room {

  @Id
  @GeneratedValue
  private Integer id;

  private Integer roomNumber;

  @Enumerated
  private RoomTypes roomType;

  @ManyToOne
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public RoomTypes getRoomType() {
    return roomType;
  }

  public void setRoomType(RoomTypes roomType) {
    this.roomType = roomType;
  }

  public Room roomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
    return this;
  }

  public Room roomType(RoomTypes roomType) {
    this.roomType = roomType;
    return this;
  }
}
