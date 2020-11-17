package com.codurance.corporatehotel.companies.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.codurance.corporatehotel.companies.model.Company;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.repository.EmployeePolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class BasicCompanyServiceTest {

  @InjectMocks
  private BasicCompanyService companyService;

  @Mock
  private CompanyRepository companyRepository;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private EmployeePolicyRepository policyRepository;

  private final Integer companyId = 1;
  private final Integer employeeId = 1;

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
  }

  public void shouldAddCompany_whenAddingAnEmployee() throws Exception {
    // given
    // when
    companyService.addEmployee(companyId, employeeId);
    // then
    verify(companyRepository).save(any(Company.class));
  }

  @Test
  public void shouldAddEmployeeWithoutAddingCompany() throws Exception {
    // given
    given(companyRepository.findById(companyId)).willReturn(Optional.of(new Company(companyId)));

    // when
    companyService.addEmployee(companyId, employeeId);

    // then
    verify(companyRepository, times(0)).save(any(Company.class));
    verify(employeeRepository).save(any(Employee.class));
  }

  @Test
  public void shouldAddEmployee() throws Exception {
    // given
    // when
    companyService.addEmployee(companyId, employeeId);
    // then
    verify(employeeRepository).save(any(Employee.class));
  }

  @Test
  public void shouldNotAddEmployee_givenAlreadyExistsInTheSameCompany() throws Exception {
    // given
    given(companyRepository.findById(companyId)).willReturn(Optional.of(new Company(companyId)));
    given(employeeRepository.findById(employeeId)).willReturn(Optional.of(new Employee(employeeId)));

    // when
    companyService.addEmployee(companyId, employeeId);

    // then
    verify(employeeRepository, times(0)).save(any(Employee.class));
  }

  @Test
  public void shouldDeleteEmployee() throws Exception {
    // given
    given(employeeRepository.findById(employeeId)).willReturn(Optional.of(new Employee(employeeId)));

    // when
    companyService.deleteEmployee(employeeId);

    // then
    verify(policyRepository).deleteEmployee(employeeId);
    verify(employeeRepository).delete(any(Employee.class));
  }

  @Test
  public void shouldNotDeleteEmployee() throws Exception {
    // when
    companyService.deleteEmployee(employeeId);

    // then
    verify(employeeRepository, times(0)).delete(any(Employee.class));
  }
}