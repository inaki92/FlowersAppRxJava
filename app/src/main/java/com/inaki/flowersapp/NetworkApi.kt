package com.inaki.flowersapp

import com.inaki.flowersapp.model.Flowers
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkApi {

    // here we are doing a get call to server in order to get the flowers.json
    @GET("feeds/flowers.json")
    // Single refers to a single object of the type array getting from the API
    // this function is going to be reactive
    fun fetchFlowers(): Single<Flowers>
}