package com.demo.testamc.controller;

import com.demo.testamc.dto.PortfolioSummary;
import com.demo.testamc.service.PortfolioService;
import com.demo.testamc.service.impl.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/portfolio")
@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/summary")
    public ResponseEntity<PortfolioSummary> getPortfolioSummary(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                                @RequestParam(required = false) String email) {
        String userEmail = (email == null || email.isEmpty()) ? userDetails.getUsername():email;
        return ResponseEntity.ok(portfolioService.getPortfolioSummary(userEmail));
    }
}
