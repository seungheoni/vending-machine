package com.example.cash.repo

import com.example.mongo.model.Cash
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class CashRepositoryTest(
    private val cashRepository : CashRepository
) : BehaviorSpec({

    Given("입금 상태가 0원의 상태에서") {
        cashRepository.deleteAll()
        When("1000원을 입금하면") {
            val result = cashRepository.save(Cash.of(1000L))
            Then("1000원의 잔액데이터를 보여줘야한다.") {
                result.balance shouldBe 1000L
            }
        }
    }

    Given("잔액 데이터 1개 존재한 상태에서") {
        cashRepository.deleteAll()
        cashRepository.save(Cash.of(1000L))
        When("잔액 전체 데이터를 조회 하면") {
            val result = cashRepository.findAll()
            Then("거래 내역 리스트는 1개를 보여준다.") {
                result.size shouldBe 1
            }
        }
    }

    Given("조회시 잔액 데이터 1개 존재하고") {
        cashRepository.deleteAll()
        val cash = cashRepository.save(Cash.of(1000L))
        When("조회된 잔액 데이터를 삭제 하고 전체 조회를 하면"){
            cashRepository.delete(cash)
            val result = cashRepository.findAll()
            Then("결과 데이터는 0이 나온다.") {
                result.size shouldBe 0
            }
        }
    }

    Given("잔액 데이터 1000원이 존재하고") {
        val cash = Cash.of(1000L)
            .let(cashRepository::save)
        When("findFirstBy를 사용하여 잔액 정보를 가져오면") {
            val result = cashRepository.findFirstBy()
            Then("남아있는 잔액 데이터를 1건을 가져온다. ") {
                result.get().id shouldBe cash.id
            }
        }
    }
})
