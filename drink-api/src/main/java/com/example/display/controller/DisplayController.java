package com.example.display.controller;

import com.example.drink.dto.DrinkDto;
import com.example.drink.service.DrinkService;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
public class DisplayController {

    private final DrinkService drinkService;
    /**
     * 상품 진열
     * @author ued123
     */
    @GetMapping(path = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public DrinkDto drinkDisplay(DrinkDto drinkDto) {

        List<DrinkDto> drinks = drinkService.GetDrinkAllList();
        return drinkDto;
    }

}
