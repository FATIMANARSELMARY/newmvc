package com.bpo.jiffy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bpo.jiffy.entity.Employee;
import com.bpo.jiffy.entity.ScrumMaster;
import com.bpo.jiffy.repository.EmployeeRepository;
import com.bpo.jiffy.repository.ScrumMasterRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ScrumMasterRepository scrumMasterRepository;

    @GetMapping("/check-user-type/{userId}")
    public ResponseEntity<String> checkUserType(@PathVariable Long userId) {
        Employee employee = employeeRepository.findById(userId).orElse(null);
        if (employee != null) {
            return ResponseEntity.ok("EMPLOYEE");
        }

        ScrumMaster scrumMaster = scrumMasterRepository.findById(userId).orElse(null);
        if (scrumMaster != null) {
            return ResponseEntity.ok("SCRUM_MASTER");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
