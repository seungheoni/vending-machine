package com.example.order.fixture

import com.example.mongo.model.Order
import org.bson.types.ObjectId
import java.time.Instant

inline fun order(block: OrderBuilder.() -> Unit = {}) =
    OrderBuilder().apply(block).build()

class OrderBuilder {
    var id: ObjectId? = null
    var drinkCode : String? = null
    var createDate : Instant? = null
    var item : String? = null
    var price : Long = 0

    fun build(): Order = Order(
        id,drinkCode,item,price,createDate
    )
}