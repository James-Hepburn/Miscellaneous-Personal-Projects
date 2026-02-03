package com.example.threeforecasts.controller;

import com.example.threeforecasts.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private PlayerRepository playerRepository;

    @GetMapping("/home")
    public String dashboard(Model model) {
        model.addAttribute ("players", this.playerRepository.findAll ());
        return "home";
    }
}