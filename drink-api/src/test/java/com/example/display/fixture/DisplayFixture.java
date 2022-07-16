package com.example.display.fixture;

import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import org.bson.types.ObjectId;
import java.util.List;

import static org.mockito.Mockito.when;

public class DisplayFixture {

    public static void displayRepositoryStubbing(DisplayRepository displayRepository) {

        when(displayRepository.findWithDrink())
                .thenReturn(List.of(
                        DisplayFixture.of(1,"콜라"),
                        DisplayFixture.of(2,"식혜"),
                        DisplayFixture.of(3,"사이다")));
    }

    public static Display of(int position, String drinkName) {
        return new Display(ObjectId.get(), position, ObjectId.get(), List.of(Drink.of(drinkName,2000,5)));
    }
}