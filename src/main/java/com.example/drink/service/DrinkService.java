package com.example.drink.service;

import com.example.drink.dto.Drink;
import com.example.drink.dto.Order;

public interface DrinkService {

    Drink sellDrink(Order order) throws Exception;

}
