package com.inaki.flowersapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.inaki.flowersapp.model.Flowers
import com.inaki.flowersapp.rest.RestApi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    // this variable is getting the network manager from the system service
    private val networkManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // here we are assigning our active network
        val activeNetwork = networkManager.activeNetworkInfo

        // here we are checking if the network is connected and not null
        activeNetwork?.let { myNetwork ->
            if (myNetwork.isConnected) {
                // here we need to create the background task to fetch the data
                RestApi().retrofit.fetchFlowers()
                    // here everytime you are using subscribeOn you switch to a worker thread
                    .subscribeOn(Schedulers.io())
                    // everytime you use observeOn you can get the data in
                    // the main thread by using AndroidSchedulers
                    .observeOn(AndroidSchedulers.mainThread())
                    // when you subscribe this is the time you can handle
                    // the success or error response or the data.
                    .subscribe(
                        // here it goes the success with the Flower object
                        { flowers -> handleSuccess(flowers) },
                        // here it goes the error with a Throwable object
                        { error -> handleError(error) }
                    )
            } else {
                // we display a error message by making a toast
                Toast.makeText(baseContext, "Connectivity issues", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleError(error: Throwable) {

        Toast.makeText(baseContext, error.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccess(flowers: Flowers) {
        Toast.makeText(baseContext, flowers[0].name, Toast.LENGTH_LONG).show()
    }
}