package com.demo.testamc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioSummary {
    private String totalMarketValue;
    private List<CategorySummaryResponseDTO> categories;
}
