package com.example.threeforecasts.controller;

import com.example.threeforecasts.dto.PlayerPropOdds;
import com.example.threeforecasts.service.ApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    private ApiService apiService;

    public ApiController (ApiService apiService) {
        this.apiService = apiService;
    }

    @Scheduled(fixedRate = 60 * 60 * 1000 * 24) // every day
    public void updateAndStoreData () {
        this.apiService.storeAllData ();
    }

    @GetMapping("/props/{eventId}/{market}")
    public Map <String, Object> getPlayerProps (@PathVariable String eventId, @PathVariable String market) {
        return this.apiService.getPlayerProps (eventId, market);
    }

    @GetMapping("/allProps/{eventId}")
    public List <PlayerPropOdds> getAllPlayerProps (@PathVariable String eventId) {
        return this.apiService.getAllPlayerProps (eventId);
    }

    @GetMapping("/eventIds")
    public List <String> getAllEventIds () {
        return this.apiService.getAllEventIds ();
    }
}