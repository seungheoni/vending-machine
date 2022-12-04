package com.example.cash.service

import com.example.cash.dto.CashChangeView
import com.example.cash.repo.CashRepository
import com.example.error.ErrorMessage
import com.example.error.exception.CashEmptyException
import com.example.error.exception.CashNotEnoughException
import com.example.mongo.model.Cash
import com.example.mongo.model.entitymapper.CashMapper
import com.example.mongo.model.entitymapper.TransactionMapper
import com.example.transaction.service.TransactionServiceImpl
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.mapstruct.factory.Mappers
import java.util.*

class CashServiceTest : BehaviorSpec({

    val transactionServiceImpl : TransactionServiceImpl = mockk()
    val cashRepository :  CashRepository = mockk()

    val cashMapper : CashMapper =  Mappers.getMapper(CashMapper::class.java)
    val transactionMapper : TransactionMapper =  Mappers.getMapper(TransactionMapper::class.java)

    val cashService = CashServiceImpl(cashRepository,transactionServiceImpl,cashMapper)

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
            } returns transactionMapper.ofDeposit(amount)

            val exception = shouldThrowExactly<NoSuchElementException> {
                cashService.deposit(amount)
            }

            Then("java.util.NoSuchElementException: No value present 오류가 발생한다.") {
                exception.message shouldBe "No value present"
            }
        }

        When("자판기의 잔액이 "+ balance +"원인 상태에서 "+ amount + "원을 추가로 입금하면") {
            val cash = Cash.of(balance)
            val depositCash = cashMapper.deposit(cash,amount)
            val expected = cashMapper.CashToCashDepositView(depositCash)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.deposit(amount)
            } returns transactionMapper.ofDeposit(amount)

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
            val changeCash = cashMapper.change(cash)
            val expected = CashChangeView(balance)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.change(balance)
            } returns transactionMapper.ofChange(balance)

            every {
                cashRepository.save(changeCash)
            } returns changeCash

            val cashChangeView = cashService.change()
            Then(""+expected.amount + "원의 잔액 결과를 반환한다.") {
                cashChangeView.amount shouldBe expected.amount
            }
        }
    }

    Given("cashService charge 테스트") {
        val amount = 1000L
        val balance = 1000L
        When("입금 금액이 부족한 경우") {
            val cash = Cash.of(1)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            val exception = shouldThrowExactly<CashNotEnoughException> {
                cashService.charge(amount)
            }

            Then("message: 금액이 부족합니다") {
                exception.reason shouldBe ErrorMessage.CASH_NOT_ENOUGH
            }
        }

        When("잔액이 " + balance + "원인 경우" + amount + "원을 사용할떄") {
            val cash = Cash.of(balance)
            val chargeCash = cashMapper.charge(cash,amount)

            every {
                cashRepository.findFirstBy()
            } returns Optional.of(cash)

            every {
                transactionServiceImpl.charge(balance)
            } returns transactionMapper.ofCharge(balance)

            every {
                cashRepository.save(chargeCash)
            } returns chargeCash

            val result = cashService.charge(amount)
            Then("return 값이 존재하지 않는다") {
                result shouldBe Unit
            }
        }
    }
})
