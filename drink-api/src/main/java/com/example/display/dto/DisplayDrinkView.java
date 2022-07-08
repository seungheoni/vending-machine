package com.example.display.dto;

import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;

import java.util.Random;

@Data
@ToString
@AllArgsConstructor
public class DisplayDrinkView {

    private ObjectId drinkId;
    private int position;
    private Status status;
    private String name;
    private long price;

    public static DisplayDrinkView of(String name,int position) {

        return new DisplayDrinkView(
                ObjectId.get(),
                position,
                Status.AVAILABLE,
                name,
                (int) (Math.random() * (7000 - 1000 + 1)) + 1000);
    }
}