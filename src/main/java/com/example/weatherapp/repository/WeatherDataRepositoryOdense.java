package com.example.weatherapp.repository;

import com.example.weatherapp.entity.WeatherDataOdense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepositoryOdense extends JpaRepository<WeatherDataOdense, Long> {

    // Add custom methods for querying weather data as needed

}
