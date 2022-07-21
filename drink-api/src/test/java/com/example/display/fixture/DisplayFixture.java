package com.example.display.fixture;

import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import org.bson.types.ObjectId;
import java.util.List;

import static org.mockito.Mockito.when;

public class DisplayFixture {

    public static Display of(int position, String drinkName) {
        return new Display(ObjectId.get(), position, ObjectId.get(), List.of(Drink.of(drinkName,2000,5)));
    }
}