package com.quyunshuo.androidbaseframemvvm.base.utils

import android.os.Build
import android.os.Looper

/**
 * 线程相关工具类
 *
 * @author Qu Yunshuo
 * @since 2023/3/12 19:29
 */
object ThreadUtils {

    /**
     * 判断当前是否是主线程
     * 在 [Build.VERSION.SDK_INT] >= [Build.VERSION_CODES.M] 有一个简化方法来判断当前线程是否是主线程
     * ```
     * Looper.getMainLooper().isCurrentThread()
     * ```
     *
     * @return Boolean
     */
    fun isMainThread(): Boolean = Looper.getMainLooper().thread == Thread.currentThread()
}