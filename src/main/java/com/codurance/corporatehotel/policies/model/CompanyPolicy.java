package com.codurance.corporatehotel.policies.model;

import com.codurance.corporatehotel.common.model.Policy;
import com.codurance.corporatehotel.companies.model.Company;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CompanyPolicy extends Policy {

  @ManyToOne
  @JoinColumn(name = "company_id")
  private Company company;

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
