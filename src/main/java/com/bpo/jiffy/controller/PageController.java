package com.bpo.jiffy.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/signup")
    public String signupPage() {
        return "signUp";  // Refers to signup.html inside the templates folder
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Refers to signup.html inside the templates folder
    }

    @GetMapping("/scrum-master/meetings")
    public String scrumMasterMeetingsPage() {
        return "scrumMasterMeetings";  // Refers to scrumMasterMeetings.html
    }

    @GetMapping("/employee/meetings")
    public String employeeMeetingsPage() {
        return "employeeMeetings";  // Refers to employeeMeetings.html
    }

    @GetMapping("/employee-report")
    public String employeePage() {
        return "employee"; // Serves employee.html
    }

    @GetMapping("/scrum-master-report")
    public String scrumMasterPage() {
        return "scrum-master"; // Serves scrum-master.html
    }
}


