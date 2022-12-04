package com.example.transaction.service;

import com.example.mongo.model.Transaction;
import com.example.mongo.model.entitymapper.TransactionMapper;
import com.example.transaction.repo.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public Transaction deposit(Long amount) {
        return transactionRepository.save(transactionMapper.ofDeposit(amount));
    }

    @Override
    public Transaction change(Long amount) {
        return transactionRepository.save(transactionMapper.ofChange(amount));
    }

    @Override
    public Transaction charge(Long amount) {return transactionRepository.save(transactionMapper.ofCharge(amount));}

}
