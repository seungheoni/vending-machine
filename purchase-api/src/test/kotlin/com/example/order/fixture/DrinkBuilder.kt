package com.example.order.fixture

import com.example.mongo.model.Drink
import org.bson.types.ObjectId

inline fun drink(block: DrinkBuilder.() -> Unit = {}) =
    DrinkBuilder().apply(block).build()


class DrinkBuilder {

    var id: ObjectId? = null
    var code: String? = null
    var name: String? = null
    var price: Long = 0L
    var quantity: Long = 0L

    fun build(): Drink = Drink(
        id,code,name,price,quantity
    )
}