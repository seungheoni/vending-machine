package com.example.mongo.model.entitymapper;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;
import com.example.mongo.model.Cash;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface CashMapper {

    CashDepositView CashToCashDepositView(Cash cash);

    @Mapping(target="amount", source="cash.balance")
    CashChangeView CashToCashChangeView(Cash cash);


    /**
     * 입금하기
     * @param cash 캐시정보
     * @param amount 금액
     * @return Cash
     */
    @Mapping(target="balance", expression = "java(cash.getBalance() + amount )")
    Cash deposit(Cash cash,Long amount);


    /**
     * 남은 거스름돈 반환
     * @param cash 캐시정보
     * @return Cash
     */
    @Mapping(target="balance", constant = "0L")
    Cash change(Cash cash);

    /**
     * 입금 금액 사용
     * @param amount 금액
     */
    @Mapping(target = "balance", expression = "java(cash.getBalance() - amount)")
    Cash charge(Cash cash,Long amount);
}
