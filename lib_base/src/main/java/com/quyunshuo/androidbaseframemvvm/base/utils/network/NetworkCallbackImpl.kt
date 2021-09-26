package com.quyunshuo.androidbaseframemvvm.base.utils.network

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities


/**
 * 实时监听网络状态变化的[ConnectivityManager.NetworkCallback]实现类
 *
 * @author Qu Yunshuo
 * @since 2021/7/10 5:38 下午
 */
class NetworkCallbackImpl : ConnectivityManager.NetworkCallback() {

    /**
     * 当前网络类型
     */
    var currentNetworkType: NetworkTypeEnum = NetworkTypeEnum.OTHER

    /**
     * 当前网络是否已连接
     */
    var isConnected = false

    /**
     * 注册的监听
     */
    var changeCall: NetworkStateChangeListener? = null

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        isConnected = true
        changeCall?.networkConnectChange(isConnected)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        isConnected = false
        changeCall?.networkConnectChange(isConnected)
    }

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities)
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {
            currentNetworkType = networkTypeConvert(networkCapabilities)
            changeCall?.networkTypeChange(currentNetworkType)
        }
    }

    /**
     * 网络类型转换
     */
    private fun networkTypeConvert(networkCapabilities: NetworkCapabilities): NetworkTypeEnum {
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                NetworkTypeEnum.TRANSPORT_CELLULAR
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                NetworkTypeEnum.TRANSPORT_WIFI
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                NetworkTypeEnum.TRANSPORT_BLUETOOTH
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                NetworkTypeEnum.TRANSPORT_ETHERNET
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> {
                NetworkTypeEnum.TRANSPORT_VPN
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> {
                NetworkTypeEnum.TRANSPORT_WIFI_AWARE
            }
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> {
                NetworkTypeEnum.TRANSPORT_LOWPAN
            }
            else -> NetworkTypeEnum.OTHER
        }
    }
}