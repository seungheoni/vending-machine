package com.example.display.fixture

import com.example.mongo.model.Display
import com.example.mongo.model.Drink
import org.bson.types.ObjectId


inline fun display(block: DisplayBuilder.() -> Unit = {}) =
    DisplayBuilder().apply(block).build()

class DisplayBuilder {

    var id: ObjectId? = null
    var position: Int = 0
    var drinkCode: String? = null
    var drinks: List<Drink>? = null

    fun build(): Display = Display(
        id,position,drinkCode,drinks
    )
}