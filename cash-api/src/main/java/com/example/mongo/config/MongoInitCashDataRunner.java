package com.example.mongo.config;

import com.example.cash.repo.CashRepository;
import com.example.mongo.model.Cash;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class MongoInitCashDataRunner implements ApplicationRunner {

    private final CashRepository cashRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("cash 도메인 개발모드 활성화...");
        clearData();
        initData();
    }

    public void initData() {

        System.out.println("cash 컬렉션 초기화 데이터 생성중... ");
        cashRepository.save(Cash.of(0L));
    }

    public void clearData() {
        System.out.println("cash 컬렉션 데이터 비우는 중... ");
        cashRepository.deleteAll();
    }
}