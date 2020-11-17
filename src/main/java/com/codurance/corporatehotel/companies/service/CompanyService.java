package com.codurance.corporatehotel.companies.service;

import com.codurance.corporatehotel.companies.model.Employee;

public interface CompanyService {

  Employee addEmployee(Integer companyId, Integer employeeId);

  void deleteEmployee(Integer employeeId);
}
