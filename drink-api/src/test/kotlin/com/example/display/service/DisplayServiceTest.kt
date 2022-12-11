package com.example.display.service

import com.example.display.fixture.display
import com.example.display.repo.DisplayRepository
import com.example.drink.fixture.drink
import com.example.mongo.model.entitymapper.DisplayDrinkMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId
import org.mapstruct.factory.Mappers

class DisplayServiceTest: BehaviorSpec ({

    val displayRepository : DisplayRepository = mockk()
    val displayDrinkMapper : DisplayDrinkMapper = Mappers.getMapper(DisplayDrinkMapper::class.java)
    val displayServiceImpl = DisplayServiceImpl(displayRepository,displayDrinkMapper)

    beforeEach {
        clearAllMocks()
    }

    Given("display와 drink의 룩업된 전체 리스트 조회 서비스") {
        When("displayServiceImpl의 displayDrinks 조회시") {
            val expected = listOf(

                display {
                this.id = ObjectId.get()
                this.position = 1
                this.drinkCode = "coca-cola-500ml"
                this.drinks = listOf(drink {
                    this.id = ObjectId.get()
                    this.code = "coca-cola-500ml"
                    this.name = "콜라"
                    this.price = 1000L
                    this.quantity = 3
                })},

                display {
                    this.id = ObjectId.get()
                    this.position = 1
                    this.drinkCode = "sik-hye-500ml"
                    this.drinks = listOf(drink {
                        this.id = ObjectId.get()
                        this.code = "sik-hye-500ml"
                        this.name = "식혜"
                        this.price = 1500L
                        this.quantity = 3
                })},

                display {
                    this.id = ObjectId.get()
                    this.position = 1
                    this.drinkCode = "soda-pop-500ml"
                    this.drinks = listOf(drink {
                        this.id = ObjectId.get()
                        this.code = "soda-pop-500ml"
                        this.name = "사이다"
                        this.price = 1000L
                        this.quantity = 0
                    })}
            )

            every {
                displayRepository.findWithDrink()
            } returns expected

            val result = displayServiceImpl.displayDrinks
            Then("drink 컬렉션과 룩업된 Display 객체 리스트를 반환한다.") {
                result.get(0).drinkCode shouldBe expected.get(0).drinkCode
            }
        }
    }
})