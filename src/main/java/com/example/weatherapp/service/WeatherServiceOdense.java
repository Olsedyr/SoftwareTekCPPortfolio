package com.example.weatherapp.service;

import com.example.weatherapp.entity.WeatherDataOdense;
import com.example.weatherapp.repository.WeatherDataRepositoryOdense;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceOdense {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast?latitude=55.4038&longitude=10.4024&current=temperature_2m&timezone=auto";

    @Autowired
    private WeatherDataRepositoryOdense weatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveWeatherDataFromApi() {
        ResponseEntity<String> weatherApiResponse = fetchWeatherDataFromApi();
        if (weatherApiResponse != null && weatherApiResponse.getStatusCode() == HttpStatus.OK) {
            String weatherDataString = weatherApiResponse.getBody();
            WeatherDataOdense weatherData = parseWeatherDataFromApiResponse(weatherDataString);
            if (weatherData != null) {
                weatherDataRepository.save(weatherData);
            }
        }
    }

    public ResponseEntity<String> fetchWeatherDataFromApi() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(WEATHER_API_URL, String.class);
            return response;
        } catch (Exception e) {
            // Handle error or log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private WeatherDataOdense parseWeatherDataFromApiResponse(String weatherDataString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            WeatherDataOdense weatherData = objectMapper.readValue(weatherDataString, WeatherDataOdense.class);
            return weatherData;
        } catch (Exception e) {
            // Handle parsing error or log exception
            e.printStackTrace();
            return null;
        }

    }

    public WeatherDataOdense getNewestWeatherData() {
        return weatherDataRepository.findTopByOrderByIdDesc();
    }

}
