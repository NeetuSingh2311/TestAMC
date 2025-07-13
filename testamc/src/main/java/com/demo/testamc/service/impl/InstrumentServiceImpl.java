package com.demo.testamc.service.impl;

import com.demo.testamc.models.Instrument;
import com.demo.testamc.repository.InstrumentRepository;
import com.demo.testamc.service.InstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
