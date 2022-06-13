package com.example.drink.rest;

import com.example.drink.dto.DisplayDrinkDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drinks")
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
