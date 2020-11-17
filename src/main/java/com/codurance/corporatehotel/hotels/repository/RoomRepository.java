package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.hotels.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

  Optional<Room> findByHotelIdAndRoomNumber(Integer hotelId, Integer roomNumber);
}
