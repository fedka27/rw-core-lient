package me.rocketwash.client.utils

import android.content.Context
import android.net.ConnectivityManager
import me.rocketwash.client.data.exceptions.NetworkException
import me.rocketwash.client.providers.AppProvider

fun Context.isOnline(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = connectivityManager.activeNetworkInfo
    return info != null && info.isConnectedOrConnecting
}

fun AppProvider.isOnlineOrThrow() {
    if (!isConnectionInternet()) throw NetworkException()
}