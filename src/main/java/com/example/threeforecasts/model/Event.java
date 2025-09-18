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
public class Event {
    @Id
    private Long id;

    private String sport;
    private String homeTeam;
    private String awayTeam;
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