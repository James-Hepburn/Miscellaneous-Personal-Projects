package com.example.threeforecasts.service;

import com.example.threeforecasts.dto.PlayerPropOdds;
import com.example.threeforecasts.repository.EventRepository;
import com.example.threeforecasts.repository.PlayerPropOddsRepository;
import com.example.threeforecasts.repository.PlayerPropRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate ();
    private final String baseUrl = "https://api.the-odds-api.com/v4/sports/americanfootball_nfl/";

    private final EventRepository eventRepository;
    private final PlayerPropRepository playerPropRepository;
    private final PlayerPropOddsRepository playerPropOddsRepository;

    public ApiService (EventRepository eventRepository, PlayerPropRepository playerPropRepository, PlayerPropOddsRepository playerPropOddsRepository) {
        this.eventRepository = eventRepository;
        this.playerPropRepository = playerPropRepository;
        this.playerPropOddsRepository = playerPropOddsRepository;
    }

    private final List <String> allMarkets = Arrays.asList (
            "player_assists",
            "player_defensive_interceptions",
            "player_field_goals",
            "player_kicking_points",
            "player_pass_attempts",
            "player_pass_completions",
            "player_pass_interceptions",
            "player_pass_longest_completion",
            "player_pass_rush_yds",
            "player_pass_rush_reception_tds",
            "player_pass_rush_reception_yds",
            "player_pass_tds",
            "player_pass_yds",
            "player_pass_yds_q1",
            "player_pats",
            "player_receptions",
            "player_reception_longest",
            "player_reception_tds",
            "player_reception_yds",
            "player_rush_attempts",
            "player_rush_longest",
            "player_rush_reception_tds",
            "player_rush_reception_yds",
            "player_rush_tds",
            "player_rush_yds",
            "player_sacks",
            "player_solo_tackles",
            "player_tackles_assists",
            "player_tds_over",
            "player_1st_td",
            "player_anytime_td",
            "player_last_td"
    );

    // Get player props for a specific market
    public Map <String, Object> getPlayerProps (String eventId, String market) {
        String url = this.baseUrl +
                "events/" + eventId + "/odds?apiKey=" + this.apiKey +
                "&regions=us&markets=" + market + "&oddsFormat=american";
        return this.restTemplate.getForObject (url, Map.class);
    }

    // Get player props for every market
    public List <PlayerPropOdds> getAllPlayerProps (String eventId) {
        List <PlayerPropOdds> playerProps = new ArrayList <>();

        for (String market : allMarkets) {
            Map <String, Object> odds = getPlayerProps (eventId, market);
            playerProps.addAll (parseOdds (odds, market));
        }

        return playerProps;
    }

    // Parse player props data
    private List <PlayerPropOdds> parseOdds (Map <String, Object> odds, String market) {
        List <PlayerPropOdds> parsed = new ArrayList <>();

        String commenceTime = (String) odds.get ("commence_time");
        LocalDateTime eventTime = LocalDateTime.parse (commenceTime.replace ("Z", ""));

        List <Map <String, Object>> bookmakers = (List <Map <String, Object>>) odds.get ("bookmakers");

        for (Map <String, Object> bookmaker : bookmakers) {
            String bookmakerName = (String) bookmaker.get ("title");
            List <Map <String, Object>> markets = (List <Map <String, Object>>) bookmaker.get ("markets");

            for (Map <String, Object> m : markets) {
                if (!market.equals (m.get ("key"))){
                    continue;
                }

                List <Map <String, Object>> outcomes = (List <Map <String, Object>>) m.get ("outcomes");

                for (Map <String, Object> outcome : outcomes) {
                    String playerName = (String) outcome.get ("description");
                    String overUnder = (String) outcome.get ("name");
                    Double price = ((Number) outcome.get ("price")).doubleValue ();

                    Map <String, Double> oddsMap = Map.of (bookmakerName, price);
                    parsed.add (new PlayerPropOdds (playerName, market, overUnder, oddsMap, eventTime));
                }
            }
        }

        return parsed;
    }

    // Get all upcoming events
    public List <Map <String, Object>> getUpcomingEvents () {
        String url = this.baseUrl + "events?apiKey=" + this.apiKey;
        return this.restTemplate.getForObject (url, List.class);
    }

    // Get the game IDs of all upcoming events
    public List <String> getAllEventIds () {
        List <Map <String, Object>> upcomingEvents = getUpcomingEvents ();
        List <String> eventIds = new ArrayList <>();

        for (Map <String, Object> event : upcomingEvents) {
            eventIds.add ((String) event.get ("id"));
        }

        return eventIds;
    }
}