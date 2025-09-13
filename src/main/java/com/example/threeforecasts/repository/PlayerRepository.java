package com.example.threeforecasts.repository;

import com.example.threeforecasts.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository <Player, Long> {

}