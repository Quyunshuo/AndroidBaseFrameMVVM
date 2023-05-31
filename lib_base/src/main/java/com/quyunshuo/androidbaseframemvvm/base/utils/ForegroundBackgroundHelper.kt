package com.quyunshuo.androidbaseframemvvm.base.utils

/**
 * 前后台切换帮助类，该类实现了前后台监听以及支持注册变化响应监听
 *
 * @see ForegroundBackgroundObserver
 * @see ForegroundBackgroundSubject
 *
 * @author Qu Yunshuo
 * @since 2023/5/31 14:22
 */
object ForegroundBackgroundHelper : ForegroundBackgroundSubject {

    private var mActivityStartCount = 0

    private var mIsForeground = false

    private val mObservers = mutableListOf<ForegroundBackgroundObserver>()

    fun onActivityStarted() {
        mActivityStartCount++
        if (mActivityStartCount == 1) {
            mIsForeground = true
            notifyObservers()
        }
    }

    fun onActivityStopped() {
        mActivityStartCount--
        if (mActivityStartCount == 0) {
            mIsForeground = false
            notifyObservers()
        }
    }

    /**
     * 通知所有订阅者状态变化
     */
    override fun notifyObservers() {
        mObservers.forEach {
            it.foregroundBackgroundNotify(mIsForeground)
        }
    }

    /**
     * 添加订阅者
     *
     * @param observer ForegroundBackgroundObserver
     */
    override fun addObserve(observer: ForegroundBackgroundObserver) {
        mObservers.add(observer)
    }

    /**
     * 移除订阅者
     *
     * @param observer ForegroundBackgroundObserver
     */
    override fun removeObserver(observer: ForegroundBackgroundObserver) {
        mObservers.remove(observer)
    }
}

/**
 * 订阅者需要实现的接口
 *
 * @author Qu Yunshuo
 * @since 2023/5/31 14:23
 */
interface ForegroundBackgroundObserver {
    fun foregroundBackgroundNotify(isForeground: Boolean)
}

/**
 * 被观察者抽象主题
 *
 * @author Qu Yunshuo
 * @since 2023/5/31 14:24
 */
interface ForegroundBackgroundSubject {
    fun notifyObservers()
    fun addObserve(observer: ForegroundBackgroundObserver)
    fun removeObserver(observer: ForegroundBackgroundObserver)
}