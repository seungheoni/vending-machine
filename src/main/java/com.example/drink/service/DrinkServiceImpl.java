package com.example.drink.service;

import com.example.drink.dto.DrinkDto;
import com.example.drink.dto.OrderDto;
import com.example.drink.model.Drink;
import com.example.drink.repo.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;


    private Drink getDrink(String drinkName) throws Exception {
        return drinkRepository.findByName(drinkName)
                .orElseThrow(() -> new Exception("존재하지 않는 음료수 입니다."));
    }

    @Override
    public DrinkDto sellDrink(OrderDto order) throws Exception {
        return null;
    }
}
