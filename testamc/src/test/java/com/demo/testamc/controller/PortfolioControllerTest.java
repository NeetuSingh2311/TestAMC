package com.demo.testamc.controller;

import com.demo.testamc.dto.PortfolioSummary;
import com.demo.testamc.service.PortfolioService;
import com.demo.testamc.service.impl.CustomUserDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PortfolioControllerTest {

    @Mock
    private PortfolioService portfolioService;

    @InjectMocks
    private PortfolioController portfolioController;

    @Mock
    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolioSummary_WithEmailParam() {
        String email = "user@example.com";
        PortfolioSummary summary = new PortfolioSummary();
        // Optionally set fields on summary

        when(portfolioService.getPortfolioSummary(email)).thenReturn(summary);

        ResponseEntity<PortfolioSummary> response = portfolioController.getPortfolioSummary(customUserDetails, email);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(summary, response.getBody());
        verify(portfolioService, times(1)).getPortfolioSummary(email);
        verifyNoMoreInteractions(portfolioService);
    }

    @Test
    void testGetPortfolioSummary_WithoutEmailParam() {
        String userEmail = "user2@example.com";
        PortfolioSummary summary = new PortfolioSummary();
        // Optionally set fields on summary

        when(customUserDetails.getUsername()).thenReturn(userEmail);
        when(portfolioService.getPortfolioSummary(userEmail)).thenReturn(summary);

        ResponseEntity<PortfolioSummary> response = portfolioController.getPortfolioSummary(customUserDetails, null);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(summary, response.getBody());
        verify(portfolioService, times(1)).getPortfolioSummary(userEmail);
        verifyNoMoreInteractions(portfolioService);
    }

    @Test
    void testGetPortfolioSummary_WithEmptyEmailParam() {
        String userEmail = "user3@example.com";
        PortfolioSummary summary = new PortfolioSummary();

        when(customUserDetails.getUsername()).thenReturn(userEmail);
        when(portfolioService.getPortfolioSummary(userEmail)).thenReturn(summary);

        ResponseEntity<PortfolioSummary> response = portfolioController.getPortfolioSummary(customUserDetails, "");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(summary, response.getBody());
        verify(portfolioService, times(1)).getPortfolioSummary(userEmail);
        verifyNoMoreInteractions(portfolioService);
    }
}
