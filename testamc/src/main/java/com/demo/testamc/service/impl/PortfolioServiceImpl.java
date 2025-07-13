package com.demo.testamc.service.impl;

import com.demo.testamc.dto.CategorySummaryResponseDTO;
import com.demo.testamc.dto.PortfolioSummary;
import com.demo.testamc.models.Holding;
import com.demo.testamc.models.Portfolio;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.HoldingRepository;
import com.demo.testamc.repository.PortfolioRepository;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final HoldingRepository holdingRepository;

    @Autowired
    public PortfolioServiceImpl(UserRepository userRepository, PortfolioRepository portfolioRepository, HoldingRepository holdingRepository) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
        this.holdingRepository = holdingRepository;
    }

    @Override
    public PortfolioSummary getPortfolioSummary(String email) {
        PortfolioSummary portfolioSummary = new PortfolioSummary();
        CategorySummaryResponseDTO equityHoldings = new CategorySummaryResponseDTO();
        CategorySummaryResponseDTO fixedIncome = new CategorySummaryResponseDTO();
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) {
          Optional<Portfolio> portfolio = portfolioRepository.findByUserId(user.get().getUserId());
          if(portfolio.isPresent()) {
              portfolioSummary.setTotalMarketValue(portfolio.get().getTotalMarketValue());
             List<Holding> holding =holdingRepository.findByPortfolioId(portfolio.get().getPortfolioId());
             Map<String, Double> map = holding.stream().collect(
                     Collectors.groupingBy(
                             Holding::getCategoryName,
                             Collectors.summingDouble(Holding::getMarketValue)
                     ));
             equityHoldings.setCategoryName("Equity");
             equityHoldings.setMarketValue(map.get("Equity"));
             fixedIncome.setCategoryName("Fixed Income");
             fixedIncome.setMarketValue(map.get("Fixed Income"));
          }

        }

        List<CategorySummaryResponseDTO> categoryList = new ArrayList<>();
        categoryList.add(equityHoldings);
        categoryList.add(fixedIncome);
        portfolioSummary.setCategories(categoryList);
        return portfolioSummary;
    }

    @Override
    public Portfolio getPortfolioByUserId(Long userid) {
        return portfolioRepository.findByUserId(userid).orElse(null);
    }


}
