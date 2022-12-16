package com.example.drink.service

import com.example.drink.fixture.drink
import com.example.drink.repo.DrinkRepository
import com.example.error.ErrorMessage
import com.example.error.exception.DrinkNotExistExcepion
import com.example.error.exception.DrinkSoldOutException
import com.example.mongo.model.Drink
import com.example.mongo.model.entitymapper.DrinkMapper
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId
import org.mapstruct.factory.Mappers
import java.util.*

class DrinkServiceImplTest : BehaviorSpec({

    val drinkRepository: DrinkRepository = mockk()
    val drinkMapper: DrinkMapper = Mappers.getMapper(DrinkMapper::class.java)
    val drinkService = DrinkServiceImpl(drinkRepository, drinkMapper)

    beforeEach {
        clearAllMocks()
    }

    Given("drinkCode를 통해 drink 정보 가져오기") {
        When("drinkCode에 해당하는 음료수 정보가 없을떄") {

            val expected: Optional<Drink> = Optional.empty()
            val drinkCode = "fanta-fanta-500ml"

            every { drinkRepository.findByCode(drinkCode) } returns expected

            val exception = shouldThrowExactly<DrinkNotExistExcepion> {
                drinkService.getByDrinkCode(drinkCode)
            }

            Then("message: " + ErrorMessage.DRINK_NOT_EXIST) {
                exception.reason shouldBe ErrorMessage.DRINK_NOT_EXIST
            }
        }

        When("drinkCode에 해당하는 음료수 정보가 정상적으로 들어 있을때") {

            val expected: Optional<Drink> = Optional.of(
                drink {
                    this.id = ObjectId.get()
                    this.code = "coca-cola-500ml"
                    this.name = "콜라"
                    this.price = 1000
                    this.quantity = 2
                })
            val drinkCode = "coca-cola-500ml"

            every { drinkRepository.findByCode(drinkCode) } returns expected
            val result = drinkService.getByDrinkCode(drinkCode)

            Then("조회한 음료수 정보의 code값은 같다") {
                result.code shouldBe expected.get().code
            }
        }
    }

    Given("drink 수량 줄이기") {
        When("수량이 없는 경우") {
            val drink = drink {
                this.id = ObjectId.get()
                this.code = "coca-cola-500ml"
                this.name = "콜라"
                this.price = 1000
                this.quantity = 0
            }

            coEvery {
                drinkService.getByDrinkCode(drink.code)
            } returns drink

            val exception = shouldThrowExactly<DrinkSoldOutException> {
                drinkService.bringOut(drink.code)
            }

            Then("message: " + ErrorMessage.DRINK_SOLD_OUT) {
                exception.reason shouldBe ErrorMessage.DRINK_SOLD_OUT
            }
        }

        When("수량이 충분한 경우") {
            val drink = drink {
                this.id = ObjectId.get()
                this.code = "coca-cola-500ml"
                this.name = "콜라"
                this.price = 1000
                this.quantity = 2
            }

            val updateDrink = drinkMapper.reduceQuantity(drink)

            coEvery {
                drinkService.getByDrinkCode(drink.code)
            } returns drink

            coEvery {
                drinkRepository.save(updateDrink)
            } returns updateDrink

            val result = drinkService.bringOut(drink.code)

            Then("음료수의 수량이 감소한다") {
                result.quantity shouldBeLessThan drink.quantity
            }
        }
    }
})
