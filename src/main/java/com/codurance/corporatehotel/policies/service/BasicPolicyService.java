package com.codurance.corporatehotel.policies.service;

import com.codurance.corporatehotel.common.model.Policy;
import com.codurance.corporatehotel.common.model.RoomType;
import com.codurance.corporatehotel.common.model.RoomTypes;
import com.codurance.corporatehotel.common.repository.RoomTypeRepository;
import com.codurance.corporatehotel.companies.model.Employee;
import com.codurance.corporatehotel.companies.repository.CompanyRepository;
import com.codurance.corporatehotel.companies.repository.EmployeeRepository;
import com.codurance.corporatehotel.policies.model.CompanyPolicy;
import com.codurance.corporatehotel.policies.model.EmployeePolicy;
import com.codurance.corporatehotel.policies.repository.CompanyPolicyRepository;
import com.codurance.corporatehotel.policies.repository.EmployeePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasicPolicyService implements PolicyService {

    @Autowired
    private EmployeePolicyRepository employeePolicyRepository;

    @Autowired
    private CompanyPolicyRepository companyPolicyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public void setEmployeePolicy(Integer employeeId, final List<RoomTypes> roomTypes) {

        List<RoomType> roomTypesToPersist = roomTypes.stream()
                .map(roomTypeRepository::findByRoomType)
                .collect(Collectors.toList());

        this.employeePolicyRepository.findByEmployeeId(employeeId).ifPresentOrElse(employeePolicy -> {
            employeePolicy.setRoomTypes(roomTypesToPersist);
        }, () -> {
            EmployeePolicy employeePolicy = new EmployeePolicy();
            employeePolicy.setEmployee(employeeRepository.findById(employeeId).get());
            employeePolicy.setRoomTypes(roomTypesToPersist);

            this.employeePolicyRepository.save(employeePolicy);
        });
    }

    public void setCompanyPolicy(Integer companyId, List<RoomTypes> roomTypes) {

        List<RoomType> roomTypesToPersist = roomTypes.stream()
                .map(roomTypeRepository::findByRoomType)
                .collect(Collectors.toList());

        this.companyPolicyRepository.findByCompanyId(companyId).ifPresentOrElse(companyPolicy -> {
            companyPolicy.setRoomTypes(roomTypesToPersist);
        }, () -> {
            CompanyPolicy companyPolicy = new CompanyPolicy();
            companyPolicy.setCompany(companyRepository.findById(companyId).get());
            companyPolicy.setRoomTypes(roomTypesToPersist);

            this.companyPolicyRepository.save(companyPolicy);
        });
    }

    public boolean isBookingAllowed(Integer employeeId, RoomTypes roomType) {
        Optional<EmployeePolicy> optionalEmployeePolicy = this.employeePolicyRepository.findByEmployeeId(employeeId);

        if (optionalEmployeePolicy.isPresent()) {
            return this.isPolicySufficient(optionalEmployeePolicy.get(), roomType);
        } else {
            Optional<CompanyPolicy> optionalCompanyPolicy = this.findCompanyPolicy(employeeId);
            if (optionalCompanyPolicy.isPresent()) {
                return this.isPolicySufficient(optionalCompanyPolicy.get(), roomType);
            }
        }

        return true;
    }

    private boolean isPolicySufficient(Policy policy, RoomTypes roomType) {
        return policy != null
                && policy.getRoomTypes()
                        .stream()
                        .anyMatch(roomTypes -> roomTypes.getRoomType().equals(roomType));
    }

    private Optional<CompanyPolicy> findCompanyPolicy(Integer employeeId) {
        Optional<Employee> employee = this.employeeRepository.findById(employeeId);

        if (employee.isPresent()) {
            return this.companyPolicyRepository.findByCompanyId(employee.get().getCompany().getId());
        }

        return Optional.ofNullable(null);
    }
}
