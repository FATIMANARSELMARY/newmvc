package com.bpo.jiffy.controller;

import com.bpo.jiffy.dto.ScrumMasterEmployeePair;
import com.bpo.jiffy.service.ScrumMasterEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/scrummaster-employee")
@RequiredArgsConstructor
public class ScrumMasterEmployeeController {

    private final ScrumMasterEmployeeService scrumMasterEmployeeService;

    // Endpoint to fetch all ScrumMaster-Employee pairs grouped by project
    @GetMapping("/pairs")
    public List<ScrumMasterEmployeePair> getAllScrumMasterEmployeePairs() {
        return scrumMasterEmployeeService.getAllScrumMasterEmployeePairs();
    }
}