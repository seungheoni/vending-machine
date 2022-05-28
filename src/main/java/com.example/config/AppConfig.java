package com.example.config;

import com.example.drink.repo.DrinkJdbcRepository;
import com.example.drink.repo.DrinkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DrinkRepository drinkRepository() {
        return new DrinkJdbcRepository();
    }
}
