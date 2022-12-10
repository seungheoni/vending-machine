package com.example.display.service;

import com.example.display.dto.DisplayDrinkView;
import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.Display;
import com.example.mongo.model.entitymapper.DisplayDrinkMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final DisplayRepository displayRepository;

    private final DisplayDrinkMapper displayDrinkMapper;

    @Override
    public List<DisplayDrinkView> getDisplayDrinks() {

        return  displayRepository.findWithDrink().stream()
                .filter(Display::isDrinkRegistered)
                .map(displayDrinkMapper::drinkToDisplayDrinkView)
                .collect(Collectors.toList());
    }
}
