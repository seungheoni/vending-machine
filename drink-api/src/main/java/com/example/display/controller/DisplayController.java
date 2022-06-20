package com.example.display.controller;

import com.example.display.dto.DisplayDrinkDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayController {

    /**
     * 상품 진열
     * @author ued123
     */
    @GetMapping(path = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public DisplayDrinkDto displayDrink(DisplayDrinkDto displayDrinkDto) {


        return displayDrinkDto;
    }

}
