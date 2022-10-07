package com.example.transaction.service;

import com.example.mongo.model.Transaction;
import com.example.transaction.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction deposit(Long amount) {
        return transactionRepository.save(Transaction.ofDeposit(amount));
    }

}