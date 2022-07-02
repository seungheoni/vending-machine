package com.example.display.repo;

import com.example.drink.repo.DrinkRepository;
import com.example.mongo.DbIntegrationTests;
import com.example.mongo.model.Display;
import com.example.mongo.model.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class DisplayRepositoryTests extends DbIntegrationTests {

    @Autowired
    private DisplayRepository displayRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    private final List<Drink> drinks = List.of(
            Drink.of("콜라", 2000, 30),
            Drink.of("식헤", 3000, 30),
            Drink.of("사이다", 1500, 0));

    @BeforeEach
    public void setUp() {
        displayRepository.deleteAll();
    }

    @Test
    public void findWithDrink() {
        List<Display> displays = new ArrayList<>();
        drinkRepository.saveAll(drinks).forEach(drink -> {
            generateDisplay(displays, drink);
        });
        displayRepository.saveAll(displays);
        long count = displayRepository.findWithDrink().stream().filter(display -> display.getDrinks() != null).count();
        assertTrue(count > 0);

    }

    private void generateDisplay(List<Display> displays, Drink drink) {
        int position = displays.size() + 1;
        if (position > 9) return;
        displays.add(Display.of(position, drink.getId()));
    }
}
