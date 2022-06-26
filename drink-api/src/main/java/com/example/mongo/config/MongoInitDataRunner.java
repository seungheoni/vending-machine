package com.example.mongo.config;

import com.example.drink.repo.DrinkRepository;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class MongoInitDataRunner implements ApplicationRunner {

    private final DrinkRepository drinkRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        initData();
    }

    @Transactional
    public void initData() {

        System.out.println("몽고 디비 데이터 초기화 중... ");
        drinkRepository.deleteAll();

        System.out.println("몽고 디비 초기화 데이터 생성중... ");
        List<Drink> drinks = Arrays.asList(new Drink(ObjectId.get(),"콜라",2000,30, List.of(new Display(1),new Display(2),new Display(3))),
                new Drink(ObjectId.get(),"식헤",3000,30, List.of(new Display(4),new Display(5),new Display(6))),
                new Drink(ObjectId.get(),"사이다",1500,0, List.of(new Display(7),new Display(8))));

        drinkRepository.saveAll(drinks);
        System.out.println("몽고 디비 초기화 데이터 생성 완료... ");
    }
}