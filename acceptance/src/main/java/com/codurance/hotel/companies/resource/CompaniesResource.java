package com.codurance.hotel.companies.resource;

import com.codurance.hotel.companies.model.Employee;
import com.codurance.hotel.companies.service.CompanyService;
import com.codurance.hotel.hotels.model.Hotel;
import com.codurance.hotel.hotels.model.Room;
import com.codurance.hotel.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompaniesResource {

  private final CompanyService companyService;

  @Autowired
  public CompaniesResource(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping("/addemployee")
  public ResponseEntity<Employee> addEmployee(@RequestBody @Valid Employee employee) {
    Employee savedEmployee = companyService.addEmployee(employee.getCompany().getId(), employee.getId());

    return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  @DeleteMapping("/deleteemployee")
  public void deleteEmployee(@PathVariable Integer employeeId) {
    companyService.deleteEmployee(employeeId);
  }
}
