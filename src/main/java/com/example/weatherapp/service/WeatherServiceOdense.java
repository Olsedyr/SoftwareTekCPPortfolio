package com.example.weatherapp.service;

import com.example.weatherapp.entity.WeatherDataOdense;
import com.example.weatherapp.repository.WeatherDataRepositoryOdense;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
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
        // method to save weather data that is fetched from the API

        ResponseEntity<String> weatherApiResponse = fetchWeatherDataFromApi();
        if (weatherApiResponse != null && weatherApiResponse.getStatusCode() == HttpStatus.OK) {

            String weatherDataString = weatherApiResponse.getBody();
            System.out.println("API Response as string: " + weatherDataString);

            WeatherDataOdense weatherData = parseWeatherDataFromApiResponse(weatherDataString);
            System.out.println("API response parsed: " + weatherData);

            if (weatherData != null) {
                weatherDataRepository.save(weatherData);
            }
        }
    }

    public ResponseEntity<String> fetchWeatherDataFromApi() {
        // method to fetch API for weather data

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(WEATHER_API_URL, String.class);
            return response;
        } catch (Exception e) {
            // Handle error or log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private WeatherDataOdense parseWeatherDataFromApiResponse(String weatherDataString) {
        // method to parse the API response from JSON to "weatherData" Java Object

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherDataString);

            // extract "temperature_2m" value from the "current" object
            JsonNode currentTemperatureNode = rootNode.path("current").path("temperature_2m");
            Double temperature = currentTemperatureNode.isMissingNode() ? null : currentTemperatureNode.asDouble();

            WeatherDataOdense weatherData = new WeatherDataOdense();
            weatherData.setTemperature(temperature);

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public WeatherDataOdense getNewestWeatherData() {
        return weatherDataRepository.findTopByOrderByIdDesc();
    }

}
