package com.bpo.jiffy.service;

import com.bpo.jiffy.dto.ScrumMasterEmployeePair;
import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.ScrumMasterEmployeeRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScrumMasterEmployeeService {

    private final ScrumMasterEmployeeRepository scrumMasterEmployeeRepository;
    private final ScrumMasterRepository scrumMasterRepository;

    // Fetch all ScrumMaster-Employee pairs grouped by project
    public List<ScrumMasterEmployeePair> getAllScrumMasterEmployeePairs() {
        List<ScrumMasterEmployeePair> pairs = new ArrayList<>();

        // Fetch all ScrumMasters
        List<ScrumMaster> scrumMasters = scrumMasterRepository.findAll();

        // Create a map to group ScrumMasters by project
        Map<String, ScrumMaster> scrumMasterByProject = new HashMap<>();
        for (ScrumMaster scrumMaster : scrumMasters) {
            scrumMasterByProject.put(scrumMaster.getProject(), scrumMaster);
        }

        // Fetch all Employees
        List<Employee> employees = scrumMasterEmployeeRepository.findAll();

        // Group employees by their project
        Map<String, ScrumMasterEmployeePair> projectMap = new HashMap<>();

        for (Employee employee : employees) {
            // Get the project of the employee
            String project = employee.getProject();

            // Find the ScrumMaster for this project
            ScrumMaster scrumMaster = scrumMasterByProject.get(project);

            if (scrumMaster != null) {
                // Create a unique key for the ScrumMaster and project
                String key = scrumMaster.getName() + "-" + project;

                // If the key doesn't exist in the map, create a new pair
                ScrumMasterEmployeePair pair = projectMap.computeIfAbsent(key, k -> {
                    ScrumMasterEmployeePair newPair = new ScrumMasterEmployeePair();
                    newPair.setScrumMasterName(scrumMaster.getName());
                    newPair.setScrumMasterEmail(scrumMaster.getEmail());
                    newPair.setProject(project);
                    newPair.setEmployeeNames(new ArrayList<>()); // Initialize employeeNames list
                    newPair.setEmployeeEmails(new ArrayList<>()); // Initialize employeeEmails list
                    newPair.setDepartments(new ArrayList<>()); // Initialize departments list
                    return newPair;
                });

                // Add employee details to the respective lists
                pair.getEmployeeNames().add(employee.getName());
                pair.getEmployeeEmails().add(employee.getEmail());
                pair.getDepartments().add(employee.getDepartment());
            }
        }

        // Convert the map values to a list
        pairs.addAll(projectMap.values());

        return pairs;
    }
}