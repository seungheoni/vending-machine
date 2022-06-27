package com.example.drink.service;

import com.example.drink.dto.DrinkDisplayDTO;
import com.example.drink.dto.DrinkDto;
import com.example.drink.enums.Status;
import com.example.drink.repo.DrinkRepository;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;

    public List<DrinkDisplayDTO> GetDrinkAllList() {
        return drinkRepository.findAll().stream()
                .map(Drink::toDrinkDisplayDTOs)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
