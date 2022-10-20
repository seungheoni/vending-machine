package com.example.drink.repo

import com.example.mongo.model.Drink
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class DrinkRepositoryTest(
    private val drinkRepository: DrinkRepository
):BehaviorSpec({

    Given("drink repository 저장 테스트") {
        val drink = Drink.of("콜라",1000,10);
        When("콜라(drink) 상품 저장하면") {
            val result = drinkRepository.save(drink)
            Then("저장 성공시 콜라(drink)를 반환한다.") {
                result.shouldBeSameInstanceAs(drink)
            }
        }
    }

    Given("drink repository 삭제 테스트") {
        val drink = Drink.of("콜라",1000,10);
       drinkRepository.save(drink)
        When("콜라(drink) 상품 삭제하면") {
            val result = drinkRepository.delete(drink);
            Then("성공시 void를 반환한다.") {
                result shouldBe kotlin.Unit
            }
        }
    }

    Given("drink repository 조회 테스트") {
        val drink = Drink.of("콜라",1000,10);
        drinkRepository.save(drink)
        When("콜라(drink) 상품 조회하면") {
            val result = drinkRepository.findById(drink.id);
            Then("아까 저장한 콜라(drink)를 반환한다.") {
                result.get().id shouldBe drink.id
            }
        }
    }
})