package com.example.display.controller

import com.example.display.dto.DisplayDrinkView
import com.example.display.fixture.display
import com.example.display.service.DisplayService
import com.example.mongo.model.Drink
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import org.bson.types.ObjectId
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
                every { displayService.displayDrinks } returns listOf((display {
                    id = ObjectId.get()
                    position = 1
                    drinkCode = "coke"
                    drinks = listOf<Drink>(Drink.of("coke", "콜라", 1000,5))
                }))

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