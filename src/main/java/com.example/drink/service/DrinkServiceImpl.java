package com.example.drink.service;

import com.example.drink.dto.Drink;
import com.example.drink.dto.Order;
import com.example.drink.repo.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Override
    public Optional sellDrink(Order order) {

        Optional<Drink> drinkOpt = drinkRepository.getDrink(order.getDrinkName());

        if(drinkOpt.isPresent()) {
            Drink drink = drinkOpt.get();

            if(order.getDrinkPrice() >= drink.getPrice()) {
                return Optional.of(drink);
            } else {
                return Optional.of("잔액이 부족합니다.");
            }

        } else {
            return Optional.of("상품이 품절입니다.");
        }
    }
}
