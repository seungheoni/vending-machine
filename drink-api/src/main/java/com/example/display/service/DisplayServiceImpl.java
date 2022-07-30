package com.example.display.service;

import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.Display;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final DisplayRepository displayRepository;

    @Override
    public List<Display> getDisplayDrinks() {
        return displayRepository.findWithDrink();
    }
}
