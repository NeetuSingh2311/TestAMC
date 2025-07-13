package com.demo.testamc.controller;

import com.demo.testamc.dto.LoginRequest;
import com.demo.testamc.dto.LoginResponse;
import com.demo.testamc.dto.SignupRequest;
import com.demo.testamc.models.User;
import com.demo.testamc.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup_ReturnsUser() {
        SignupRequest signupRequest = SignupRequest.builder()
                .fullName("John Doe")
                .address("123 Main St")
                .email("john@example.com")
                .contactNumber("9876543210")
                .pan("ABCDE1234F")
                .password("password123")
                .build();

        User user = User.builder()
                .userId(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .address("123 Main St")
                .contactNumber("9876543210")
                .pan("ABCDE1234F")
                .password("password123")
                .role("USER")
                .build();

        when(authService.signup(signupRequest)).thenReturn(user);

        ResponseEntity<User> response = authController.signup(signupRequest);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("John Doe", response.getBody().getFullName());
        assertEquals("john@example.com", response.getBody().getEmail());
        verify(authService, times(1)).signup(signupRequest);
    }

    @Test
    void testLogin_ReturnsLoginResponse() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("john@example.com");
        loginRequest.setPassword("password123");

        LoginResponse loginResponse = LoginResponse.builder()
                .email("john@example.com")
                .name("John Doe")
                .role("USER")
                .build();

        when(authService.login(loginRequest)).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> response = authController.login(loginRequest);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("john@example.com", response.getBody().getEmail());
        assertEquals("John Doe", response.getBody().getName());
        assertEquals("USER", response.getBody().getRole());
        verify(authService, times(1)).login(loginRequest);
    }
}
