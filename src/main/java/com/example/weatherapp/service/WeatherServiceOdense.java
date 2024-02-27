package com.example.weatherapp.service;

import com.example.weatherapp.entity.WeatherDataOdense;
import com.example.weatherapp.repository.WeatherDataRepositoryOdense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceOdense {

    @Autowired
    private WeatherDataRepositoryOdense weatherDataRepository;

    public void saveWeatherData(WeatherDataOdense weatherData) {
        weatherDataRepository.save(weatherData);
    }

    public WeatherDataOdense getWeatherDataById(Long id) {
        return weatherDataRepository.findById(id).orElse(null);
    }

    // Other methods as needed

}