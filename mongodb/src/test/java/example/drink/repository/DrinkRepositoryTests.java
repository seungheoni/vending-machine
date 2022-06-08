package example.drink.repository;

import com.example.config.MongoConfigurer;
import com.example.drink.model.Drink;
import com.example.drink.repo.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {MongoConfigurer.class})
public class DrinkRepositoryTests {

    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));

    static {
        mongoDBContainer.start();
    }

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> mongoDBContainer.getReplicaSetUrl());
    }

    @Autowired
    private DrinkRepository drinkRepository;

    @BeforeEach
    public void setUp() {
        drinkRepository.deleteAll();

        //init data
        var count = (long) drinkRepository.saveAll(initData()).size();
        assertTrue(count == 3);
    }

    @Test
    public void findAll() {

        var resultCount = drinkRepository.findAll().stream().filter(drink -> drink.getId() != null).count();
        assertTrue(3 == resultCount);
    }

    @Test
    public void findOne() {

        Optional<Drink> result = drinkRepository.findByName("콜라");

        System.out.println(result.get());
        assertNotNull(result);
    }

    @Test
    public void insertOne() {

        Drink drink = new Drink(null,"주스",4000,30);
        drinkRepository.insert(drink);
        var count = (long) drinkRepository.findAll().size();
        assertEquals(4,count);
    }

    @Test
    public void DeleteOne() {

        Optional<Drink> sida = drinkRepository.findByName("사이다");
        assertNotNull(sida);

        drinkRepository.delete(sida.get());
        assertThrows(NoSuchElementException.class, () -> drinkRepository.findByName("사이다").get());
    }

    @Test
    public void save() {
        Optional<Drink> drink = drinkRepository.findByName("사이다");

        Drink sida = drink.get();
        sida.setPrice(5000);

        drinkRepository.save(sida);
        assertTrue(drinkRepository.findById(String.valueOf(sida.getId())).get().getPrice() == 5000);
    }

    private List<Drink> initData() {

        List<Drink> drinks = Arrays.asList(new Drink(null,"콜라",2000,30),
                new Drink(null,"식헤",3000,30),
                new Drink(null,"사이다",1500,30));

        return drinks;
    }
}
