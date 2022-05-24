package com.example.drink.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Drink {

    private long drinkId;
    private String drinkName;
    private int price;
    private int quantity_count;

}
