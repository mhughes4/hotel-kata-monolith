//package com.codurance.hotel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.codurance.hotel.bookings.exception.InsufficientPolicyException;
import com.codurance.hotel.bookings.model.Booking;
import com.codurance.hotel.bookings.repository.BookingRepository;
import com.codurance.hotel.bookings.service.BasicBookingService;
import com.codurance.hotel.bookings.service.BookingService;
import com.codurance.hotel.common.model.RoomTypes;
import com.codurance.hotel.companies.repository.CompanyRepository;
import com.codurance.hotel.companies.repository.EmployeeRepository;
import com.codurance.hotel.companies.service.BasicCompanyService;
import com.codurance.hotel.companies.service.CompanyService;
import com.codurance.hotel.companies.model.Employee;
import com.codurance.hotel.hotels.model.Hotel;
import com.codurance.hotel.hotels.repository.HotelRepository;
import com.codurance.hotel.hotels.repository.RoomRepository;
import com.codurance.hotel.hotels.service.BasicHotelService;
import com.codurance.hotel.hotels.service.HotelService;
import com.codurance.hotel.policies.repository.EmployeePolicyRepository;
import com.codurance.hotel.policies.service.BasicPolicyService;
import com.codurance.hotel.policies.service.PolicyService;
import com.codurance.hotel.utils.IdGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

//@SpringBootTest
//@ActiveProfiles("test")
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
//public class AcceptanceTest {
//
//  private IdGenerator idGenerator;
//  @Autowired
//  private HotelService hotelService;
//  @Autowired
//  private CompanyService companyService;
//  @Autowired
//  private BookingService bookingService;
//  @Autowired
//  private PolicyService policyService;
//
//  @Autowired
//  private HotelRepository hotelRepository;
//  @Autowired
//  private RoomRepository roomRepository;
//  @Autowired
//  private CompanyRepository companyRepository;
//  @Autowired
//  private EmployeeRepository employeeRepository;
//  @Autowired
//  private EmployeePolicyRepository policyRepository;
//  @Autowired
//  private BookingRepository bookingRepository;
//
//  Integer hotelId = 1;
//  Integer employeeId = 2;
//  Integer roomNumber = 1;
//  Integer companyId = 10;
//  List<RoomTypes> roomTypes;
//  RoomTypes standardRoomType = RoomTypes.STANDARD;
//  RoomTypes masterRoomType = RoomTypes.MASTER_SUITE;
//  LocalDateTime checkIn = LocalDateTime
//      .parse("2020-01-01 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//  LocalDateTime checkOut = LocalDateTime
//      .parse("2020-01-10 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//  String hotelName = "Mariott - London";
//
//  @BeforeEach
//  private void init() {
//    idGenerator = new IdGenerator();
    // hotelRepository = new HotelRepository();
    // roomRepository = new RoomRepository();
    // policyRepository = new PolicyRepository();
    // hotelService = new BasicHotelService(hotelRepository, roomRepository);
    // companyRepository = new CompanyRepository();
    // employeeRepository = new EmployeeRepository();
    // bookingRepository = new BookingRepository();
    // companyService = new BasicCompanyService(companyRepository, employeeRepository,
    //     policyRepository);
    // policyService = new BasicPolicyService(policyRepository, employeeRepository);
    // bookingService = new BasicBookingService(idGenerator,
    //     bookingRepository, policyService, hotelService);

//    roomTypes = new ArrayList<>();
//  }

//  @Test
//  public void shouldBookingContainAllExpectedData() throws Exception {
//    // given
//    hotelService.addHotel(hotelId, hotelName);
//    hotelService.setRoom(hotelId, roomNumber, standardRoomType);
//
//    // when
//    Booking booking = bookingService.book(employeeId, hotelId, standardRoomType, checkIn, checkOut);
//
//    // then
//    assertThat(booking.getEmployeeId()).isEqualTo(employeeId);
//    assertThat(booking.getHotelId()).isEqualTo(hotelId);
//    assertThat(booking.getRoomType()).isEqualTo(standardRoomType);
//    assertThat(booking.getCheckIn()).isEqualTo(checkIn);
//    assertThat(booking.getCheckOut()).isEqualTo(checkOut);
//  }

//  @Test
//  public void shouldAllowBooking_givenEmployeeWasDeleted() throws Exception {
//    // Given
//    Employee employee = companyService.addEmployee(companyId, employeeId);
//    roomTypes.add(standardRoomType);
//    policyService.setEmployeePolicy(employeeId, roomTypes);
//
//    // When
//    companyService.deleteEmployee(employee.getId());
//
//    // Then
//    assertThat(policyService.isBookingAllowed(employeeId, standardRoomType)).isTrue();
//  }

  // @Test
  // public void shouldFindHotelAfterAdding() throws Exception {
  //   // given
  //   hotelService.addHotel(hotelId, hotelName);

  //   // when
  //   Hotel hotel = hotelService.findHotelById(hotelId);

  //   // then
  //   assertThat(hotel.getId()).isEqualTo(hotelId);
  //   assertThat(hotel.getName()).isEqualTo(hotelName);
  // }

  // @Test
  // public void shouldBookRoom_givenSufficientCompanyPolicy() throws Exception {
  //   // Given
  //   hotelService.addHotel(hotelId, hotelName);
  //   hotelService.setRoom(hotelId, roomNumber, masterRoomType);

  //   companyService.addEmployee(companyId, employeeId);

  //   roomTypes.add(masterRoomType);
  //   policyService.setCompanyPolicy(companyId, roomTypes);

  //   // When
  //   Booking booking = bookingService
  //       .book(employeeId, hotelId, masterRoomType, checkIn, checkOut);

  //   // Then
  //   assertThat(booking).isNotNull();
  // }

  // @Test
  // public void shouldFailBooking_givenInsufficientCompanyPolicy() throws Exception {
  //   // Given
  //   hotelService.addHotel(hotelId, hotelName);
  //   hotelService.setRoom(hotelId, roomNumber, masterRoomType);

  //   companyService.addEmployee(companyId, employeeId);

  //   roomTypes.add(standardRoomType);
  //   policyService.setCompanyPolicy(companyId, roomTypes);

  //   // When
  //   // Then
  //   assertThatThrownBy(
  //       () -> bookingService.book(employeeId, hotelId, masterRoomType, checkIn, checkOut))
  //       .isInstanceOf(InsufficientPolicyException.class);
  // }

  // @Test
  // public void shouldFailBooking_givenInsufficientEmployeePolicy() throws Exception {
  //   // Given
  //   hotelService.addHotel(hotelId, hotelName);
  //   hotelService.setRoom(hotelId, roomNumber, masterRoomType);

  //   companyService.addEmployee(companyId, employeeId);

  //   roomTypes.add(standardRoomType);
  //   roomTypes.add(masterRoomType);
  //   policyService.setCompanyPolicy(companyId, roomTypes);

  //   List<RoomTypes> employeeRoomTypes = new ArrayList<>();
  //   employeeRoomTypes.add(RoomTypes.STANDARD);
  //   policyService.setEmployeePolicy(employeeId, employeeRoomTypes);

  //   // when
  //   // Then
  //   assertThatThrownBy(
  //       () -> bookingService.book(employeeId, hotelId, masterRoomType, checkIn, checkOut))
  //       .isInstanceOf(InsufficientPolicyException.class);
  // }
//}
