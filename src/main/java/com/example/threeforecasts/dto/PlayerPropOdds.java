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
    String playerName;
    String market;
    Map <String, Map <String, Double>> oddsByBookmaker;
    LocalDateTime eventTime;
    Map <String, Double> lineByPropType;
}