package com.example.drink.service;

import com.example.drink.dto.DrinkDisplayDTO;
import com.example.drink.dto.DrinkDto;
import com.example.mongo.model.Drink;

import java.util.List;

public interface DrinkService {

    //List<DrinkDto> GetDrinkAllList();

    List<DrinkDisplayDTO> GetDrinkAllList();
}
