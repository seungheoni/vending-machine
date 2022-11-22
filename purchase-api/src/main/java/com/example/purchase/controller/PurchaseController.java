package com.example.purchase.controller;

import com.example.purchase.dto.PurchaseDrinkPayLoad;
import com.example.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> purchaseDrink(@Valid @RequestBody PurchaseDrinkPayLoad purchaseDrinkPayLoad) {
        return purchaseService.purchaseDrink(purchaseDrinkPayLoad);
    }
}
