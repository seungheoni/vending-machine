package com.example.drink.service;

import com.example.drink.dto.Drink;
import com.example.drink.repo.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final DrinkRepository drinkRepository;

    @Override
    public Drink sellDrink() {
        return null;
    }
}
