package com.demo.testamc.controller;
import com.demo.testamc.dto.LoginRequest;
import com.demo.testamc.dto.SignupResponse;
import com.demo.testamc.dto.SignupRequest;
import com.demo.testamc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        // TODO: Add user registration logic (save user, hash password, etc.)
        // For demonstration, just return a success message
        return ResponseEntity.ok("User registered successfully: " + signupRequest.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
