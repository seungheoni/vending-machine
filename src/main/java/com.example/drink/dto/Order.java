package com.example.drink.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    private long UUID;
    private String drinkName;
    private int drinkPrice;

}
