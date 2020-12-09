package com.codurance.hotel.policies.service;

import com.codurance.hotel.common.model.RoomTypes;
import java.util.List;

public interface PolicyService {

  void setEmployeePolicy(Integer employeeId, List<RoomTypes> roomTypes);

  void setCompanyPolicy(Integer companyId, List<RoomTypes> roomTypes);

  boolean isBookingAllowed(Integer employeeId, RoomTypes roomType);

}
