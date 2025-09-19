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
@Table(name = "events")
public class Event {
    @Id
    private String id;

    private String sport;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "away_team")
    private String awayTeam;

    @Column(name = "commence_time")
    private LocalDateTime commenceTime;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List <PlayerProp> playerProps;

    public Event (String sport, String homeTeam, String awayTeam, LocalDateTime commenceTime) {
        this.sport = sport;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.commenceTime = commenceTime;
    }
}