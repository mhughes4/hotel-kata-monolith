package com.codurance.corporatehotel.hotels.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {

  @Id
  private Integer id;

  @Version
  private Integer version;

  private String name;

  @OneToMany(mappedBy = "hotel")
  private List<Room> rooms;

  public List<Room> getRooms() {
    return rooms;
  }

  public void setRooms(List<Room> rooms) {
    this.rooms = rooms;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
