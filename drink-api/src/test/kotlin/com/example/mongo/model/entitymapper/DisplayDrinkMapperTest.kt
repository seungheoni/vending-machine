package com.example.mongo.model.entitymapper

import com.example.display.dto.DisplayDrinkView
import com.example.display.fixture.display
import com.example.drink.fixture.drink
import com.example.mongo.model.Display
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.bson.types.ObjectId
import org.mapstruct.factory.Mappers

class DisplayDrinkMapperTest : BehaviorSpec({


    Given("display drink mapper 테스트") {
        val displayDrinkMapper : DisplayDrinkMapper = Mappers.getMapper(DisplayDrinkMapper::class.java)
        When("display에 drinks 리스트가 존재한 상태에서 displayDrinkMapper로 displayDrinkView 변환하면") {
            val display : Display = display {
                this.id = ObjectId.get()
                this.position = 1
                this.drinkCode = "coca-cola-500ml"
                this.drinks = listOf(drink{
                    this.id = ObjectId.get()
                    this.code = "coca-cola-500ml"
                    this.price = 1000
                    this.name = "콜라"
                    this.quantity = 5
                })
            }

            val displayDrinkView : DisplayDrinkView = displayDrinkMapper.drinkToDisplayDrinkView(display);

            Then("displayDrinkView의 drinkCode: "+displayDrinkView.drinkCode + "display의 drinkCode: " + display.drinkCode +"는 같아야한다.") {
                displayDrinkView.drinkCode shouldBe display.drinkCode
            }
        }
    }

})
