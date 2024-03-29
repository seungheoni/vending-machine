package com.example.display.controller;

import com.example.display.service.DisplayService;
import com.example.display.dto.DisplayDrinkView;
import com.example.mongo.model.Display;
import com.example.mongo.model.entitymapper.DisplayDrinkMapper;
import io.swagger.v3.oas.annotations.Operation;
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

    private final DisplayService displayService;

    /**
     * 상품 진열
     * @author ued123
     */
    @Operation(summary = "상품 진열")
    @GetMapping(path = "/display", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DisplayDrinkView> drinkDisplay() {

        return displayService.getDisplayDrinks();
    }
}
