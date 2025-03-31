package com.bpo.jiffy.dto;

import lombok.Data;
import java.util.List;

@Data
public class ScrumMasterEmployeePair {
    private Long scrumMasterId; // Add this field
    private String scrumMasterName;
    private String scrumMasterEmail;
    private String project;
    private List<String> employeeNames;
    private List<String> employeeEmails;
    private List<String> departments;
}