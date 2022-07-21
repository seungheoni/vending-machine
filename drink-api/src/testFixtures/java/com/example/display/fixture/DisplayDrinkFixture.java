package com.example.display.fixture;

import com.example.display.dto.DisplayDrinkView;
import com.example.drink.enums.Status;
import org.bson.types.ObjectId;

public class DisplayDrinkFixture {

    public static DisplayDrinkView of(int position,String name) {
        return new DisplayDrinkView(ObjectId.get(),position,Status.AVAILABLE,name,5000);
    }
}