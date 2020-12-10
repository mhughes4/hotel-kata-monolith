package com.codurance.hotel.common.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public class Policy {

  @Id
  @GeneratedValue
  protected Integer id;

  @Version
  protected Integer version;

  @ManyToMany
  private List<RoomType> roomTypes = new ArrayList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<RoomType> getRoomTypes() {
    return roomTypes;
  }

  public void setRoomTypes(List<RoomType> roomTypes) {
    this.roomTypes = roomTypes;
  }

  public void addRoomType(RoomTypes standard) {
    roomTypes.add(new RoomType(standard));
  }
}
