package com.example.drink.dto;

import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;


@Data
@ToString
@AllArgsConstructor
public class DrinkDto {

    private int position;
    private String drinkId;
    private Status status;
    private String name;
    private long price;
}
