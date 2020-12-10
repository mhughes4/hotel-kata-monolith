package com.codurance.hotel.common.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RoomType {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated
    private RoomTypes roomType;

    public RoomType() {
    }

    public RoomType(RoomTypes roomTypes) {
        this.roomType = roomTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypes roomTypes) {
        this.roomType = roomTypes;
    }
}
