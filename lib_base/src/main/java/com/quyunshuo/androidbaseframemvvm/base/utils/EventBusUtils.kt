package com.quyunshuo.androidbaseframemvvm.base.utils

import org.greenrobot.eventbus.EventBus

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/29
 * @Class: EventBusUtil
 * @Remark: EventBus工具类
 */
object EventBusUtils {

    /**
     * 订阅
     * @param subscriber 订阅者
     */
    fun register(subscriber: Any) = EventBus.getDefault().register(subscriber)

    /**
     * 解除注册
     * @param subscriber 订阅者
     */
    fun unRegister(subscriber: Any) = EventBus.getDefault().unregister(subscriber)

    /**
     * 发送普通事件
     * @param event 事件
     */
    fun postEvent(event: Any) = EventBus.getDefault().post(event)

    /**
     * 发送粘性事件
     * @param stickyEvent 粘性事件
     */
    fun postStickyEvent(stickyEvent: Any) = EventBus.getDefault().postSticky(stickyEvent)

    /**
     * 手动获取粘性事件
     * @param stickyEventType 粘性事件
     * @param <T>             事件泛型
     * @return 返回给定事件类型的最近粘性事件
     */
    fun <T> getStickyEvent(stickyEventType: Class<T>): T =
        EventBus.getDefault().getStickyEvent(stickyEventType)

    /**
     * 手动删除粘性事件
     * @param stickyEventType 粘性事件
     * @param <T>             事件泛型
     * @return 返回给定事件类型的最近粘性事件
     */
    fun <T> removeStickyEvent(stickyEventType: Class<T>): T =
        EventBus.getDefault().removeStickyEvent(stickyEventType)
}