package com.example.threeforecasts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "player_props")
public class PlayerProp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "prop_type")
    private String propType;

    @Column(name = "market")
    private String market;

    @Column(name = "line")
    private Double line;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "playerProp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <PlayerPropOdds> odds = new ArrayList <>();

    public PlayerProp (String playerName, String propType, String market, Double line, LocalDateTime eventTime) {
        this.playerName = playerName;
        this.propType = propType;
        this.market = market;
        this.line = line;
        this.eventTime = eventTime;
    }
}