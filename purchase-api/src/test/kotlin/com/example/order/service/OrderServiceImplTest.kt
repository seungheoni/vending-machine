package com.example.order.service

import com.example.mongo.model.Order
import com.example.mongo.model.TransactionType
import com.example.mongo.model.entitymapper.OrderMapper
import com.example.order.fixture.drink
import com.example.order.fixture.order
import com.example.order.fixture.transaction
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

    Given("order 주문서 생성 서비스 테스트") {

        val order2 = order {
            this.id = ObjectId.get()
            this.drinkCode = "coca-cola-500ml"
            this.item = "콜라"
            this.price = 2000L
            this.createDate = Instant.now()
        }

        every { orderRepository.save(any()) } returns order2

        When("주문서 생성") {

            val expected = orderServiceImpl.registerBy(drink,transaction)

            Then("order를 반환한다.") {
                expected.shouldBeTypeOf<Order>()
            }
        }
    }
})
