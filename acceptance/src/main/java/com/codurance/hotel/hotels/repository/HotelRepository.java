package com.codurance.hotel.hotels.repository;

import com.codurance.hotel.hotels.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}
