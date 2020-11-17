package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.Policy;
import com.codurance.corporatehotel.companies.model.Employee;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeePolicy extends Policy {

  @ManyToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
}
