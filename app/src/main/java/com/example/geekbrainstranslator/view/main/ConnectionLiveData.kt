package com.example.geekbrainstranslator.view.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.MutableLiveData

class ConnectionLiveData(context: Context) {
    val isOnline: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

    fun check() {
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                isOnline.postValue(true)
                return
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                isOnline.postValue(true)
                return
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                isOnline.postValue(true)
                return
            }
        }
        isOnline.postValue(false)
    }

}