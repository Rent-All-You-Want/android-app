package com.pablojuice.core.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkHelper {

    fun Context.isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val actNw = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
    }

    fun isOnline(): Boolean {
        return try {
            val p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com")
            (p1 as Object).wait(500)
            val returnVal = p1.waitFor()
            returnVal == 0
        } catch (e: Exception) {
            false
        }
    }
}