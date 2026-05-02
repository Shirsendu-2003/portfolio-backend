package com.portfolio.controller;

import com.portfolio.dto.LoginRequest;
import com.portfolio.model.User;
import com.portfolio.security.JwtUtil;
import com.portfolio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        User user = authService.login(request.getEmail(), request.getPassword());
        return jwtUtil.generateToken(user.getEmail());
    }
}
