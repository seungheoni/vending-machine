package com.example.display.dto;

import com.example.drink.enums.Status;
import lombok.*;

/**
 * 음료수 진열 응답 필드
 */
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class DisplayDrinkView {

    /**
     * 음료수 물류 관리 번호
     */
    private String drinkCode;

    /**
     * 진열 위치
     */
    private int position;

    /**
     * 상품 상태
     */
    private Status status = Status.SOLDOUT;

    /**
     * 상품 명
     */
    private String name;

    /**
     * 가격
     */
    private long price;
}