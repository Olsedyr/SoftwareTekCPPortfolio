package com.example.weatherapp.controller;

import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import com.example.weatherapp.entity.WeatherDataOdense;
import com.example.weatherapp.service.WeatherServiceKøbenhavn;
import com.example.weatherapp.service.WeatherServiceOdense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Console;

// WeatherController.java
@RestController
@RequestMapping("/api/weatherOdense")
public class WeatherController {
    @Autowired
    private WeatherServiceOdense weatherService;

    @GetMapping("/latest")
    public ResponseEntity<WeatherDataOdense> getLatestWeatherData() {
        weatherService.saveWeatherDataFromApi();
        WeatherDataOdense weatherData = weatherService.getNewestWeatherData();
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
