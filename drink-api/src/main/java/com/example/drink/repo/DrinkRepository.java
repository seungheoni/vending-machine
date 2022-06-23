package com.example.drink.repo;

import com.example.mongo.model.Drink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkRepository extends MongoRepository<Drink,String> {

    Optional<Drink> findByName(String name);

}