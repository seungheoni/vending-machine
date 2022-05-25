package com.example.drink.repo;

import com.example.drink.dto.Drink;

import java.util.Optional;

public class DrinkJdbcRepository implements DrinkRepository{

    @Override
    public void pushDrink(Drink drink) {

    }

    @Override
    public Optional<Drink> getDrink(String drinkName) {
        return Optional.empty();
    }
}
