package com.codurance.corporatehotel.policies.repository;

import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeePolicyRepository extends JpaRepository<EmployeePolicy, Integer> {

  Optional<EmployeePolicy> findByEmployeeId(Integer employeeId);
}
