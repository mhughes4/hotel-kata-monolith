package com.codurance.hotel.policies.resource;

import com.codurance.hotel.policies.model.CompanyPolicyDTO;
import com.codurance.hotel.policies.model.EmployeePolicy;
import com.codurance.hotel.policies.model.EmployeePolicyDTO;
import com.codurance.hotel.policies.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/policies")
public class PolicyResource {

  private final PolicyService policyService;

  @Autowired
  public PolicyResource(PolicyService policyService) {
    this.policyService = policyService;
  }

  @PostMapping("/employee")
  public void setEmployeePolicy(@RequestBody @Valid EmployeePolicyDTO employeePolicy) {
    policyService.setEmployeePolicy(employeePolicy.getEmployeeId(), employeePolicy.getRoomTypes());
  }

  @PostMapping("/company")
  public void setCompanyPolicy(@RequestBody @Valid CompanyPolicyDTO companyPolicyDTO) {
    policyService.setCompanyPolicy(companyPolicyDTO.getCompanyId(), companyPolicyDTO.getRoomTypes());
  }

}
