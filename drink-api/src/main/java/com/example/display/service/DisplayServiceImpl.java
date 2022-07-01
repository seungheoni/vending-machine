package com.example.display.service;

import com.example.display.repo.DisplayRepository;
import com.example.mongo.model.DisplayDrinkResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final MongoTemplate mongoTemplate;
    private final DisplayRepository displayRepository;

    @Override
    public List<DisplayDrinkResult> GetDrinkAllList() {

        return null;
    }


}
