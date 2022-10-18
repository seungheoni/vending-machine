package com.example.display.service

import com.example.display.repo.DisplayRepository
import com.example.mongo.model.Display
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.bson.types.ObjectId

class DisplayServiceTest: BehaviorSpec ({

    val displayRepository : DisplayRepository = mockk()
    val displayServiceImpl : DisplayServiceImpl = DisplayServiceImpl(displayRepository)

    beforeEach {
        clearAllMocks()
    }

    Given("display와 drink의 룩업된 전체 리스트 조회 서비스") {
        When("displayServiceImpl의 displayDrinks 조회시") {
            every {
                displayRepository.findWithDrink();
            } returns listOf(Display.of(1,ObjectId.get()))
            val result = displayServiceImpl.displayDrinks
            Then("drink 컬렉션과 룩업된 Display 객체 리스트를 반환한다.") {
                result is List<Display>
            }
        }
    }
})