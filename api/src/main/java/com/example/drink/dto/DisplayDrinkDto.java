package com.example.drink.dto;

import com.example.drink.core.Status;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DisplayDrinkDto {

    private int position;
    private Status status;
    private String name;
    private int price;
}
