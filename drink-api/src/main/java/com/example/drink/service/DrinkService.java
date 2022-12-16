package com.example.drink.service;

import com.example.mongo.model.Drink;

public interface DrinkService {

    Drink getDrinkByDrinkCode(String drinkCode);
}