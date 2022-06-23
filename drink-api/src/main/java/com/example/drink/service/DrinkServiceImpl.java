package com.example.drink.service;

import com.example.drink.dto.DrinkDto;
import com.example.drink.enums.Status;
import com.example.drink.repo.DrinkRepository;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    public List<DrinkDto> GetDrinkAllList() {

         List<DrinkDto> drinkList = drinkRepository.findAll().stream()
                                                   .filter(d -> d.getQuantity() > 0 )
                                                   .map(d -> new DrinkDto(1,String.valueOf(d.getId()), Status.AVAILABLE,d.getName(),d.getPrice()))
                                                   .collect(Collectors.toList());

         return drinkList;
    }

}
