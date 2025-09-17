package com.example.threeforecasts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ApiService {
    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate ();
    private final String baseUrl = "https://api.the-odds-api.com/v4/sports/americanfootball_nfl/";

    public Map <String, Object> getPlayerReceptions (String eventId, String regions, String markets, ) {
        String url = this.baseUrl +
                "events/{eventId}/odds?apiKey={apiKey}&regions={regions}&markets={markets}&dateFormat={dateFormat}&oddsFormat={oddsFormat}";
        return this.restTemplate.getForObject (url, Map.class);
    }
}