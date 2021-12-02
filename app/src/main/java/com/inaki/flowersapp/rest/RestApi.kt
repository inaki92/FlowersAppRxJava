package com.inaki.flowersapp.rest

import com.inaki.flowersapp.NetworkApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestApi {

    // here we are creating our retrofit instance to build the Network API
    val retrofit by lazy {
        Retrofit.Builder()
            // we provide the base URL
            .baseUrl(BASE_URL)
            // we provide the gson converter to convert the JSON to POJO class
            .addConverterFactory(GsonConverterFactory.create())
            // we provide the RxJava2 call adapter to transform our response into reactive
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            // we build our retrofit object
            .build()
            .create(NetworkApi::class.java)
    }

    companion object {
        // best practise to have the base URL (endpoint) provided by a static field
        private const val BASE_URL = "http://services.hanselandpetal.com/"
    }
}