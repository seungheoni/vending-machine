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
     * 주문서 uniq 코드
     * ex) 0020101,0020102
     */
    private String code;

    /**
     * 구매 항목
     */
    private String item;

    /**
     * 제품 가격
     */
    private long price;

    public Order toOrder() {
        return new Order(null,code,item,price,null);
    }

}
