package com.example.transaction.service;

import com.example.mongo.model.Transaction;

public interface TransactionService {

    Transaction deposit(Long amount);

}
