package com.example.drink.dto;

import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class DrinkDisplayDTO {

    private int position;
    private String drinkId;
    private Status status;
    private String name;
    private long price;
}
