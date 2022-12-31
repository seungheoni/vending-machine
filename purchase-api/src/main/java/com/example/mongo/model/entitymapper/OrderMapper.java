package com.example.mongo.model.entitymapper;

import com.example.mongo.model.Drink;
import com.example.mongo.model.Order;
import com.example.mongo.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {})
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price",source = "transaction.amount")
    @Mapping(target = "drinkCode",source = "drink.code")
    @Mapping(target = "item",source = "drink.name")
    Order drinkTransactionToOrder(Drink drink, Transaction transaction);
}