package com.codurance.corporatehotel.hotels.repository;

import com.codurance.corporatehotel.hotels.model.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Integer> {
}
