package com.example.purchase.controller;

import com.example.cash.service.CashService;
import com.example.drink.service.DrinkService;
import com.example.mongo.model.Drink;
import com.example.mongo.model.Transaction;
import com.example.order.service.OrderService;
import com.example.purchase.dto.PurchaseDrinkPayLoad;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

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
     * 음료수 구매 api
     * @param purchaseDrinkPayLoad
     * @return ResponseEntity<String>
     */
    @PostMapping(value = "/drink",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchaseDrink(@Valid @RequestBody PurchaseDrinkPayLoad purchaseDrinkPayLoad) {
        Drink drink = drinkService.bringOut(purchaseDrinkPayLoad.getDrinkCode());
        Transaction transaction = cashService.charge(drink.getPrice());
        orderService.registerBy(drink,transaction);
    }
}
