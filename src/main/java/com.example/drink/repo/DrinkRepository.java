package com.example.drink.repo;

import com.example.drink.dto.Drink;

public interface DrinkRepository {

    void pushDrink(Drink drink);

    Drink getDrink(String drinkName);
}
