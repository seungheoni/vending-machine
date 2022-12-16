package com.example.drink.service

import com.example.drink.fixture.drink
import com.example.drink.repo.DrinkRepository
import com.example.error.ErrorMessage
import com.example.error.exception.DrinkNotExistExcepion
import com.example.error.exception.DrinkSoldOutException
import com.example.mongo.model.Drink
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId
import java.util.*

class DrinkServiceImplTest: BehaviorSpec({

    val drinkRepository: DrinkRepository = mockk()
    val drinkService = DrinkServiceImpl(drinkRepository)

    beforeEach {
        clearAllMocks()
    }

    Given("drinkCode를 통해 drink 정보 가져오기"){
        When("drinkCode에 해당하는 음료수 정보가 없을떄") {

            val expected : Optional<Drink> = Optional.empty()
            val drinkCode = "fanta-fanta-500ml";

            every { drinkRepository.findByCode(drinkCode) } returns expected

            val exception = shouldThrowExactly<DrinkNotExistExcepion> {
                drinkService.getDrinkByDrinkCode(drinkCode)
            }

            Then("message: " + ErrorMessage.DRINK_NOT_EXIST ) {
                exception.reason shouldBe ErrorMessage.DRINK_NOT_EXIST
            }
        }

        When("drinkCode에 해당하는 음료수 정보가 품절일때") {

            val expected : Optional<Drink> = Optional.of(
                drink{
                    this.id = ObjectId.get()
                    this.code = "coca-cola-500ml"
                    this.name = "콜라"
                    this.price= 1000
                    this.quantity = 0
                })
            val drinkCode = "coca-cola-500ml";

            every { drinkRepository.findByCode(drinkCode) } returns expected
            val exception = shouldThrowExactly<DrinkSoldOutException> {
                drinkService.getDrinkByDrinkCode(drinkCode)
            }

            Then("message: " + ErrorMessage.DRINK_SOLD_OUT ) {
                exception.reason shouldBe ErrorMessage.DRINK_SOLD_OUT
            }
        }

        When("drinkCode에 해당하는 음료수 정보가 정상적으로 들어 있을때") {

            val expected : Optional<Drink> = Optional.of(
                    drink{
                        this.id = ObjectId.get()
                        this.code = "coca-cola-500ml"
                        this.name = "콜라"
                        this.price= 1000
                        this.quantity = 2
                })
            val drinkCode = "coca-cola-500ml";

            every { drinkRepository.findByCode(drinkCode) } returns expected
            val result = drinkService.getDrinkByDrinkCode(drinkCode)

            Then("조회한 음료수 정보의 code값은 같다" ) {
                result.code shouldBe expected.get().code
            }
        }
    }

})
