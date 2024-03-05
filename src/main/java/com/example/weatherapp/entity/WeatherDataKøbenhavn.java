package com.example.weatherapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;


@RestController
@Entity
@Table
public class WeatherDataKøbenhavn {


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
    @JsonProperty("temperature_2m")
    private Double temperature;


    private LocalDateTime time;

    @PrePersist
    protected void onCreate() {
        time = LocalDateTime.now();
    }

    public WeatherDataKøbenhavn() {
    }

    public WeatherDataKøbenhavn(Long id, Double temperature, LocalDateTime localDateTime) {
        this.id = id;
        this.temperature = temperature;
        this.time = localDateTime;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTime() {
        time = LocalDateTime.now();
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}