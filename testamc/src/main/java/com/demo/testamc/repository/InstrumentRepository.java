package com.demo.testamc.repository;

import com.demo.testamc.models.Instrument;
import com.demo.testamc.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {
    Optional<Instrument> findByInstrumentId(Long instrumentId);

}