package com.example.display.controller

import com.example.display.dto.DisplayDrinkView
import com.example.display.service.DisplayService
import com.example.drink.enums.Status
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class DisplayControllerTest(
    @MockkBean private val displayService : DisplayService,
    private val webTestClient: WebTestClient
) : BehaviorSpec({

    Given("/drinks/display 테스트") {
        When("cashService 0원인 상태"){
            Then("요청하면 displaydrinkview 형태의 음료수 전시 데이터를 리턴한다."){
                every { displayService.displayDrinks } returns listOf(
                    DisplayDrinkView("coca-cola-500ml",1,Status.AVAILABLE,"콜라",1000L),
                    DisplayDrinkView("sik-hye-500ml",2,Status.AVAILABLE,"식혜",2000L),
                    DisplayDrinkView("soda-pop-500ml",3,Status.SOLDOUT,"사이다",1500L),
                )

                webTestClient
                    .get()
                    .uri("/drinks/display")
                    .exchange()
                    .expectStatus().isOk
                    .expectHeader().contentType(MediaType.APPLICATION_JSON)
                    .expectBody<List<DisplayDrinkView>>()
            }
        }
    }
})