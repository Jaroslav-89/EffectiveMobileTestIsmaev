package com.example.network.utills

import android.content.Context

class NetworkConnectionCheckerApi internal constructor(
    private val connectionChecker: NetworkConnectionCheckerImpl
) {
    val isConnected
        get() = connectionChecker.isConnected()
}

fun NetworkConnectionCheckerApi(applicationContext: Context): NetworkConnectionCheckerApi {
    val networkConnectionCheckerImpl =
        NetworkConnectionCheckerImpl(applicationContext)
    return NetworkConnectionCheckerApi(networkConnectionCheckerImpl)
}
