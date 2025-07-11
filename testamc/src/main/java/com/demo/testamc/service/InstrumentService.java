package com.demo.testamc.service;

import com.demo.testamc.models.Instrument;

public interface InstrumentService {
    Instrument getInstrumentById(Long instrumentId);
}
