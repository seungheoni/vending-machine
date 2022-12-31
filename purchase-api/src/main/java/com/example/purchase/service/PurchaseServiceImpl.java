package com.example.purchase.service;

import com.example.cash.service.CashService;
import com.example.drink.service.DrinkService;
import com.example.mongo.model.Drink;
import com.example.mongo.model.Transaction;
import com.example.order.service.OrderService;
import com.example.purchase.dto.PurchaseDrinkPayLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService{

    /**
     * cash 서비스
     */
    private final CashService cashService;

    /**
     * 음료수 서비스
     */
    private final DrinkService drinkService;

    /**
     * 주문서 서비스
     */
    private final OrderService orderService;

    /**
     * 음료수 구매 서비스
     * @param purchaseDrinkPayLoad
     * @return
     */
    @Override
    public void purchaseDrink(PurchaseDrinkPayLoad purchaseDrinkPayLoad) {
        Drink drink = drinkService.bringOut(purchaseDrinkPayLoad.getDrinkCode());
        Transaction transaction = cashService.charge(drink.getPrice());
        orderService.registerBy(drink,transaction);
    }
}
