package com.codurance.hotel.policies.service;

import com.codurance.hotel.common.model.RoomType;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.common.repository.RoomTypeRepository;
import com.codurance.hotel.companies.model.Company;
import com.codurance.hotel.companies.model.Employee;
import com.codurance.hotel.companies.repository.CompanyRepository;
import com.codurance.hotel.companies.repository.EmployeeRepository;
import com.codurance.hotel.policies.model.CompanyPolicy;
import com.codurance.hotel.policies.model.EmployeePolicy;
import com.codurance.hotel.policies.repository.CompanyPolicyRepository;
import com.codurance.hotel.policies.repository.EmployeePolicyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BasicPolicyServiceTest {

  @InjectMocks
  private BasicPolicyService policyService;

  @Mock
  private EmployeePolicyRepository employeePolicyRepositoryStub;

  @Mock
  private CompanyPolicyRepository companyPolicyRepositoryStub;

  @Mock
  private EmployeeRepository employeeRepositoryStub;

  @Mock
  private CompanyRepository companyRepository;

  @Mock
  private RoomTypeRepository roomTypeRepository;

  private Integer employeeId;
  private Integer companyId;
  private List<RoomTypes> roomTypes;

  @BeforeEach
  private void init() {
    MockitoAnnotations.initMocks(this);
    employeeId = 10;
    companyId = 101;
    roomTypes = new ArrayList<>();
  }

  @Test
  public void shouldCreateEmployeePolicyForSingleRoomType() throws Exception {
    // given
    roomTypes.add(RoomTypes.STANDARD);
    given(roomTypeRepository.findByRoomType(any(RoomTypes.class))).willReturn(new RoomType());
    given(employeeRepositoryStub.findById(employeeId)).willReturn(Optional.of(new Employee()));

    // when
    policyService.setEmployeePolicy(employeeId, roomTypes);

    // then
    verify(employeePolicyRepositoryStub).save(any(EmployeePolicy.class));
  }

  @Test
  public void shouldCreateEmployeePoliciesForManyRoomTypes() throws Exception {
    // given
    roomTypes.add(RoomTypes.STANDARD);
    roomTypes.add(RoomTypes.MASTER_SUITE);
    given(roomTypeRepository.findByRoomType(any(RoomTypes.class))).willReturn(new RoomType());
    given(employeeRepositoryStub.findById(employeeId)).willReturn(Optional.of(new Employee()));

    // when
    policyService.setEmployeePolicy(employeeId, roomTypes);

    // then
    verify(employeePolicyRepositoryStub).save(any(EmployeePolicy.class));
  }

  @Test
  public void shouldCreateCompanyPolicyForSingleRoomType() throws Exception {
    // given
    roomTypes.add(RoomTypes.STANDARD);
    given(roomTypeRepository.findByRoomType(any(RoomTypes.class))).willReturn(new RoomType());
    given(companyRepository.findById(companyId)).willReturn(Optional.of(new Company()));

    // when
    policyService.setCompanyPolicy(companyId, roomTypes);

    // then
    verify(companyPolicyRepositoryStub).save(any(CompanyPolicy.class));
  }

  @Test
  public void shouldCreateCompanyPoliciesForMultipleRoomTypes() throws Exception {
    // given
    roomTypes.add(RoomTypes.STANDARD);
    roomTypes.add(RoomTypes.MASTER_SUITE);
    given(roomTypeRepository.findByRoomType(any(RoomTypes.class))).willReturn(new RoomType());
    given(companyRepository.findById(companyId)).willReturn(Optional.of(new Company()));

    // when
    policyService.setCompanyPolicy(companyId, roomTypes);

    // then
    verify(companyPolicyRepositoryStub).save(any(CompanyPolicy.class));
  }

  @Test
  public void shouldUpdateEmployeePolicyGivenItAlreadyExists() throws Exception {
    // when
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.of(new EmployeePolicy()));
    roomTypes.add(RoomTypes.STANDARD);
    given(roomTypeRepository.findByRoomType(any(RoomTypes.class))).willReturn(new RoomType());

    // when
    policyService.setEmployeePolicy(employeeId, roomTypes);

    // then
    verify(employeePolicyRepositoryStub, times(0)).save(any(EmployeePolicy.class));
  }

  @Test
  public void shouldUpdateCompanyPolicy_givenItAlreadyExists() throws Exception {
    // when
    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.of(new CompanyPolicy()));
    roomTypes.add(RoomTypes.STANDARD);

    // when
    policyService.setCompanyPolicy(companyId, roomTypes);

    // then
    verify(employeePolicyRepositoryStub, times(0)).save(any(EmployeePolicy.class));
  }

  @Test
  public void shouldAllowBooking_givenNoPoliciesExist() throws Exception {
    // when
    RoomTypes roomType = RoomTypes.STANDARD;
    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.empty());
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.empty());

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isTrue();
  }

  @Test
  public void shouldAllowBooking_givenEmployeePolicyExists() throws Exception {
    // given
    RoomTypes roomType = RoomTypes.STANDARD;

    Employee employee = new Employee();
    employee.setId(employeeId);

    EmployeePolicy employeePolicy = new EmployeePolicy();
    employeePolicy.setEmployee(employee);
    employeePolicy.addRoomType(RoomTypes.STANDARD);

    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.empty());
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.of(employeePolicy));

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isTrue();
  }

  @Test
  public void shouldAllowBooking_givenCompanyPolicyExists() throws Exception {
    // given
    RoomTypes roomType = RoomTypes.STANDARD;

    Company company = new Company();
    company.setId(companyId);

    CompanyPolicy companyPolicy = new CompanyPolicy();
    companyPolicy.setCompany(company);
    companyPolicy.addRoomType(RoomTypes.STANDARD);

    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.of(companyPolicy));
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.empty());

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isTrue();
  }

  @Test
  public void shouldNotAllowBooking_givenInsufficientEmployeePolicyExists() throws Exception {
    // given
    RoomTypes roomType = RoomTypes.MASTER_SUITE;


    Employee employee = new Employee();
    employee.setId(employeeId);

    EmployeePolicy employeePolicy = new EmployeePolicy();
    employeePolicy.setEmployee(employee);
    employeePolicy.addRoomType(RoomTypes.STANDARD);

    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.empty());
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.of(employeePolicy));

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isFalse();

  }

  @Test
  public void shouldNotAllowBooking_givenInsufficientCompanyPolicyExists() throws Exception {
    // when
    RoomTypes roomType = RoomTypes.MASTER_SUITE;

    Company company = new Company();
    company.setId(companyId);

    Employee employee = new Employee();
    employee.setId(employeeId);
    employee.setCompany(company);

    CompanyPolicy companyPolicy = new CompanyPolicy();
    companyPolicy.setCompany(company);
    companyPolicy.addRoomType(RoomTypes.STANDARD);

    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.of(companyPolicy));
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.empty());
    given(employeeRepositoryStub.findById(employeeId)).willReturn(Optional.of(employee));

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isFalse();
  }

  @Test
  public void shouldEmployeePolicyAllowButCompanyDoesNotAllow() throws Exception {
    // given
    RoomTypes roomType = RoomTypes.MASTER_SUITE;

    Company company = new Company();
    company.setId(companyId);

    Employee employee = new Employee();
    employee.setId(employeeId);
    employee.setCompany(company);

    CompanyPolicy companyPolicy = new CompanyPolicy();
    companyPolicy.setCompany(company);
    companyPolicy.addRoomType(RoomTypes.STANDARD);

    EmployeePolicy employeePolicy = new EmployeePolicy();
    employeePolicy.setEmployee(employee);
    employeePolicy.addRoomType(RoomTypes.MASTER_SUITE);

    given(companyPolicyRepositoryStub.findByCompanyId(companyId)).willReturn(Optional.of(companyPolicy));
    given(employeePolicyRepositoryStub.findByEmployeeId(employeeId)).willReturn(Optional.of(employeePolicy));
    given(employeeRepositoryStub.findById(employeeId)).willReturn(Optional.of(employee));

    // when
    boolean isBookingAllowed = policyService.isBookingAllowed(employeeId, roomType);

    // then
    assertThat(isBookingAllowed).isTrue();
  }

}
