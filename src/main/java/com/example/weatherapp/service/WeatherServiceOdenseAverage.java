package com.example.weatherapp.service;



import com.example.weatherapp.entity.WeatherDataOdenseAverage;
import com.example.weatherapp.repository.WeatherDataRepositoryOdenseAverage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceOdenseAverage {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast?latitude=55.4038&longitude=10.4024&daily=temperature_2m_mean&past_days=7&forecast_days=0";

    @Autowired
    private WeatherDataRepositoryOdenseAverage weatherDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void saveWeatherDataFromApi() {
        // method to save weather data that is fetched from the API

        ResponseEntity<String> weatherApiResponse = fetchWeatherDataFromApi();
        if (weatherApiResponse != null && weatherApiResponse.getStatusCode() == HttpStatus.OK) {

            String weatherDataString = weatherApiResponse.getBody();
            System.out.println("API Response as string: " + weatherDataString);

            WeatherDataOdenseAverage weatherData = parseWeatherDataFromApiResponse(weatherDataString);
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

    private WeatherDataOdenseAverage parseWeatherDataFromApiResponse(String weatherDataString) {
        // method to parse the API response from JSON to "weatherData" Java Object

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(weatherDataString);

            // extract temperatures from the array
            JsonNode temperatureArrayNode = rootNode.path("daily").path("temperature_2m_mean");

            // calculate the sum of temperatures and count the number of temperatures
            double sum = 0.0;
            int count = 0;
            for (JsonNode temperatureNode : temperatureArrayNode) {
                double temperature = temperatureNode.isMissingNode() ? 0.0 : temperatureNode.asDouble();
                sum += temperature;
                count++;
            }

            // calculate the weekly average temperature
            double weeklyAverage = count > 0 ? sum / count : 0.0;

            // round the weekly average temperature to two decimal places
            double roundedAverage = Math.round(weeklyAverage * 100.0) / 100.0;

            WeatherDataOdenseAverage weatherData = new WeatherDataOdenseAverage();
            weatherData.setAverageTemperatureOdense(roundedAverage);

            return weatherData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public WeatherDataOdenseAverage getNewestWeatherData() {
        return weatherDataRepository.findTopByOrderByIdDesc();
    }

}
