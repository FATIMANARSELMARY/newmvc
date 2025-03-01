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
}


