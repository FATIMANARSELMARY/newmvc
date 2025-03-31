package com.bpo.jiffy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;

    @JsonProperty("isScrumMaster")
    private boolean scrumMaster; // Indicates if the user is a ScrumMaster

    private String project; // Project for ScrumMaster or Employee
    private String department; // Department for Employee
}