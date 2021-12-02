package com.inaki.flowersapp.model

import com.google.gson.annotations.SerializedName

/**
 * This data class is our POJO parsed from JSON
 */
data class FlowersItem(
    // here Gson library is serializing the values from JSON
    @SerializedName("category")
    val category: String,
    // the serialized name has to be the same as the key from the JSON
    // or the name of your property needs to be the same as the JSON
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("productId")
    val productId: Int
)