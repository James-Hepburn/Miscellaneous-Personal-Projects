package com.example.threeforecasts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate ();
    private final String baseUrl = "https://api.the-odds-api.com/v4/sports/americanfootball_nfl/";

    public Map <String, Object> getPlayerProps (String eventId, String market) {
        String url = this.baseUrl +
                "events/" + eventId + "/odds?apiKey=" + this.apiKey +
                "&regions=us&markets=" + market + "&oddsFormat=american";
        return this.restTemplate.getForObject (url, Map.class);
    }

    public List <Map <String, Object>> getUpcomingEvents () {
        String url = this.baseUrl + "events?apiKey=" + this.apiKey;
        return this.restTemplate.getForObject (url, List.class);
    }

    public List <String> getAllEventIds () {
        List <Map <String, Object>> upcomingEvents = getUpcomingEvents ();
        List <String> eventIds = new ArrayList <>();

        for (Map <String, Object> event : upcomingEvents) {
            eventIds.add ((String) event.get ("id"));
        }

        return eventIds;
    }
}