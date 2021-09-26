package com.quyunshuo.androidbaseframemvvm.base.utils.network

/**
 * 网络状态改变监听起
 *
 * @author Qu Yunshuo
 * @since 2021/7/11 4:56 下午
 */
interface NetworkStateChangeListener {

    /**
     * 网络类型更改回调
     * @param type NetworkTypeEnum 网络类型
     * @return Unit
     */
    fun networkTypeChange(type: NetworkTypeEnum)

    /**
     * 网络连接状态更改回调
     * @param isConnected Boolean 是否已连接
     * @return Unit
     */
    fun networkConnectChange(isConnected: Boolean)
}