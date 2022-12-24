package com.example.mongo.model.entitymapper

import com.example.mongo.model.Drink
import com.example.mongo.model.Order
import com.example.mongo.model.TransactionType
import com.example.order.dto.OrderPayLoad
import com.example.order.fixture.drink
import com.example.order.fixture.transaction
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.bson.types.ObjectId
import org.mapstruct.factory.Mappers

class OrderMapperTest(

) : BehaviorSpec({

    Given("order entity mapper 테스트") {
        val orderMapper : OrderMapper = Mappers.getMapper(OrderMapper::class.java)
        When("payload를 entityMapper를 통해 entity로 변환에 성공하면") {

            val drink = drink {
                this.id = ObjectId.get()
                this.code = "coca-cola-500ml"
                this.name = "콜라"
                this.price = 2000L
            }

            val transaction = transaction {
                this.id = ObjectId.get()
                this.type = TransactionType.CHARGE
                this.amount = 2000L
            }

            val order : Order = orderMapper.drinkTransactionToOrder(drink,transaction)
            Then("order entity객체의 drinkCode는 " + drink.code + "와 같다") {
                order.drinkCode shouldBe drink.code
            }
        }
    }
})
