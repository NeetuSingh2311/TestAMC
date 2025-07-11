package com.demo.testamc.controller;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.models.User;
import com.demo.testamc.service.HoldingService;
import com.demo.testamc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/holding")
public class HoldingController {
    private HoldingService holdingService;
    @Autowired
    public void setHoldingService(HoldingService holdingService) {
        this.holdingService = holdingService;
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping()
    public ResponseEntity<List<HoldingDetailsResponseDTO>> getHoldings(@RequestParam("email") String email, @RequestParam("category") String category) {
        return ResponseEntity.ok(holdingService.getEquity(email, category));
    }
}
