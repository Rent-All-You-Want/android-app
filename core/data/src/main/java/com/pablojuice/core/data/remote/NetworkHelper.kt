package com.pablojuice.core.data.remote

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.pablojuice.core.data.remote.api.http.NetworkUtils.TIME_OUT_CONNECTION
import java.net.HttpURLConnection
import java.net.URL


object NetworkHelper {

    @SuppressLint("MissingPermission")
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
            val connection =
                URL("https://clients3.google.com/generate_204").openConnection() as HttpURLConnection
            connection.setRequestProperty("User-Agent", "Android")
            connection.setRequestProperty("Connection", "close")
            connection.connectTimeout = TIME_OUT_CONNECTION.toInt()
            connection.connect()
            connection.responseCode == 204 && connection.contentLength == 0
        } catch (e: Exception) {
            false
        }
    }
}