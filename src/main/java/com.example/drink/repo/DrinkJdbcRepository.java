package com.example.drink.repo;

import com.example.drink.dto.DrinkDto;

import java.util.Optional;

public class DrinkJdbcRepository implements DrinkRepository{

    @Override
    public Optional<DrinkDto> findByDrinkName(String drinkName) {
        return null;
    }
}
