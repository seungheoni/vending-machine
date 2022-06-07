package com.example.drink.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class DrinkDto {

    private long drinkId;
    private String drinkName;
    private int price;
    private int quantity_count;

    /**
     * 음료 구매 여부
     * amount: 보유 금액
     */
    public Boolean enablePurchase(int amount) {
        return amount >= price;
    }
}
