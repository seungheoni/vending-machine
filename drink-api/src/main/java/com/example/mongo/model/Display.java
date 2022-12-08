package com.example.mongo.model;

import com.example.display.dto.DisplayDrinkView;
import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Objects;

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
     * 음료수 물류 관리 번호
     */
    String drinkCode;

    /**
     * 전시 등록된 음료수 리스트
     */
    @ReadOnlyProperty
    List<Drink> drinks;

    public static Display of(int position, String drinkCode) {
        return new Display(null, position, drinkCode, null);
    }

    public boolean isDrinkRegistered() {
        return !drinks.isEmpty();
    }
}
