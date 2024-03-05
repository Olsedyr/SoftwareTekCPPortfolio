package com.example.weatherapp.entity;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;


@RestController
@Entity
@Table
public class WeatherDataOdense {

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
    private Double temperature_2m;

    private LocalDateTime time;

    public WeatherDataOdense() {
    }

    public WeatherDataOdense(Long id, Double temperature_2m, LocalDateTime localDateTime) {
        this.id = id;
        this.temperature_2m = temperature_2m;
        this.time = localDateTime;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public Double getTemperature() {
        return temperature_2m;
    }

    public void setTemperature(Double temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public LocalDateTime getTime() {
        time = LocalDateTime.now();
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
