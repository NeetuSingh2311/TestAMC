package com.demo.testamc.service;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.models.Holding;
import com.demo.testamc.models.Instrument;
import com.demo.testamc.models.Portfolio;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.HoldingRepository;
import com.demo.testamc.repository.InstrumentRepository;
import com.demo.testamc.repository.PortfolioRepository;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.impl.HoldingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HoldingServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PortfolioRepository portfolioRepository;
    @Mock
    private HoldingRepository holdingRepository;
    @Mock
    private InstrumentRepository instrumentRepository;

    @InjectMocks
    private HoldingServiceImpl holdingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHoldingDetails_Success() {
        String email = "user@example.com";
        String category = "EQUITY";

        User user = User.builder().userId(1L).email(email).build();
        Portfolio portfolio = Portfolio.builder().portfolioId(10L).userId(1L).build();

        Holding holding = Holding.builder()
                .holdingId(100L)
                .portfolioId(10L)
                .instrumentId(1000L)
                .categoryName(category)
                .marketValue(BigDecimal.valueOf(5000).doubleValue())
                .quantity(BigDecimal.valueOf(10).intValue())
                .build();

        Instrument instrument = Instrument.builder()
                .instrumentId(1000L)
                .name("Test Instrument")
                .description("Test Desc")
                .tickerSymbol("TST")
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUserId(user.getUserId())).thenReturn(Optional.of(portfolio));
        when(holdingRepository.findByPortfolioId(portfolio.getPortfolioId())).thenReturn(Collections.singletonList(holding));
        when(instrumentRepository.findByInstrumentId(holding.getInstrumentId())).thenReturn(Optional.of(instrument));

        List<HoldingDetailsResponseDTO> result = holdingService.getHoldingDetails(email, category);

        assertNotNull(result);
        assertEquals(1, result.size());
        HoldingDetailsResponseDTO dto = result.getFirst();
        assertEquals("Test Instrument", dto.getInstrumentName());
        assertEquals("Test Desc", dto.getDescription());
        assertEquals("TST", dto.getTicker());
        assertEquals("5000.0", dto.getMarketValue());
        assertEquals("10", dto.getQuantity());
    }

    @Test
    void testGetHoldingDetails_UserNotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        List<HoldingDetailsResponseDTO> result = holdingService.getHoldingDetails("notfound@example.com", "EQUITY");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetHoldingDetails_PortfolioNotFound() {
        User user = User.builder().userId(1L).email("user@example.com").build();
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUserId(user.getUserId())).thenReturn(Optional.empty());
        List<HoldingDetailsResponseDTO> result = holdingService.getHoldingDetails("user@example.com", "EQUITY");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetHoldingDetails_NoHoldings() {
        User user = User.builder().userId(1L).email("user@example.com").build();
        Portfolio portfolio = Portfolio.builder().portfolioId(10L).userId(1L).build();
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(portfolioRepository.findByUserId(user.getUserId())).thenReturn(Optional.of(portfolio));
        when(holdingRepository.findByPortfolioId(portfolio.getPortfolioId())).thenReturn(Collections.emptyList());
        List<HoldingDetailsResponseDTO> result = holdingService.getHoldingDetails("user@example.com", "EQUITY");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
