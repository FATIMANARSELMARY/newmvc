package com.bpo.jiffy.service;

import com.bpo.jiffy.dto.LoginRequest;
import com.bpo.jiffy.dto.SignupRequest;
import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.EmployeeRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final ScrumMasterRepository scrumMasterRepository;

    public String registerUser(SignupRequest request, boolean isScrumMaster) {
        if (isScrumMaster) {
            ScrumMaster scrumMaster = new ScrumMaster();
            scrumMaster.setName(request.getName());
            scrumMaster.setEmail(request.getEmail());
            scrumMaster.setPassword(request.getPassword());
            scrumMaster.setProject("Default Project");
            scrumMasterRepository.save(scrumMaster);
        } else {
            Employee employee = new Employee();
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employee.setPassword(request.getPassword());
            employee.setDepartment("Default Department");
            employeeRepository.save(employee);
        }
        return "User Registered Successfully";
    }

    public String loginUser(LoginRequest request) {
        Optional<Employee> employee = employeeRepository.findByEmail(request.getEmail());
        Optional<ScrumMaster> scrumMaster = scrumMasterRepository.findByEmail(request.getEmail());

        if (employee.isPresent() && employee.get().getPassword().equals(request.getPassword())) {
            return "Employee login successful";
        } 
        if (scrumMaster.isPresent() && scrumMaster.get().getPassword().equals(request.getPassword())) {
            return "Scrum Master login successful";
        } 
        return "Invalid credentials";
    }
}
