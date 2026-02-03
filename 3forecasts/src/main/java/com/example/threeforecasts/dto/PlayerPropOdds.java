package com.example.threeforecasts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class PlayerPropOdds {
    private String playerName;
    private String market;
    private Map <String, Map <String, Double>> oddsByBookmaker;
    private LocalDateTime eventTime;
    private Map <String, Double> lineByPropType;
}