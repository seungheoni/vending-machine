package com.example.purchase.service;

import com.example.purchase.dto.PurchaseDrinkPayLoad;
import org.springframework.http.ResponseEntity;

public interface PurchaseService {

    ResponseEntity<String> purchaseDrink(PurchaseDrinkPayLoad purchaseDrinkPayLoad);
}
