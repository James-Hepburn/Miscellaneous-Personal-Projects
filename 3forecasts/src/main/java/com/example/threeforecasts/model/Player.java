package com.example.threeforecasts.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String team;
    private String position;

    public Player (String firstName, String lastName, String team, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.position = position;
    }
}