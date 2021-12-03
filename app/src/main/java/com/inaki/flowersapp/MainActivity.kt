package com.inaki.flowersapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.inaki.flowersapp.databinding.ActivityMainBinding
import com.inaki.flowersapp.model.Example
import com.inaki.flowersapp.model.Flowers
import com.inaki.flowersapp.rest.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    // here we are declaring our binding variable
    // lateinit is basically a late initialization
    private lateinit var binding: ActivityMainBinding

    // how to use shared preferences
    var x: String?
    get() {
        // accessing shared preferences
        baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE).apply {
            // getting the String from shared preferences with the exact same KEY
            return getString("MY_STRING", null)
        }
    }
    set(value) {
        // here I am accessing the shared preferences in private mode
        baseContext.getSharedPreferences("MY_SHARED_PREFS", MODE_PRIVATE).apply {
            // applying an editor to put data in the shared preferences file
            edit().apply {
                // putting the String into the file
                putString("MY_STRING", value)
                // we need to call apply() in order to apply the putString
            }.apply()
        }
    }

    // this variable is getting the network manager from the system service
    // by lazy immutable variable that know how to initialize its value
    private val networkManager by lazy {
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // here we assign the value inflating the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        // we set content provided from the root layout
        setContentView(binding.root)

        x = "email@email.com"

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
                        { flowers -> handleSuccess(flowers) {
                            Log.d("MainActivity", it.toString())
                        } },
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

        val list: MutableList<Int> = mutableListOf()

        list.forEachIndexed { index, s ->

        }

        // this is an enum to know any data state
        val example: Example = Example.SECOND

        // we have a when statement to check what to do when getting that enum
        when(example) {
            Example.FIRST -> ""
            Example.SECOND -> ""
            Example.THIRD -> ""
        }

        val string = ""

        string.myExtension()
    }

    private fun myHighOrderFunction(action: (Boolean) -> Unit) {
        action.invoke(true)

        KotlinExample().example()
        KotlinExample.getInstance()
    }

    private fun handleSuccess(flowers: Flowers, action: (Boolean) -> Unit) {
        Log.d("MainActivity", x.toString())
        action.invoke(true)
        timeToMillis(10)
        Toast.makeText(baseContext, flowers[0].name, Toast.LENGTH_LONG).show()
    }
}