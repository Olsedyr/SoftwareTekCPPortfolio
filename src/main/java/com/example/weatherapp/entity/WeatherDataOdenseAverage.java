package com.example.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;


@RestController
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class WeatherDataOdenseAverage {


    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @JsonProperty("temperature_2m_mean")
    private Double averageTemperatureOdense;


    private LocalDateTime time;

    @PrePersist
    protected void onCreate() {
        time = LocalDateTime.now();
    }

    public WeatherDataOdenseAverage() {
    }

    public WeatherDataOdenseAverage(Long id, Double averageTemperatureOdense, LocalDateTime localDateTime) {
        this.id = id;
        this.averageTemperatureOdense = averageTemperatureOdense;
        this.time = localDateTime;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Double getAverageTemperatureOdense() {
        return averageTemperatureOdense;
    }

    public void setAverageTemperatureOdense(Double averageTemperatureOdense) {
        this.averageTemperatureOdense = averageTemperatureOdense;
    }

    public LocalDateTime getTime() {
        time = LocalDateTime.now();
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}