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
    public Drink sellDrink(Order order) throws Exception {

        Optional<Drink> drinkOpt = drinkRepository.getDrink(order.getDrinkName());

         /*
            음료 판매
            1. 음료가 존재하지 않으면 에러메시지
            2. 고객의 금액이 부족한 경우 에러 메시지
            3. 구매
         */
        Drink drink = getDrink(order.getDrinkName());

        // 보유 금액
        int amount = order.getDrinkPrice();
        if(!drink.enablePurchase(amount)) throw new Exception("잔액이 부족합니다.");
        return drink;
    }

    private Drink getDrink(String drinkName) throws Exception {
        return drinkRepository.getDrink(drinkName)
                .orElseThrow(() -> new Exception("존재하지 않는 음료수 입니다."));
    }
}
