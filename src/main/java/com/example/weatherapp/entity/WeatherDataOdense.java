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
    private String temperature;
    private String condition;

    private LocalDateTime localDateTime;

    public WeatherDataOdense() {
    }

    public WeatherDataOdense(Long id, String temperature, String condition, LocalDateTime localDateTime) {
        this.id = id;
        this.temperature = temperature;
        this.condition = condition;
        this.localDateTime = localDateTime;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}