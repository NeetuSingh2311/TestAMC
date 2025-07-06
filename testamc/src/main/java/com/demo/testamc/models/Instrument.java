package com.demo.testamc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instruments")
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instrumentId;

    private String name;
    private String tickerSymbol;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}