package com.example.drink.service;

import com.example.drink.dto.DrinkDto;
import com.example.drink.dto.OrderDto;

public interface DrinkService {

    DrinkDto sellDrink(OrderDto order) throws Exception;

}
