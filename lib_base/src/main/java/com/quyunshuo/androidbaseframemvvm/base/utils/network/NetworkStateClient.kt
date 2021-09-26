package com.quyunshuo.androidbaseframemvvm.base.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.annotation.RequiresPermission
import com.quyunshuo.androidbaseframemvvm.base.BaseApplication

/**
 * 网络状态监听
 *
 * @author Qu Yunshuo
 * @since 2021/7/11 3:58 下午
 */
object NetworkStateClient {

    private val mNetworkCallback = NetworkCallbackImpl()

    /**
     * 注册网络监听客户端
     * @return Unit
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    fun register() {
        val build = NetworkRequest.Builder().build()
        val cm =
            BaseApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm.registerNetworkCallback(build, mNetworkCallback)
    }

    /**
     * 设置监听器
     * @param listener NetworkStateChangeListener 监听器
     * @return Unit
     */
    fun setListener(listener: NetworkStateChangeListener) {
        mNetworkCallback.changeCall = listener
    }

    /**
     * 移除监听器
     * @return Unit
     */
    fun removeListener() {
        mNetworkCallback.changeCall = null
    }

    /**
     * 获取网络类型
     * 当前网络类型是缓存的最近一次连接的网络类型，当无网络连接时其实拿到的是上一次的
     * 所以网络是否连接应该作为第一判断，确定网络是连接状态时再获取当前的网络类型，因为网络类型中没有设定无网
     * @return NetworkTypeEnum 参照[NetworkTypeEnum]
     */
    fun getNetworkType(): NetworkTypeEnum = mNetworkCallback.currentNetworkType

    /**
     * 网络是否连接
     * @return Boolean
     */
    fun isConnected(): Boolean = mNetworkCallback.isConnected
}