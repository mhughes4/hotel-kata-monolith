package com.codurance.hotel.companies.service;

import com.codurance.hotel.companies.model.Company;
import com.codurance.hotel.companies.model.Employee;
import com.codurance.hotel.companies.repository.CompanyRepository;
import com.codurance.hotel.companies.repository.EmployeeRepository;
import com.codurance.hotel.policies.repository.EmployeePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BasicCompanyService implements CompanyService {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private EmployeePolicyRepository employeePolicyRepository;

  public Employee addEmployee(Integer companyId, Integer employeeId) {
    Optional<Company> companyOpt = this.companyRepository.findById(companyId);
    Company company = null;

    if (companyOpt.isEmpty()) {
      company = new Company();
      company.setId(companyId);

      this.companyRepository.save(company);
    }

    Optional<Employee> employeeOpt = this.employeeRepository.findById(employeeId);
    Employee employeeToReturn = null;

    if (employeeOpt.isEmpty()) {
      Employee employee = new Employee();
      employee.setId(employeeId);
      employee.setCompany(company);

      this.employeeRepository.save(employee);
    }else{
      employeeToReturn = employeeOpt.get();
    }

    return employeeToReturn;
  }

  public void deleteEmployee(Integer employeeId) {
    this.employeeRepository.findById(employeeId).ifPresent((employee) ->{
      this.employeeRepository.delete(employee);

      employeePolicyRepository.findByEmployeeId(employeeId).ifPresent((employeePolicy -> {
        this.employeePolicyRepository.delete(employeePolicy);
      }));

    });
  }
}
