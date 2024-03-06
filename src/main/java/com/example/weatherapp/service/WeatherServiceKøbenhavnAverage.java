package com.example.weatherapp.service;


import com.example.weatherapp.entity.WeatherDataKøbenhavnAverage;
import com.example.weatherapp.repository.WeatherDataRepositoryKøbenhavnAverage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceKøbenhavnAverage {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast?latitude=55.6761&longitude=12.5683&daily=temperature_2m_mean&start_date=2024-02-26&end_date=2024-02-26&timezone=auto";

    @Autowired
    private WeatherDataRepositoryKøbenhavnAverage weatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveWeatherDataFromApi() {
        // method to save weather data that is fetched from the API

        ResponseEntity<String> weatherApiResponse = fetchWeatherDataFromApi();
        if (weatherApiResponse != null && weatherApiResponse.getStatusCode() == HttpStatus.OK) {

            String weatherDataString = weatherApiResponse.getBody();
            System.out.println("API Response as string: " + weatherDataString);

            WeatherDataKøbenhavnAverage weatherData = parseWeatherDataFromApiResponse(weatherDataString);
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

    private WeatherDataKøbenhavnAverage parseWeatherDataFromApiResponse(String weatherDataString) {
        // method to parse the API response from JSON to "weatherData" Java Object

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherDataString);

            // extract "temperature_2m_mean" value from the "current" object
            JsonNode currentTemperatureNode = rootNode.path("daily").path("temperature_2m_mean");
            Double temperature = currentTemperatureNode.isMissingNode() ? null : currentTemperatureNode.asDouble();

            WeatherDataKøbenhavnAverage weatherData = new WeatherDataKøbenhavnAverage();
            weatherData.setAverageTemperatureKøbenhavn(temperature);

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public WeatherDataKøbenhavnAverage getNewestWeatherData() {
        return weatherDataRepository.findTopByOrderByIdDesc();
    }

}
