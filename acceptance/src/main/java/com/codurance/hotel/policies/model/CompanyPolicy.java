package com.codurance.hotel.policies.model;

import com.codurance.hotel.common.model.Policy;
import com.codurance.hotel.companies.model.Company;

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
