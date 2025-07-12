package com.demo.testamc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HoldingDetailsResponseDTO {
    private String marketValue;
    private String quantity;
    private String instrumentName;
    private String description;
    private String ticker;

}
