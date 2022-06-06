package com.example.drink.controller;

import com.example.drink.dto.OrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainframe(Model model) {

        model.addAttribute("name","음료수 자판기");

        return "mainframe";
    }

    @PostMapping("/drink_buy.json")
    @ResponseBody
    public OrderDto add(@RequestBody OrderDto orderDto) {

        return orderDto;
    }
}
