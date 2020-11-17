package com.codurance.corporatehotel.policies.repository;

import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.policies.model.CompanyPolicy;
import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePolicyRepository extends CrudRepository<> {

  void persistEmployeePolicy(Integer employeeId, RoomTypes roomType);

  void persistCompanyPolicy(Integer companyId, RoomTypes roomType);

  EmployeePolicy findForEmployee(Integer employeeId);

  void updateEmployeePolicy(Integer employeeId, RoomTypes roomType);

  CompanyPolicy findForCompany(Integer companyId);

  void updateCompanyPolicy(Integer companyId, RoomTypes roomType);

  void deleteEmployee(Integer employeeId);
}
