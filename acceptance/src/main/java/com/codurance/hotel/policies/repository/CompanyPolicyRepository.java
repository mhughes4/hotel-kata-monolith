package com.codurance.hotel.policies.repository;

import com.codurance.hotel.policies.model.CompanyPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyPolicyRepository extends CrudRepository<CompanyPolicy, Integer> {

    Optional<CompanyPolicy> findByCompanyId(Integer companyId);
}
