package com.example.threeforecasts.repository;

import com.example.threeforecasts.model.PlayerProp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerPropRepository extends JpaRepository <PlayerProp, Long> {
}