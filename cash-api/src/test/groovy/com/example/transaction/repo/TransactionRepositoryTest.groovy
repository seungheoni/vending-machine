package com.example.transaction.repo

import com.example.mongo.config.MongoConfigurer
import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.spock.Testcontainers
import org.testcontainers.utility.DockerImageName
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@ActiveProfiles("test")
@DataMongoTest
@ContextConfiguration(classes = [MongoConfigurer.class])
class TransactionRepositoryTest extends Specification {

    @Shared
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))

    static {
        mongoDBContainer.start()
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> mongoDBContainer.getReplicaSetUrl())
    }
    @Autowired
    TransactionRepository transactionRepository

    def "거래 내역 데이터 조회"() {
        given: "500원의 입금 내역 데이터를 생성"
        Transaction expected = Transaction.ofDeposit(500)
        when: "입금 내역을 db 에 저장"
        Transaction result = transactionRepository.save(expected)
        then: "결과값의 type deposit 이고, 금액은 500원이다"
        verifyAll {
            result.getAmount() == expected.getAmount()
            result.getType() == TransactionType.DEPOSIT
        }


    }

}
