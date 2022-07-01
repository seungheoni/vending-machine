package com.example.mongo.model;

import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;

@Data
@ToString
@AllArgsConstructor
public class DisplayDrinkResult {

    private ObjectId id;

    private int position;

    private Status status;

    private String name;

    private long price;

}
