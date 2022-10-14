package com.example.cash.controller

import com.example.cash.dto.CashDepositPayLoad
import com.example.cash.dto.CashDepositView
import com.example.cash.service.CashService
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
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
) : StringSpec({

    "/cash/deposit 테스트" {
        val cash = CashDepositView(1000L)

        every { cashService.deposit(CashDepositPayLoad(1000L)) } returns CashDepositView(1000L)

        webTestClient
            .put()
            .uri("/cash/deposit")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(CashDepositPayLoad(1000L)))
            .exchange()
            .expectStatus().isOk
            .expectBody<CashDepositView>().isEqualTo(cash)
    }
})