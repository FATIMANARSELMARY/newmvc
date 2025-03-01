package com.bpo.jiffy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
    @JsonProperty("isScrumMaster")
    private boolean isScrumMaster; // ✅ Matches JSON key exactly
}

