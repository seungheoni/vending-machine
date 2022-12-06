package com.example.mongo.model.entitymapper;

import com.example.cash.dto.CashChangeView;
import com.example.cash.dto.CashDepositView;
import com.example.mongo.model.Cash;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface CashMapper {

    CashDepositView cashToCashDepositView(Cash cash);

    @Mapping(target = "amount", source = "cash.balance")
    CashChangeView cashToCashChangeView(Cash cash);

    /**
     * 입금하기
     * @param cash 캐시정보
     * @param amount 금액
     */
    @Mapping(target = "balance",source = "amount", qualifiedByName = "increaseBalance")
    Cash deposit(@Context Cash cash, Long amount);


    /**
     * 남은 거스름돈 반환
     * @param cash 캐시정보
     */
    @Mapping(target = "balance", constant = "0L")
    Cash change(Cash cash);

    /**
     * 입금 금액 사용
     * @param amount 금액
     */
    @Mapping(target = "balance",source = "amount", qualifiedByName = "decreaseBalance")
    Cash charge(@Context Cash cash, Long amount);

    @Named("decreaseBalance")
    default Long decreaseBalance(@Context Cash cash, Long amount) {
        return cash.getBalance() - amount;
    }

    @Named("increaseBalance")
    default Long increaseBalance(@Context Cash cash, Long amount) {
        return cash.getBalance() + amount;
    }
}
