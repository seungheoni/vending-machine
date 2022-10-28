package com.example.cash.service

import com.example.cash.dto.CashDepositView
import com.example.cash.repo.CashRepository
import com.example.mongo.model.Cash
import com.example.mongo.model.Transaction
import com.example.transaction.service.TransactionServiceImpl
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class CashServiceTest : BehaviorSpec({

    val transactionServiceImpl : TransactionServiceImpl = mockk();
    val cashRepository :  CashRepository = mockk()

    val cashService = CashServiceImpl(cashRepository,transactionServiceImpl)

    beforeEach {
        clearAllMocks()
    }

    Given("cashService deposit 테스트") {
        When("자판기의 잔액이 1000원인 상태에서 1000원을 추가로 넣으면") {
            val amount = 1000L
            val expected = Cash.of(amount)
            every {
                cashRepository.save(expected)
            } returns expected

            every {
                cashRepository.findFirstBy()
            } returns expected

            every {
                transactionServiceImpl.deposit(amount)
            } returns Transaction.ofDeposit(amount)

            val cashDepositView : CashDepositView = cashService.deposit(amount)
            Then("2000원의 잔액 결과를 반환한다. (또한 1000원에 해당하는 거래 내역을 생성한다.)") {
                cashDepositView.balance shouldBe 2000L
            }
        }
    }

})
