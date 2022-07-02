package com.example.mongo.config;

import com.example.display.repo.DisplayRepository;
import com.example.drink.repo.DrinkRepository;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class MongoInitDataRunner implements ApplicationRunner {

    private final DrinkRepository drinkRepository;

    private final DisplayRepository displayRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("개발모드 활성화...");
        clearData();
        initData();
    }

    public void initData() {

        System.out.println("컬렉션 초기화 데이터 생성중... ");
        List<Drink> drinks = List.of(
                Drink.of("콜라", 2000, 30),
                Drink.of("식헤", 3000, 30),
                Drink.of("사이다", 1500, 0));

        List<Display> displays = new ArrayList<>();
        drinkRepository.saveAll(drinks).forEach(drink -> generateDisplay(displays, drink));
        displayRepository.saveAll(displays);

        System.out.println("컬렉션 초기화 데이터 생성 완료... ");
    }

    public void generateDisplay(List<Display> displays, Drink drink) {
        int position = displays.size() + 1;
        if (position > 9) return;
        displays.add(Display.of(position, drink.getId()));
    }

    public void clearData() {
        System.out.println("컬렉션 데이터 비우는 중... ");
        drinkRepository.deleteAll();
        displayRepository.deleteAll();
    }
}