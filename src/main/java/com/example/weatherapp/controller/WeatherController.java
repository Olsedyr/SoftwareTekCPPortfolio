package com.example.weatherapp.controller;

import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import com.example.weatherapp.entity.WeatherDataKøbenhavnAverage;
import com.example.weatherapp.entity.WeatherDataOdense;
import com.example.weatherapp.service.WeatherServiceKøbenhavn;
import com.example.weatherapp.service.WeatherServiceKøbenhavnAverage;
import com.example.weatherapp.service.WeatherServiceOdense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/weather")
public class WeatherController {
    @Autowired
    private WeatherServiceOdense weatherService;

    @Autowired
    private WeatherServiceKøbenhavn weatherServiceKBH;

    @Autowired
    private WeatherServiceKøbenhavnAverage weatherServiceKøbenhavnAverage;

    @GetMapping("/odense/latest")
    public ResponseEntity<WeatherDataOdense> getLatestWeatherDataOdense() {
        // controller for getting latest weather data from database

        // it fetches the API for new data and saves it to the database
        weatherService.saveWeatherDataFromApi();

        // then the newest weather data gets requested and returned as "ResponseEntity.ok"
        WeatherDataOdense weatherData = weatherService.getNewestWeatherData();

        if (weatherData != null) {
            System.out.println("WeatherData in controller: " + weatherData);
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/københavn/latest")
    public ResponseEntity<WeatherDataKøbenhavn> getLatestWeatherDataKøbenhavn() {
        // controller for getting latest weather data from database

        // it fetches the API for new data and saves it to the database
        weatherServiceKBH.saveWeatherDataFromApi();

        // then the newest weather data gets requested and returned as "ResponseEntity.ok"
        WeatherDataKøbenhavn weatherData = weatherServiceKBH.getNewestWeatherData();

        if (weatherData != null) {
            System.out.println("WeatherData in controller: " + weatherData);
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/københavn/average")
    public ResponseEntity<WeatherDataKøbenhavnAverage> getAverageWeatherDataKøbenhavn() {
        // controller for getting latest weather data from database

        // it fetches the API for new data and saves it to the database
        weatherServiceKøbenhavnAverage.saveWeatherDataFromApi();

        // then the newest weather data gets requested and returned as "ResponseEntity.ok"
        WeatherDataKøbenhavnAverage weatherData = weatherServiceKøbenhavnAverage.getNewestWeatherData();

        if (weatherData != null) {
            System.out.println("WeatherData in controller: " + weatherData);
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
