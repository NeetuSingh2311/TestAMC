package com.demo.testamc.service;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.dto.PortfolioSummary;
import com.demo.testamc.models.Portfolio;

public interface PortfolioService {
    PortfolioSummary getPortfolioSummary(String email);
    Portfolio getPortfolioByUserId(Long userId);

}
