package com.example.mongo.model;

import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

/**
 * 음료 전시 데이터
 */
@Document
@Data
@AllArgsConstructor
public class Display {
    /**
     * 전시 고유 번호
     */
    @MongoId
    ObjectId id;

    /**
     * 전시 위치
     */
    int position;

    /**
     * 음료 고유 번호
     */
    ObjectId drinkId;

    /**
     * 음료
     */
    List<Drink> drinks;

    public static Display of(int position) {
        return new Display(null, position, null, null);
    }

    public static Display of(int position, ObjectId drinkId) {
        return new Display(null, position, drinkId, null);
    }

    /**
     * 전시 음료결과 데이터 변환
     */
    public DisplayDrinkResult toDisplayDrinkResult() {
        Drink drink = drinks.stream().findFirst().orElseThrow();
        Status status = (drink.quantity == 0) ? Status.SOLDOUT : Status.AVAILABLE;
        return new DisplayDrinkResult(id, position, status, drink.name, drink.price);

    }
}
