package com.example.transaction.repo

import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import mongo.DbIntegrationTest
import org.springframework.beans.factory.annotation.Autowired

class 가 extends DbIntegrationTest {

    @Autowired
    TransactionRepository transactionRepository

    def "거래 내역 데이터 조회"() {
        given: "500원의 입금 내역 데이터를 생성"
        transactionRepository.deleteAll()
        Transaction expected = Transaction.ofDeposit(500)
        when: "입금 내역을 db 에 저장할 떄"
        Transaction result = transactionRepository.save(expected)
        then: "결과값의 type deposit 이고, 금액은 기대값과 일치한다"
        verifyAll {
            result.getAmount() == expected.getAmount()
            result.getType() == TransactionType.DEPOSIT
        }
        when: "거래 내역 모두 제거할 때"
        transactionRepository.deleteAll()
        then: "조회시 거래 내역은 존재하지 않는다."
        verifyAll {
            transactionRepository.count() == 0
        }
    }
}
