package com.example.mongo.model;

import com.example.drink.dto.DrinkDisplayDTO;
import com.example.drink.dto.DrinkDto;
import com.example.drink.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Document
@Data
@AllArgsConstructor
public class Drink {

    @MongoId
    ObjectId id;
    String name;
    long price;
    long quantity;
    List<Display> displays;

    public List<DrinkDisplayDTO> toDrinkDisplayDTOs() {
        return displays.stream().map(display -> {
            Status status = (quantity > 0)? Status.AVAILABLE : Status.SOLDOUT;
            return new DrinkDisplayDTO(display.position, id.toHexString(), status, name, price);
        }).collect(Collectors.toList());
    }
}