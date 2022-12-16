package com.example.mongo.model.entitymapper;

import com.example.mongo.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface DrinkMapper {

    @Mapping(target = "quantity", source = "drink.quantity",qualifiedByName = "reduceQuantity")
    Drink reduceQuantity(Drink drink);

    @Named("reduceQuantity")
    default Long reduceQuantity(long quantity) {
        return quantity - 1;
    }
}