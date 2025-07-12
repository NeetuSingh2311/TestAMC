package com.demo.testamc.controller;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holding")
public class HoldingController {
    private final HoldingService holdingService;

    @Autowired
    public HoldingController(HoldingService holdingService) {
        this.holdingService = holdingService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<List<HoldingDetailsResponseDTO>> getHoldings(@RequestParam("email") String email, @RequestParam("category") String category) {
        return ResponseEntity.ok(holdingService.getHoldingDetails(email, category));
    }
}
