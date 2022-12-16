package com.example.drink.service;

import com.example.drink.repo.DrinkRepository;
import com.example.error.exception.DrinkNotExistExcepion;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Override
    public Drink getByDrinkCode(String drinkCode) {
        return drinkRepository.findByCode(drinkCode)
                .orElseThrow(() -> new DrinkNotExistExcepion(HttpStatus.NOT_FOUND));
    }
}