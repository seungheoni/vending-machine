package com.example.mongo.model.entitymapper;

import com.example.mongo.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface TransactionMapper {

    /**
     * 거래 입금 내역 생성
     *
     * @param amount 거래 내역금액
     * @return Transaction
     */
    @Mapping(target = "type", expression = "java(TransactionType.DEPOSIT)")
    Transaction ofDeposit(Long amount);

    /**
     * 거스름돈 반환 내역 생성
     *
     * @param amount 거스름돈 반환 금액
     * @return Transaction
     */
    @Mapping(target = "type", expression = "java(TransactionType.CHANGE)")
    Transaction ofChange(Long amount);

    /**
     * 입금 금액 사용
     * @param amount 사용한 금액
     * @return Transaction
     */
    @Mapping(target = "type", expression = "java(TransactionType.CHARGE)")
    Transaction ofCharge(Long amount);
}
