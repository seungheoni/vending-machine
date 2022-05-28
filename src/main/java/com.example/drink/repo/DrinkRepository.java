package com.example.drink.repo;

import com.example.drink.dto.DrinkDto;

import java.util.Optional;

public interface DrinkRepository {

    Optional<DrinkDto> findByDrinkName(String drinkName);

}