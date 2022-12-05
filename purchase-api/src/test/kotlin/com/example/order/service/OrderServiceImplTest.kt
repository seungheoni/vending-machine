package com.example.order.service

import com.example.mongo.model.Order
import com.example.mongo.model.entitymapper.OrderMapper
import com.example.order.dto.OrderPayLoad
import com.example.order.fixture.order
import com.example.order.repo.OrderRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId
import org.mapstruct.factory.Mappers
import java.time.Instant

class OrderServiceImplTest(

) : BehaviorSpec({

    val orderRepository : OrderRepository = mockk()
    val orderMapper : OrderMapper = Mappers.getMapper(OrderMapper::class.java)
    val orderServiceImpl =  OrderServiceImpl(orderRepository,orderMapper)

    val code = "002010"
    val drinkName = "콜라"
    val price = 1000L

    val payload = OrderPayLoad(code,drinkName,price)

    Given("order 주문서 생성 서비스 테스트") {

        val order2 = order {
            this.id = ObjectId.get()
            this.drinkCode = code
            this.item = drinkName
            this.price = price
            this.createDate = Instant.now()
        }

        every { orderRepository.save(any()) } returns order2

        When("주문서 생성") {

            val expected = orderServiceImpl.registerBy(payload)

            Then("order를 반환한다.") {
                expected.shouldBeTypeOf<Order>()
            }
        }
    }
})
