package com.example.drink.repo;

import com.example.drink.dto.Drink;

import java.util.Optional;

public interface DrinkRepository {

    void pushDrink(Drink drink);

    Optional<Drink> getDrink(String drinkName);
}
