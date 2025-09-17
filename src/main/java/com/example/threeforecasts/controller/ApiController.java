package com.example.threeforecasts.controller;

import com.example.threeforecasts.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private ApiService apiService;

    public ApiController (ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/receptions")
    public void getPlayerReceptions () {
        System.out.println (this.apiService.getPlayerReceptions ("aca5234c57e31b1931e51d2d0d6046f5"));
    }
}