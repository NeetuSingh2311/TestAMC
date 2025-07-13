package com.demo.testamc.service;

import com.demo.testamc.dto.CategorySummaryResponseDTO;
import com.demo.testamc.dto.PortfolioSummary;
import com.demo.testamc.models.Holding;
import com.demo.testamc.models.Portfolio;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.HoldingRepository;
import com.demo.testamc.repository.PortfolioRepository;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.impl.PortfolioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PortfolioServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PortfolioRepository portfolioRepository;
    @Mock
    private HoldingRepository holdingRepository;

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPortfolioSummary_Success() {
        String email = "user@example.com";
        User user = User.builder().userId(1L).email(email).build();
        Portfolio portfolio = Portfolio.builder().portfolioId(10L).userId(1L).totalMarketValue("10000").build();

        List<Holding> holdings = Arrays.asList(
                Holding.builder().categoryName("Equity").marketValue(6000.0).build(),
                Holding.builder().categoryName("Fixed Income").marketValue(4000.0).build()
        );

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUserId(user.getUserId())).thenReturn(Optional.of(portfolio));
        when(holdingRepository.findByPortfolioId(portfolio.getPortfolioId())).thenReturn(holdings);

        PortfolioSummary summary = portfolioService.getPortfolioSummary(email);

        assertNotNull(summary);
        assertEquals("10000", summary.getTotalMarketValue());
        assertNotNull(summary.getCategories());
        assertEquals(2, summary.getCategories().size());

        Map<String, Double> categoryMap = new HashMap<>();
        for (CategorySummaryResponseDTO dto : summary.getCategories()) {
            categoryMap.put(dto.getCategoryName(), dto.getMarketValue());
        }
        assertEquals(6000.0, categoryMap.get("Equity"));
        assertEquals(4000.0, categoryMap.get("Fixed Income"));
    }

    @Test
    void testGetPortfolioSummary_UserNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        PortfolioSummary summary = portfolioService.getPortfolioSummary("notfound@example.com");

        assertNotNull(summary);
        assertNull(summary.getTotalMarketValue());
        assertNotNull(summary.getCategories());
        assertEquals(2, summary.getCategories().size());
    }

    @Test
    void testGetPortfolioSummary_PortfolioNotFound() {
        User user = User.builder().userId(1L).email("user@example.com").build();
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUserId(user.getUserId())).thenReturn(Optional.empty());

        PortfolioSummary summary = portfolioService.getPortfolioSummary("user@example.com");

        assertNotNull(summary);
        assertNull(summary.getTotalMarketValue());
        assertNotNull(summary.getCategories());
        assertEquals(2, summary.getCategories().size());
    }

    @Test
    void testGetPortfolioByUserId_Found() {
        Portfolio portfolio = Portfolio.builder().portfolioId(10L).userId(1L).totalMarketValue("10000.0").build();
        when(portfolioRepository.findByUserId(1L)).thenReturn(Optional.of(portfolio));
        Portfolio result = portfolioService.getPortfolioByUserId(1L);
        assertNotNull(result);
        assertEquals(10L, result.getPortfolioId());
    }

    @Test
    void testGetPortfolioByUserId_NotFound() {
        when(portfolioRepository.findByUserId(2L)).thenReturn(Optional.empty());
        Portfolio result = portfolioService.getPortfolioByUserId(2L);
        assertNull(result);
    }
}
