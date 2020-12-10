package com.codurance.hotel.common.repository;

import com.codurance.hotel.common.model.RoomType;
import com.codurance.hotel.common.model.RoomTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer> {

    RoomType findByRoomType(RoomTypes roomType);
}
