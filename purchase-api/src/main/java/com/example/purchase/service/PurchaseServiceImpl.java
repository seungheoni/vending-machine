package com.example.purchase.service;

import com.example.purchase.dto.PurchaseDrinkPayLoad;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    @Override
    public ResponseEntity<String> purchaseDrink(PurchaseDrinkPayLoad purchaseDrinkPayLoad) {
        return null;
    }
}
