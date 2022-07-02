package com.example.display.repo;

import com.example.mongo.model.Display;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisplayRepository extends MongoRepository<Display, ObjectId> {

    @Aggregation(pipeline = {
            "{ $lookup:\n" +
            "       {\n" +
            "         from: drink,\n" +
            "         localField: drinkId,\n" +
            "         foreignField: _id,\n" +
            "         as: drinks\n" +
            "       }" +
            "}"})
    List<Display> findWithDrink();
}
