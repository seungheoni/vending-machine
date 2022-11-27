package com.example.order.repo

import com.example.order.fixture.order
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class OrderRepositoryTest(
    private val orderRepository: OrderRepository
) : BehaviorSpec({

    Given("order repository 테스트") {

        val purchaseOrder = "0020101"
        val drinkName = "콜라"
        val price = 2000L
        val order = order {
            this.code = purchaseOrder
            this.item = drinkName
            this.price = price
        }

        When("order 생성") {

            orderRepository.deleteAll();

            val expected = orderRepository.save(order);

            Then("생성한 주문서 코드는 " + purchaseOrder +"") {
                expected.code shouldBe purchaseOrder
            }
        }

        When("order 조회") {

            orderRepository.deleteAll();

            orderRepository.save(order);

            val expectedList = orderRepository.findAll();

            Then("영수증 조회 리스트 성공시 리스트 반환하고 담겨져 있는 원소는 1개") {
                expectedList.size shouldBe 1
            }
        }

        When("order 삭제") {

            orderRepository.deleteAll();

            val saveOrder = orderRepository.save(order);

            val expected = orderRepository.delete(saveOrder);

            Then("삭제 성공시 Unit을 반환.") {
                expected shouldBe Unit
            }
        }
    }

})
