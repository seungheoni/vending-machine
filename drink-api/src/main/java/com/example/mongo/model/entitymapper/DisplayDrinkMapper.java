package com.example.mongo.model.entitymapper;

import com.example.display.dto.DisplayDrinkView;
import com.example.drink.enums.Status;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;


@Mapper(componentModel = "spring", uses = {})
public interface DisplayDrinkMapper {
    @Mapping(target = "status", source = "display.drinks", qualifiedByName = "getStatusByQuantity")
    @Mapping(target = "drinkCode", source = "display.drinks", qualifiedByName = "getDrinkCode")
    @Mapping(target = "name", source = "display.drinks",qualifiedByName = "getDrinkName")
    @Mapping(target = "position", source = "display.position")
    DisplayDrinkView drinkToDisplayDrinkView(Display display);

    @Named("getStatusByQuantity")
    default Status getStatusByQuantity(List<Drink> drinks) {
        long quantity = drinks.get(0).getQuantity();
        return (quantity == 0) ? Status.SOLDOUT : Status.AVAILABLE;
    }

    @Named("getDrinkCode")
    default String getDrinkCode(List<Drink> drinks) {
        return drinks.get(0).getCode();
    }

    @Named("getDrinkName")
    default String getDrinkName(List<Drink> drinks) {
        return drinks.get(0).getName();
    }
}