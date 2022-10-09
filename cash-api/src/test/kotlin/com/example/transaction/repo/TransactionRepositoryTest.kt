package com.example.transaction.repo

import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class TransactionRepositoryTest(
    private val transactionRepository: TransactionRepository
) : BehaviorSpec({

    Given("거래 내역 데이터 저장") {
        When("입금 거래 내역인 경우") {
            transactionRepository.deleteAll()
            val expected = Transaction.ofDeposit(500)
            val result = transactionRepository.save(expected)
            Then("`type = DEPOSIT` 이다") {
                result.type shouldBe TransactionType.DEPOSIT
            }
        }
    }

    Given("거래 내역 데이터 조회") {
        When("입금 거래 내역인 경우") {
            transactionRepository.deleteAll()
            val expected = Transaction.ofDeposit(500)
            transactionRepository.save(expected)
            val result = transactionRepository.findAll()
            Then("거래 내역 리스트 개수는 1개이다") {
                result.size shouldBe 1
            }
            Then("`type = DEPOSIT` 인 리스트 갯수는 1개이다") {
                result.filter { it.type == TransactionType.DEPOSIT }.size shouldBe 1
            }
        }
    }
})
