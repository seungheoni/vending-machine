package com.example.mongo.model.entitymapper;

import com.example.display.dto.DisplayDrinkView;
import com.example.drink.enums.Status;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface DisplayDrinkMapper {

    @Mapping(target = "status", source = "drink", qualifiedByName = "statusOnQuantity")
    @Mapping(target = "drinkCode", source = "drink.code")
    @Mapping(target = "position", source = "display.position")
    DisplayDrinkView drinkToDisplayDrinkView(Display display , Drink drink);

    @Named("statusOnQuantity")
    default Status statusOnQuantity(Drink drink) {
        return (drink.getQuantity() == 0) ? Status.SOLDOUT : Status.AVAILABLE;
    }

}