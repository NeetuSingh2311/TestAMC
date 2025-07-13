package com.demo.testamc.service;

import com.demo.testamc.models.Instrument;
import com.demo.testamc.repository.InstrumentRepository;
import com.demo.testamc.service.impl.InstrumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstrumentServiceImplTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    private InstrumentServiceImpl instrumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instrumentService = new InstrumentServiceImpl();
        instrumentService.setUserService(instrumentRepository);
    }

    @Test
    void testGetInstrumentById_Found() {
        Long instrumentId = 1L;
        Instrument instrument = Instrument.builder()
                .instrumentId(instrumentId)
                .name("Test Instrument")
                .build();

        when(instrumentRepository.findByInstrumentId(instrumentId)).thenReturn(Optional.of(instrument));

        Instrument result = instrumentService.getInstrumentById(instrumentId);

        assertNotNull(result);
        assertEquals(instrumentId, result.getInstrumentId());
        assertEquals("Test Instrument", result.getName());
    }

    @Test
    void testGetInstrumentById_NotFound() {
        Long instrumentId = 2L;
        when(instrumentRepository.findByInstrumentId(instrumentId)).thenReturn(Optional.empty());

        Instrument result = instrumentService.getInstrumentById(instrumentId);

        assertNull(result);
    }
}
