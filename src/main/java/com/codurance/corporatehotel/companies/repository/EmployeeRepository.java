package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
