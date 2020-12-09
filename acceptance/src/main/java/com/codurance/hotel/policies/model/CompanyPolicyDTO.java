package com.codurance.hotel.policies.model;

import com.codurance.hotel.common.model.PolicyDTO;

public class CompanyPolicyDTO extends PolicyDTO {

  private Integer companyId;

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
}
