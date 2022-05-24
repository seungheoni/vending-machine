package com.example.drink.repo;

import com.example.drink.dto.Drink;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Primary
public class DrinkMemoryRepository implements DrinkRepository {

    private Map<String, List<Drink>> drinkMap;

    @Value("${drink.product.list}")
    List<String> drinkNameList;

    @PostConstruct
    public void init() {
        /*
            음료수 제품마다 List 초기화
         */
        drinkMap = new HashMap();
        for(String drinkName : drinkNameList) {
            drinkMap.put(drinkName,new ArrayList<Drink>());
        }
    }

    @Override
    public void pushDrink(Drink drink) {

        if(drink.getDrinkName() != null) {
            List<Drink> drinkList = drinkMap.get(drink.getDrinkName());
            drinkList.add(drink);
        } else {
            System.out.println("error - 넣을 음료수에 이름이 없습니다.");
        }

    }

    @Override
    public Drink getDrink(String drinkName) {

        List<Drink> drinkList = drinkMap.get(drinkName);
        Drink drink = drinkList.get(0);
        drinkList.remove(0);

        return drink;
    }
}
