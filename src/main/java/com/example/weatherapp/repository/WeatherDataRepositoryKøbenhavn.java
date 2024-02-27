package com.example.weatherapp.repository;

import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepositoryKøbenhavn extends JpaRepository<WeatherDataKøbenhavn, Long> {

    // Add custom methods for querying weather data as needed

}
