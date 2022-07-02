package com.example.display.controller;

import com.example.display.service.DisplayService;
import com.example.mongo.model.DisplayDrinkResult;
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

    /**
     * DrinkService -> DisplayService
     */
    private final DisplayService DisplayService;
    /**
     * 상품 진열
     * @author ued123
     */
    @GetMapping(path = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DisplayDrinkResult> drinkDisplay() {
        return DisplayService.GetDrinkAllList();
    }

}
