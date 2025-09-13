package com.example.threeforecasts.controller;

import com.example.threeforecasts.model.Player;
import com.example.threeforecasts.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PlayerController {
    private PlayerRepository playerRepository;

    @GetMapping
    public List <Player> getAllPlayers () {
        return this.playerRepository.findAll ();
    }

    @GetMapping("/{id}")
    public Player getPlayer (@PathVariable Long id) {
        return this.playerRepository.findById (id).orElseThrow (() ->
                new RuntimeException ("Player not found"));
    }
}