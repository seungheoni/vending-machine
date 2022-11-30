package com.example.order.dto;

import com.example.mongo.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayLoad {

    /**
     * 음료수 재고 관리 코드
     */
    private String drinkCode;

    /**
     * 구매 항목
     */
    private String item;

    /**
     * 제품 가격
     */
    private long price;

}
