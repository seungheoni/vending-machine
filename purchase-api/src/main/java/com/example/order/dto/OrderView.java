package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {

    /**
     * 주문서 고유 번호
     */
    private ObjectId id;

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

    /**
     * 주문서 생성 날짜
     */
    private Instant createDate;
}
