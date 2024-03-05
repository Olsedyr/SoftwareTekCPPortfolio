package com.example.weatherapp.repository;

import com.example.weatherapp.entity.WeatherDataKøbenhavn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepositoryKøbenhavn extends JpaRepository<WeatherDataKøbenhavn, Long> {

    // find the newest row in the database by id
    WeatherDataKøbenhavn findTopByOrderByIdDesc();


}
