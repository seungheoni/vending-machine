package com.example.drink.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderDto {

    private String drinkName;
    private int drinkPrice;
}
