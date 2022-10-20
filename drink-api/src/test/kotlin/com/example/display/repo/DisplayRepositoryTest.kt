package com.example.display.repo

import com.example.mongo.model.Display
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.bson.types.ObjectId
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest

@DataMongoTest
class DisplayRepositoryTest(
    private val displayRepository: DisplayRepository
):BehaviorSpec ({

    Given("display 데이터 1개를 생성하고") {
        val display = Display.of(0, ObjectId.get())
        When("display 데이터 1개를 저장하면") {
            val result = displayRepository.save(display);
            Then("1개의 Display를 성공적으로 반환한다.") {
                result.shouldBeInstanceOf<Display>()
            }
        }
    }

    Given("display 데이터를 생성하고"){
        val display = Display.of(0, ObjectId.get())
        displayRepository.save(display)
        When("display 데이터 1개를 삭제하면") {
            val result = displayRepository.delete(display);
            Then("성공시 void를 반환한다.") {
                result shouldBe kotlin.Unit
            }
        }
    }
})