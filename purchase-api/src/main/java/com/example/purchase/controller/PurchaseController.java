package com.example.purchase.controller;

import com.example.purchase.dto.PurchaseDrinkPayLoad;
import com.example.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * 음료수 구매 api
     * @param purchaseDrinkPayLoad
     * @return ResponseEntity<String>
     */
    @PostMapping(value = "/drink",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void purchaseDrink(@Valid @RequestBody PurchaseDrinkPayLoad purchaseDrinkPayLoad) {
        purchaseService.purchaseDrink(purchaseDrinkPayLoad);
    }
}
