package com.ad.newad

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * 网络相关工具类
 */
object NetUtils {

    /**
     * 当前网络是否是 Wi-Fi
     */
    fun currentNetIsWiFi(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false
    }
}