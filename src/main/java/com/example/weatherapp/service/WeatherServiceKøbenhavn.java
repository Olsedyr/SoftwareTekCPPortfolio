package com.example.weatherapp.service;

import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import com.example.weatherapp.repository.WeatherDataRepositoryKøbenhavn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceKøbenhavn {

    @Autowired
    private WeatherDataRepositoryKøbenhavn weatherDataRepository;

    public void saveWeatherData(WeatherDataKøbenhavn weatherData) {
        weatherDataRepository.save(weatherData);
    }

    public WeatherDataKøbenhavn getWeatherDataById(Long id) {
        return weatherDataRepository.findById(id).orElse(null);
    }

    // Other methods as needed

}