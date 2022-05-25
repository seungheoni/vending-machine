package com.example.drink.service;

import com.example.drink.dto.Order;

import java.util.Optional;

public interface DrinkService {

    Optional sellDrink(Order order);

}
