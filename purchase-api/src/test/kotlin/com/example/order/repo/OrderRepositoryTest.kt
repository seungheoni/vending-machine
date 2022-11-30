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

        val drinkCode = "coca-cola-500ml"
        val item = "코카콜라 500ml"
        val price = 2000L
        val order = order {
            this.drinkCode = drinkCode
            this.item = item
            this.price = price
        }

        When("order 생성") {

            orderRepository.deleteAll();

            val expected = orderRepository.save(order);

            Then("생성한 주문서 코드는 " + drinkCode +"") {
                expected.drinkCode shouldBe drinkCode
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
