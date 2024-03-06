package com.example.weatherapp.repository;

import com.example.weatherapp.entity.WeatherDataKøbenhavnAverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepositoryKøbenhavnAverage extends JpaRepository<WeatherDataKøbenhavnAverage, Long> {

    // find the newest row in the database by id
    WeatherDataKøbenhavnAverage findTopByOrderByIdDesc();



}
