package com.quyunshuo.androidbaseframemvvm.base.utils.network

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * 自动注册网络状态监听
 * 使用的是[androidx.lifecycle.LifecycleObserver]来同步生命周期
 *
 * @author Qu Yunshuo
 * @since 2021/7/11 4:56 下午
 */
class AutoRegisterNetListener constructor(listener: NetworkStateChangeListener) :
    LifecycleObserver {

    /**
     * 当前需要自动注册的监听器
     */
    private var mListener: NetworkStateChangeListener? = null

    init {
        mListener = listener
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun register() {
        mListener?.run { NetworkStateClient.setListener(this) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun unregister() {
        NetworkStateClient.removeListener()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clean() {
        NetworkStateClient.removeListener()
        mListener = null
    }
}