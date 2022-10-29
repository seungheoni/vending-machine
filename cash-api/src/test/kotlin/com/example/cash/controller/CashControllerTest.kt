package com.example.cash.controller

import com.example.cash.dto.CashDepositPayLoad
import com.example.cash.dto.CashDepositView
import com.example.cash.service.CashService
import com.example.error.ErrorBody
import com.example.error.ErrorMessage
import com.example.error.SimpleFieldError
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CashControllerUnitTest(
    @MockkBean private val cashService: CashService,
    private val webTestClient: WebTestClient
) : BehaviorSpec({

    fun cashDepositClient(payload: CashDepositPayLoad) = webTestClient
            .put()
            .uri("/cash/deposit")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(payload))

    Given("/cash/deposit") {
        When("payload 의 필드 값이 유효하지 않은 경우") {
            val payload = CashDepositPayLoad(-1L)
            val exchange = cashDepositClient(payload)
                .exchange()
            val expected = listOf(SimpleFieldError("amount", ErrorMessage.INVALID_VALIDATION))

            Then("status: 400 Bad Request") {
                exchange.expectStatus().isBadRequest
            }
            Then("body: fields 가 기대값과 동일하다.") {
                exchange.expectBody<ErrorBody>().returnResult().responseBody!!.should { errorBody ->
                    errorBody.fields shouldBe expected
                }
            }
        }

        When("cashService 0원인 상태에서 1000원을 넣으면 1000원 잔액 데이터를 보여준다") {
            val cash = CashDepositView(1000L)
            every { cashService.deposit(1000L) } returns CashDepositView(1000L)

            Then("0원인 상태에서 payload 규격에 맞춘 1000원을 요청하면 잔액 1000원을 보여준다.") {

                webTestClient
                    .put()
                    .uri("/cash/deposit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(CashDepositPayLoad(-1L)))
                    .exchange()
                    .expectStatus().isOk
                    .expectBody<CashDepositView>()
                    .isEqualTo(cash)
            }
        }
    }
})