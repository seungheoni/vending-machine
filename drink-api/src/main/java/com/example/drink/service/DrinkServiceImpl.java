package com.example.drink.service;

import com.example.drink.repo.DrinkRepository;
import com.example.error.exception.DrinkNotExistExcepion;
import com.example.error.exception.DrinkSoldOutException;
import com.example.mongo.model.Drink;
import com.example.mongo.model.entitymapper.DrinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    private final DrinkMapper drinkMapper;

    @Override
    public Drink getByDrinkCode(String drinkCode) {
        return drinkRepository.findByCode(drinkCode)
                .orElseThrow(() -> new DrinkNotExistExcepion(HttpStatus.NOT_FOUND));
    }

    /**
     * 음료수를 꺼내다
     * @param drinkCode 음료수 번호
     */
    @Override
    public Drink bringOut(String drinkCode) {
        Drink drink = getByDrinkCode(drinkCode);
        if (!drink.isRemained()) {
            throw new DrinkSoldOutException(HttpStatus.BAD_REQUEST);
        }
        Drink updateDrink = drinkMapper.reduceQuantity(drink);
        return drinkRepository.save(updateDrink);
    }
}