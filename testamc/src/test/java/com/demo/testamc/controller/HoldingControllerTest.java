package com.demo.testamc.controller;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.service.HoldingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HoldingControllerTest {

    @Mock
    private HoldingService holdingService;

    @InjectMocks
    private HoldingController holdingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHoldings_ReturnsHoldingDetailsList() {
        String email = "user@example.com";
        String category = "EQUITY";

        HoldingDetailsResponseDTO holding1 = new HoldingDetailsResponseDTO();
        HoldingDetailsResponseDTO holding2 = new HoldingDetailsResponseDTO();

        List<HoldingDetailsResponseDTO> mockHoldings = Arrays.asList(holding1, holding2);

        when(holdingService.getHoldingDetails(email, category)).thenReturn(mockHoldings);

        ResponseEntity<List<HoldingDetailsResponseDTO>> response = holdingController.getHoldings(email, category);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        verify(holdingService, times(1)).getHoldingDetails(email, category);
    }
}
