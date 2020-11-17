package com.codurance.corporatehotel.common.repository;

import com.codurance.corporatehotel.common.model.RoomType;
import com.codurance.corporatehotel.common.model.RoomTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer> {

    RoomType findByRoomType(RoomTypes roomType);
}
