package com.example.cash.controller

import com.example.cash.dto.CashChangeView
import com.example.cash.dto.CashDepositPayLoad
import com.example.cash.dto.CashDepositView
import com.example.cash.service.CashService
import com.example.error.ErrorBody
import com.example.error.ErrorMessage
import com.example.error.SimpleFieldError
import com.example.error.exception.CashEmptyException
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
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

    fun cashChangeClient() = webTestClient
        .put()
        .uri("/cash/change")
        .contentType(MediaType.APPLICATION_JSON)

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

        When("정의하지 않은 에러가 발생 했을 경우 (오버플로우)") {
            val amount = 9999999999999999
            val payload = CashDepositPayLoad(amount)
            val exchange = cashDepositClient(payload)
                .exchange()
            val expected = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase

            every { cashService.deposit(amount) } throws Exception()

            Then("status: 500 Internal Server Error") {
                exchange.expectStatus().is5xxServerError
            }
            Then("body: message" + expected + "이다") {
                exchange.expectBody<ErrorBody>().returnResult().responseBody!!.message shouldBe expected
            }
        }

        val balance = 1000
        When("지갑 잔액이 " +balance+ "원인 상태에서 입금하기 정상 처리된 경우") {
            val amount = 1000L
            val payload = CashDepositPayLoad(amount)
            val expected = CashDepositView(balance + amount)
            every { cashService.deposit(amount) } returns expected
            val exchange = cashDepositClient(payload)
                .exchange()
            Then("status: 200 Ok") {
                exchange.expectStatus().isOk
            }
            Then("body: balance 는 "+(balance + amount)+"원이 된다.") {
                exchange.expectBody<CashDepositView>().returnResult()
                    .responseBody!!.balance shouldBe (balance + amount)
            }
        }
    }

    Given("/cash/change") {
        val amount = 1000L
        When("자판기 잔액이 "+amount+"원인 상태에서 정상적으로 호출 했을때) "){
            every { cashService.change() } returns CashChangeView(amount)
            val exchange = cashChangeClient().exchange()
            Then("status: 200 OK") {
                exchange.expectStatus().isOk
            }
            Then("body: amount는 " + amount + "원이 된다.") {
                exchange.expectBody<CashChangeView>().returnResult()
                    .responseBody!!.amount shouldBe amount
            }
        }

        When("자판기 잔액이 0원인 상태에서 호출 했을때") {
            every { cashService.change() } throws CashEmptyException(HttpStatus.NOT_FOUND)
            val exchange = cashChangeClient().exchange()
            val expected = ErrorMessage.CASH_EMPTY
            Then("status: 404 NOT FOUND") {
                exchange.expectStatus().isNotFound
            }

            Then("body: message: " + expected + "이다") {
                exchange.expectBody<ErrorBody>().returnResult().responseBody!!.message shouldBe expected
            }
        }

        When("자판기 잔액이 "+amount+"원인 상태에서 500 server error 발생시") {
            every { cashService.change() } throws Exception()
            val exchange = cashChangeClient().exchange()
            val expected = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase
            Then("status: 500 Internal Server Error") {
                exchange.expectStatus().is5xxServerError
            }

            Then("body: message " + expected + "이다") {
                exchange.expectBody<ErrorBody>().returnResult().responseBody!!.message shouldBe expected
            }
        }
    }

})