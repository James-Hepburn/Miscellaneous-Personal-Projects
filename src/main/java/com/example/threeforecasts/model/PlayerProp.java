package com.example.threeforecasts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PlayerProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private String propType;
    private String market;
    private Double line;
    private LocalDateTime eventTime;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "playerProp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <PlayerPropOdds> odds;

    public PlayerProp (String playerName, String propType, String market, Double line, LocalDateTime eventTime) {
        this.playerName = playerName;
        this.propType = propType;
        this.market = market;
        this.line = line;
        this.eventTime = eventTime;
    }
}