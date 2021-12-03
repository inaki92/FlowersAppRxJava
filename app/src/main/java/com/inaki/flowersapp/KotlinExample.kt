package com.inaki.flowersapp

import android.util.Log
import com.inaki.flowersapp.model.FlowersItem

class KotlinExample(
    val email: String? = null,
) {

    internal fun example() {
        // here we have our nullabe variable
        val flower: FlowersItem? = null

        // here we are using elvis operator to assign empty string when flower is null
        var name: String? = flower?.name ?: email ?: ""

        // in kotlin we can assign if statements to the variables
        name = if (flower != null) {
            flower.name
        } else {
            ""
        }

        // the let block will check the flower value for non nullable
        // if null elvis operator will run the task
        flower?.let { myFlower ->
            myFlower.name?.let { flowerName ->
                name = flowerName
            }
        } ?: run {
            Log.d("", "")
            // more things here
        }

        fun myFunction(): String = run {
            return@run ""
        }
    }

    companion object {
        private var INSTANCE: KotlinExample? = null

        fun getInstance(): KotlinExample {
            if (INSTANCE == null) {
                INSTANCE = KotlinExample()
            }

            return INSTANCE as KotlinExample
        }
    }
}