package com.example.threeforecasts.controller;

import com.example.threeforecasts.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {
    private ApiService apiService;

    public ApiController (ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/props/{market}")
    public Map <String, Object> getPlayerProps (@PathVariable String market) {
        return this.apiService.getPlayerProps ("aca5234c57e31b1931e51d2d0d6046f5", market);
    }
}