package com.ps014.auth.controller;

import com.ps014.auth.entity.User;
import com.ps014.auth.security.JwtService;
import com.ps014.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            AuthService authService,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        return ResponseEntity.ok(
                authService.register(user)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        User user;

        try {
            user = authService.findByEmail(request.getEmail());
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Invalid email or password"));
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            return ResponseEntity
                    .status(401)
                    .body(Map.of("message", "Invalid email or password"));
        }

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(
            Authentication authentication) {

        return ResponseEntity.ok(
                Map.of(
                        "message", "JWT authentication successful",
                        "email", authentication.getName()
                )
        );
    }
}