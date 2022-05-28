package com.example.drink.repo;

import com.example.drink.dto.Drink;

public interface DrinkRepository {

    Drink findByDrinkName(String drinkName);

}