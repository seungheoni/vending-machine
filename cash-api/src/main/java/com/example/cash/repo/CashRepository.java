package com.example.cash.repo;

import com.example.mongo.model.Cash;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRepository extends MongoRepository<Cash, ObjectId> {
}
