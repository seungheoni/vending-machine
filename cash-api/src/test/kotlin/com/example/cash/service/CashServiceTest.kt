package com.example.cash.service

import com.example.cash.repo.CashRepository
import com.example.mongo.model.Cash
import com.example.mongo.model.Transaction
import com.example.transaction.service.TransactionServiceImpl
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import java.util.*
import kotlin.NoSuchElementException

class CashServiceTest : BehaviorSpec({

    val transactionServiceImpl : TransactionServiceImpl = mockk()
    val cashRepository :  CashRepository = mockk()

    val cashService = CashServiceImpl(cashRepository,transactionServiceImpl)

    beforeEach {
        clearAllMocks()
    }

    Given("cashService deposit 테스트") {

        val amount = 1000L
        val balance = 1000L
        When("자판기의 잔액 데이터가 존재하지 않을때 "+amount+"원을 입금을 하면") {

            val cash : Optional<Cash> = Optional.empty()

            every {
                cashRepository.findFirstBy()
            } returns cash

            every {
                transactionServiceImpl.deposit(amount)
            } returns Transaction.ofDeposit(amount)

            val exception = shouldThrowExactly<NoSuchElementException> {
                cashService.deposit(amount)
            }

            Then("java.util.NoSuchElementException: No value present 오류가 발생한다.") {
                exception.message shouldBe "No value present"
            }
        }

        When("자판기의 잔액이 "+ balance +"원인 상태에서 "+ amount + "원을 추가로 입금하면") {
            val cash = Cash.of(balance)
            val depositCash = cash.deposit(amount)
            val expected = depositCash.toCashDepositView()

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.deposit(amount)
            } returns Transaction.ofDeposit(amount)

            every {
                cashRepository.save(depositCash)
            } returns depositCash

            val cashDepositView = cashService.deposit(amount)
            Then(""+expected.balance+"원의 잔액 결과를 반환한다.") {
                cashDepositView.balance shouldBe expected.balance
            }
        }
    }

})
