package com.example.order.fixture

import com.example.mongo.model.Order
import org.bson.types.ObjectId
import java.time.Instant

inline fun order(block: OrderBuilder.() -> Unit = {}) =
    OrderBuilder().apply(block).build()

class OrderBuilder {
    var id: ObjectId? = null
    var purchaseOrder : String? = null
    var createDate : Instant? = null
    var description : String? = null
    var price : Long = 0

    fun build(): Order = Order(
        id,purchaseOrder, createDate, description, price
    )
}