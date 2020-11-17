package com.codurance.corporatehotel.companies.resource;

import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.service.CompanyService;
import com.codurance.corporatehotel.hotels.model.Hotel;
import com.codurance.corporatehotel.hotels.model.Room;
import com.codurance.corporatehotel.hotels.service.HotelService;
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