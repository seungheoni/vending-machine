package com.example.display.repo;

import com.example.mongo.model.Display;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DisplayRepository extends MongoRepository<Display, ObjectId> {
}
