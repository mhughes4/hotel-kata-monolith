package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.PolicyDTO;

public class CompanyPolicyDTO extends PolicyDTO {

  private Integer companyId;

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
}
