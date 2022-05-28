package com.example.drink.service;

import com.example.drink.dto.DrinkDto;
import com.example.drink.dto.OrderDto;
import com.example.drink.repo.DrinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    @Override
    public DrinkDto sellDrink(OrderDto order) throws Exception {

         /*
            음료 판매
            1. 음료가 존재하지 않으면 에러메시지
            2. 고객의 금액이 부족한 경우 에러 메시지
            3. 구매
         */
        DrinkDto drink = getDrink(order.getDrinkName());

        // 보유 금액
        int amount = order.getDrinkPrice();
        if(!drink.enablePurchase(amount)) throw new Exception("잔액이 부족합니다.");
        return drink;
    }

    private DrinkDto getDrink(String drinkName) throws Exception {
        return drinkRepository.findByDrinkName(drinkName)
                .orElseThrow(() -> new Exception("존재하지 않는 음료수 입니다."));
    }
}
