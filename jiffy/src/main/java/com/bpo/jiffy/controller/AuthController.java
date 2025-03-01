package com.bpo.jiffy.controller;

import com.bpo.jiffy.dto.LoginRequest;
import com.bpo.jiffy.dto.SignupRequest;
import com.bpo.jiffy.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.registerUser(request, request.isScrumMaster());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.loginUser(request);
    }


}
