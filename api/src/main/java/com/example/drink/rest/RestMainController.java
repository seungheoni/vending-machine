package com.example.drink.rest;

import com.example.drink.dto.OrderDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMainController {

    @PostMapping("/drink_buy.json")
    @ResponseBody
    public OrderDto add(@RequestBody OrderDto orderDto) {

        return orderDto;
    }

}
