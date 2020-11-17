package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.PolicyDTO;

public class EmployeePolicyDTO extends PolicyDTO {

  private Integer employeeId;

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }
}
