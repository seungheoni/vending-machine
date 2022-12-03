package com.example.mongo.model.entitymapper

import com.example.mongo.model.Order
import com.example.order.dto.OrderPayLoad
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.mapstruct.factory.Mappers

class OrderMapperTest(

) : BehaviorSpec({

    Given("order entity mapper 테스트") {
        val orderMapper : OrderMapper = Mappers.getMapper(OrderMapper::class.java)
        When("payload를 entityMapper를 통해 entity로 변환에 성공하면") {
            val payload = OrderPayLoad("coca-cola-500ml","콜라",2000L)
            val order : Order = orderMapper.orderPayLoadToOrder(payload)
            Then("order entity객체의 drinkCode는 " + payload.drinkCode + "와 같다") {
                order.drinkCode shouldBe payload.drinkCode
            }
        }
    }
})
