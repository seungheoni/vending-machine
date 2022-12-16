package com.example.drink.service;

import com.example.drink.repo.DrinkRepository;
import com.example.error.exception.DrinkNotExistExcepion;
import com.example.error.exception.DrinkSoldOutException;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Override
    public Drink getDrinkByDrinkCode(String drinkCode) {

        Drink result = drinkRepository.findByCode(drinkCode)
                .orElseThrow(() -> new DrinkNotExistExcepion(HttpStatus.NOT_FOUND));

        return Optional.of(result).filter(drink -> drink.getQuantity() > 0)
                .orElseThrow(() -> new DrinkSoldOutException(HttpStatus.BAD_REQUEST));
    }
}