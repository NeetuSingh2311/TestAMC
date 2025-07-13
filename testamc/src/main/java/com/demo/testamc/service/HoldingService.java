package com.demo.testamc.service;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import java.util.List;

public interface HoldingService {
    List<HoldingDetailsResponseDTO> getHoldingDetails(String email, String category);
}
