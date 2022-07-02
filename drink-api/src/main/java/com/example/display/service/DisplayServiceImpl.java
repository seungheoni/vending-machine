package com.example.display.service;

import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.Display;
import com.example.display.dto.DisplayDrinkView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final DisplayRepository displayRepository;

    @Override
    public List<DisplayDrinkView> GetDrinkAllList() {
        List<Display> result = displayRepository.findWithDrink();
        return result.stream().map(Display::toDisplayDrinkResult).collect(Collectors.toList());
    }
}
