package com.example.transaction.service

import com.example.mongo.model.TransactionType
import com.example.transaction.fixture.transaction
import com.example.transaction.repo.TransactionRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class TransactionServiceTest : BehaviorSpec({

    val transactionRepository: TransactionRepository = mockk()
    val transactionServiceImpl = TransactionServiceImpl(transactionRepository)

    beforeEach {
        clearAllMocks()
    }

    Given("5000원의 입금 거래 내역 생성") {
        val amount = 5000L
        val expected = transaction {
            type = TransactionType.DEPOSIT
            this.amount = amount
        }
        When("transactionServiceImpl.deposit() 한 경우") {
            every {
                transactionRepository.save(expected)
            } returns expected
            val result = transactionServiceImpl.deposit(amount)
            Then("result.type = expected.type, result.amount = expected.amount") {
                result.type shouldBe expected.type
                result.amount shouldBe expected.amount
            }
        }
    }

    Given("거스름돈 반환 내역 생성") {
        val amount = 1000L
        val expected = transaction {
            type = TransactionType.CHANGE
            this.amount = amount
        }
        When(""+amount+"원에 대한 거스름돈 반환내역을 생성시") {
            every {
                transactionRepository.save(expected)
            } returns expected
            val result = transactionServiceImpl.change(amount)
            Then("result.type = "+ expected.type + ", result.amount = "+expected.amount) {
                result.type shouldBe expected.type
                result.amount shouldBe expected.amount
            }
        }
    }
})
