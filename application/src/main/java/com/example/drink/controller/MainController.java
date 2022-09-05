package com.example.drink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    @GetMapping("/")
    public String mainframe(Model model) {

        model.addAttribute("name","음료수 자판기");


        return "mainframe";
    }
}
