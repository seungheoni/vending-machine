package com.example.purchase.controller

import com.example.purchase.dto.PurchaseDrinkPayLoad
import com.example.purchase.service.PurchaseService
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PurchaseControllerTest(
    @MockkBean private val purchaseService: PurchaseService,
    private val webTestClient: WebTestClient
) : BehaviorSpec({

    fun purchaseDrinkClient(payload: PurchaseDrinkPayLoad) = webTestClient
        .post()
        .uri("/purchases/drink")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(payload))

    Given("/purchases/drink") {

        When("payload에 음료수 구매 코드가 비어 있을떄") {

            val payload = PurchaseDrinkPayLoad("")
            val exchange = purchaseDrinkClient(payload).exchange()

            Then("status 400 bad request") {
                exchange.expectStatus().isBadRequest
            }
        }

        When("500 server 에러시") {

            val payload = PurchaseDrinkPayLoad("coca-cola-500ml")
            every { purchaseService.purchaseDrink(payload) } throws Exception()
            val exchange = purchaseDrinkClient(payload).exchange()

            Then("500 server error") {
                exchange.expectStatus().is5xxServerError
            }

        }

        When("음료수 구매 코드 전달 정상 처리시") {

            val payload = PurchaseDrinkPayLoad("coca-cola-500ml")

            every { purchaseService.purchaseDrink(payload) } returns 0
            val exchange = purchaseDrinkClient(payload).exchange()

            Then("status 204 NoContent") {
                exchange.expectStatus().isNoContent
            }
        }
    }

})
