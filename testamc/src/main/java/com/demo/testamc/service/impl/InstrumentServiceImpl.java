package com.demo.testamc.service.impl;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.models.Holding;
import com.demo.testamc.models.Instrument;
import com.demo.testamc.models.Portfolio;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.InstrumentRepository;
import com.demo.testamc.service.HoldingService;
import com.demo.testamc.service.InstrumentService;
import com.demo.testamc.service.PortfolioService;
import com.demo.testamc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentServiceImpl implements InstrumentService {
    private InstrumentRepository    instrumentRepository;

    @Autowired
    public void setUserService( InstrumentRepository instrumentRepository) {
   this.instrumentRepository = instrumentRepository;

    }
    @Override
    public Instrument getInstrumentById(Long instrumentId) {
        Optional<Instrument> instrument = instrumentRepository.findByInstrumentId(instrumentId);
        return instrument.orElse(null);
    }
}
