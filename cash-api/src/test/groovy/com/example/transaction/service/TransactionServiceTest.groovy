package com.example.transaction.service

import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import com.example.transaction.fixture.TransactionBuilder
import com.example.transaction.repo.TransactionRepository
import spock.lang.Specification

class TransactionServiceTest extends Specification {

    def transactionRepository = Stub(TransactionRepository.class)
    TransactionServiceImpl transactionServiceImpl = new TransactionServiceImpl(transactionRepository)

    def "입금 거래 내역 생성"() {
        given: "5000원의 입금 내역 데이터를 생성"
        Transaction expected = TransactionBuilder.make {
            type TransactionType.DEPOSIT
            amount 5000
        }.build()
        when: "transactionRepository.save() 수행한 경우"
        transactionRepository.save({ expected } as Transaction) >> expected
        then: "transactionServiceImpl.deposit()의 결과값의 type 과 amount는 기대값과 같다"
        def result = transactionServiceImpl.deposit(5000)
        verifyAll {
            result.type == expected.type
            result.amount == expected.amount
        }
    }
}
