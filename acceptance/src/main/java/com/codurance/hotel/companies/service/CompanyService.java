package com.codurance.hotel.companies.service;

import com.codurance.hotel.companies.model.Employee;

public interface CompanyService {

  Employee addEmployee(Integer companyId, Integer employeeId);

  void deleteEmployee(Integer employeeId);
}
