package com.demo.testamc.service.impl;

import com.demo.testamc.dto.HoldingDetailsResponseDTO;
import com.demo.testamc.models.Holding;
import com.demo.testamc.models.Instrument;
import com.demo.testamc.models.Portfolio;
import com.demo.testamc.models.User;
import com.demo.testamc.repository.HoldingRepository;
import com.demo.testamc.repository.InstrumentRepository;
import com.demo.testamc.repository.PortfolioRepository;
import com.demo.testamc.repository.UserRepository;
import com.demo.testamc.service.HoldingService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Builder
public class HoldingServiceImpl  implements HoldingService {

    private final UserRepository userRepository;
    private final PortfolioRepository portfolioRepository;
    private final HoldingRepository holdingRepository;
    private final InstrumentRepository instrumentRepository;


    @Autowired
    public HoldingServiceImpl(UserRepository userRepository, PortfolioRepository portfolioRepository, HoldingRepository holdingRepository, InstrumentRepository instrumentRepository) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
        this.holdingRepository = holdingRepository;
        this.instrumentRepository = instrumentRepository;
    }


    @Override
    public List<HoldingDetailsResponseDTO> getHoldingDetails(String email, String category) {
        Optional<User> user = userRepository.findByEmail(email);
        List<HoldingDetailsResponseDTO> holdingsResp = new ArrayList<>();
        if(user.isPresent()) {
            Optional<Portfolio> portfolio = portfolioRepository.findByUserId(user.get().getUserId());
            if(portfolio.isPresent()){
                List<Holding> holdings =holdingRepository.findByPortfolioId(portfolio.get().getPortfolioId());

                holdings.stream().filter((holding -> Objects.equals(holding.getCategoryName(), category)))
                        .forEach(holding -> { instrumentRepository.findByInstrumentId(holding.getInstrumentId())
                            .ifPresent(instrument -> holdingsResp.add(createHoldingsResp(instrument, holding)));
                });
            }
        }
        return holdingsResp;

    }

    private HoldingDetailsResponseDTO createHoldingsResp(Instrument instrument, Holding holding){
        return HoldingDetailsResponseDTO.builder()
                .instrumentName(instrument.getName()).marketValue(holding.getMarketValue().toString()).quantity(holding.getQuantity().toString())
                .description(instrument.getDescription()).ticker(instrument.getTickerSymbol()).build();
    }
}
