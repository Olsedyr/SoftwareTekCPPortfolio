package com.example.weatherapp.repository;


import com.example.weatherapp.entity.WeatherDataOdenseAverage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepositoryOdenseAverage extends JpaRepository<WeatherDataOdenseAverage, Long> {

    // find the newest row in the database by id
    WeatherDataOdenseAverage findTopByOrderByIdDesc();



}
