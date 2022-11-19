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

    /**
     * 전시 음료결과 데이터 변환
     */
    public DisplayDrinkView toDisplayDrinkResult() {
        return drinks.stream().findFirst().map(this::toDisplayDrinkView).orElse(null);
    }

    /**
     * DisplayDrinkView 변환 함수
     *
     * @param drink 음료수
     */
    private DisplayDrinkView toDisplayDrinkView(Drink drink) {
        Status status = (drink.quantity == 0) ? Status.SOLDOUT : Status.AVAILABLE;
        return new DisplayDrinkView(drink.code, position, status, drink.name, drink.price);
    }
}
