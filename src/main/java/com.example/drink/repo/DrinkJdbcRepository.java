package com.example.drink.repo;

import com.example.drink.dto.Drink;
import org.springframework.stereotype.Repository;

@Repository
public class DrinkJdbcRepository implements DrinkRepository{

    @Override
    public void pushDrink(Drink drink) {

    }

    @Override
    public Drink getDrink(String drinkName) {
        return null;
    }
}
