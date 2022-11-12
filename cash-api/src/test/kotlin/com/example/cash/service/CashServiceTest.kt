package com.example.cash.service

import com.example.cash.dto.CashChangeView
import com.example.cash.repo.CashRepository
import com.example.error.exception.CashEmptyException
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

    Given("cashService change 테스트") {
        When("잔액이 0원 일떄 거스름돈을 반환하면") {
            val cash = Cash.of(0)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.change(any())
            } throws CashEmptyException()

            val exception = shouldThrowExactly<CashEmptyException> {
                cashService.change()
            }

            Then("message: 잔액이 없습니다") {
                exception.reason shouldBe "잔액이 없습니다."
            }
        }

        val balance = 1000L
        When("잔액이 "+balance+"원 일떄 거스름돈을 반환하면") {
            val cash = Cash.of(balance)
            val changeCash = cash.change()
            val expected = CashChangeView(balance)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.change(balance)
            } returns Transaction.ofChange(balance)

            every {
                cashRepository.save(changeCash)
            } returns changeCash

            val cashChangeView = cashService.change()
            Then(""+expected.amount + "원의 잔액 결과를 반환한다.") {
                cashChangeView.amount shouldBe expected.amount
            }
        }
    }
})
