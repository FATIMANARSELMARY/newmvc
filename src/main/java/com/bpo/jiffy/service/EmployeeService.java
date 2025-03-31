package com.bpo.jiffy.service;

import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.EmployeeRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ScrumMasterRepository scrumMasterRepository;

    public Employee saveEmployee(Employee employee) {
        // Fetch the ScrumMaster
        ScrumMaster scrumMaster = scrumMasterRepository.findById(employee.getScrumMaster().getId())
                .orElseThrow(() -> new RuntimeException("ScrumMaster not found"));

        // Validate that the ScrumMaster's project matches the employee's project
        if (!scrumMaster.getProject().equals(employee.getProject())) {
            throw new RuntimeException("ScrumMaster's project does not match the employee's project");
        }

        // Save the employee
        return employeeRepository.save(employee);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}