package com.bpo.jiffy.service;

import com.bpo.jiffy.dto.LoginRequest;
import com.bpo.jiffy.dto.SignupRequest;
import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.EmployeeRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final EmployeeRepository employeeRepository;
    private final ScrumMasterRepository scrumMasterRepository;

    public String registerUser(SignupRequest request, boolean isScrumMaster) {
        if (isScrumMaster) {
            // Handle ScrumMaster signup
            ScrumMaster scrumMaster = new ScrumMaster();
            scrumMaster.setName(request.getName());
            scrumMaster.setEmail(request.getEmail());
            scrumMaster.setPassword(request.getPassword());
            scrumMaster.setProject(request.getProject());
            scrumMasterRepository.save(scrumMaster);
        } else {
            // Handle Employee signup
            // Find the ScrumMaster by project
            ScrumMaster scrumMaster = scrumMasterRepository.findByProject(request.getProject())
                    .orElseThrow(() -> new RuntimeException("ScrumMaster not found for project: " + request.getProject()));

            // Create and save the employee
            Employee employee = new Employee();
            employee.setName(request.getName());
            employee.setEmail(request.getEmail());
            employee.setPassword(request.getPassword());
            employee.setDepartment(request.getDepartment());
            employee.setProject(request.getProject());
            employee.setScrumMaster(scrumMaster);

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