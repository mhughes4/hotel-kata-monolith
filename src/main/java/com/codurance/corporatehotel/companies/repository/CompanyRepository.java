package com.codurance.corporatehotel.companies.repository;

import com.codurance.corporatehotel.companies.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {

}
