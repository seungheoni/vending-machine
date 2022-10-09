package com.example.transaction.fixture

import com.example.mongo.model.Transaction
import com.example.mongo.model.TransactionType
import org.bson.types.ObjectId
import java.time.Instant

inline fun transaction(block: TransactionBuilder.() -> Unit = {}) =
    TransactionBuilder().apply(block).build()

class TransactionBuilder {
    var id: ObjectId? = null
    var type: TransactionType? = null
    var amount: Long = 0
    var createDate: Instant? = null
    var updateDate: Instant? = null

    fun build(): Transaction = Transaction(
        id, type, amount, createDate, updateDate
    )
}
