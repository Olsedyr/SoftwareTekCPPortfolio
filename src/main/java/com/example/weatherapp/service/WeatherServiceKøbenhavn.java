package com.example.weatherapp.service;


import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import com.example.weatherapp.repository.WeatherDataRepositoryKøbenhavn;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceKøbenhavn {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast?latitude=55.6761&longitude=12.5683&current=temperature_2m&timezone=auto";

    @Autowired
    private WeatherDataRepositoryKøbenhavn weatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveWeatherDataFromApi() {
        // method to save weather data that is fetched from the API

        ResponseEntity<String> weatherApiResponse = fetchWeatherDataFromApi();
        if (weatherApiResponse != null && weatherApiResponse.getStatusCode() == HttpStatus.OK) {

            String weatherDataString = weatherApiResponse.getBody();
            System.out.println("API Response as string: " + weatherDataString);

            WeatherDataKøbenhavn weatherData = parseWeatherDataFromApiResponse(weatherDataString);
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

    private WeatherDataKøbenhavn parseWeatherDataFromApiResponse(String weatherDataString) {
        // method to parse the API response from JSON to "weatherData" Java Object

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherDataString);

            // extract "temperature_2m" value from the "current" object
            JsonNode currentTemperatureNode = rootNode.path("current").path("temperature_2m");
            Double temperature = currentTemperatureNode.isMissingNode() ? null : currentTemperatureNode.asDouble();

            WeatherDataKøbenhavn weatherData = new WeatherDataKøbenhavn();
            weatherData.setTemperature(temperature);

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public WeatherDataKøbenhavn getNewestWeatherData() {
        return weatherDataRepository.findTopByOrderByIdDesc();
    }

}
