package com.example.display.dto;

import com.example.drink.enums.Status;
import lombok.*;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class DisplayDrinkView {

    private ObjectId drinkId;
    private int position;
    private Status status;
    private String name;
    private long price;
}