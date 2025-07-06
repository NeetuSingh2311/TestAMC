package com.demo.testamc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "holdings")
public class Holding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holdingId;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    private Integer quantity;
    private Double marketValue;
}