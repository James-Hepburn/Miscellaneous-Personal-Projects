package com.example.threeforecasts.repository;

import com.example.threeforecasts.model.PlayerPropOdds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPropOddsRepository extends JpaRepository <PlayerPropOdds, Long> {
}