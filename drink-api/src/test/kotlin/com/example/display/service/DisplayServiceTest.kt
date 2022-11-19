package com.example.display.service

import com.example.display.repo.DisplayRepository
import com.example.mongo.model.Display
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class DisplayServiceTest: BehaviorSpec ({

    val displayRepository : DisplayRepository = mockk()
    val displayServiceImpl = DisplayServiceImpl(displayRepository)

    beforeEach {
        clearAllMocks()
    }

    Given("display와 drink의 룩업된 전체 리스트 조회 서비스") {
        When("displayServiceImpl의 displayDrinks 조회시") {
            val expected = listOf(Display.of(1, "code"))
            every {
                displayRepository.findWithDrink()
            } returns expected
            val result = displayServiceImpl.displayDrinks
            Then("drink 컬렉션과 룩업된 Display 객체 리스트를 반환한다.") {
                result.shouldBeSameInstanceAs(expected)
            }
        }
    }
})