package com.example.threeforecasts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PlayerPropOdds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookmaker;
    private Double odds;

    @ManyToOne
    @JoinColumn(name = "player_prop_id")
    private PlayerProp playerProp;

    public PlayerPropOdds (String bookmaker, Double odds) {
        this.bookmaker = bookmaker;
        this.odds = odds;
    }
}