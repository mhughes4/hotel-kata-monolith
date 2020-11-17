package com.codurance.corporatehotel.companies.service;

import com.codurance.corporatehotel.companies.model.Company;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.repository.EmployeePolicyRepository;
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

  public void addEmployee(Integer companyId, Integer employeeId) {
    Optional<Company> companyOpt = this.companyRepository.findById(companyId);
    Company company = null;

    if (companyOpt.isPresent()) {
      company = new Company();
      company.setId(companyId);

      this.companyRepository.save(company);
    }

    Optional<Employee> employeeOpt = this.employeeRepository.findById(employeeId);

    if (employeeOpt.isPresent()) {
      Employee employee = new Employee();
      employee.setId(employeeId);
      employee.setCompany(company);

      this.employeeRepository.save(employee);
    }
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
