package com.example.transaction.fixture

import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import org.bson.types.ObjectId

import java.time.Instant

class TransactionBuilder {

    ObjectId id = ObjectId.get()
    TransactionType type
    Long amount
    Instant createDate = Instant.now()
    Instant updateDate = Instant.now()

    /**
     * This method accepts a closure which is essentially the DSL. Delegate the closure methods to
     * the DSL class so the calls can be processed
     */
    static TransactionBuilder make(closure) {
        TransactionBuilder builder = new TransactionBuilder()
        // any method called in closure will be delegated to the memoDsl class
        closure.delegate = builder
        closure()
        return builder
    }

    Transaction build() {
        return new Transaction(id, type, amount, createDate, updateDate)
    }

    def id(ObjectId id) {
        this.id = id
    }

    def type(TransactionType type) {
        this.type = type
    }

    def amount(Long amount) {
        this.amount = amount
    }

    def createDate(Instant createDate) {
        this.createDate = createDate
    }

    def updateDate(Instant updateDate) {
        this.updateDate = updateDate
    }
}
