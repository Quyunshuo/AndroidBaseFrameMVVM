package com.quyunshuo.base.utils

import android.app.Activity
import java.util.*

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/11
 * @Class: ActivityStackManager
 * @Remark: Activity 栈管理类
 */
object ActivityStackManager {

    // 管理栈
    val activityStack by lazy { Stack<Activity>() }

    /**
     * 添加 Activity 到管理栈
     * @param activity Activity
     */
    fun addActivityToStack(activity: Activity) {
        activityStack.push(activity)
    }

    /**
     * 弹出栈内指定Activity 不finish
     * @param activity Activity
     */
    fun popActivityToStack(activity: Activity) {
        if (!activityStack.empty()) {
            activityStack.forEach {
                if (it == activity) {
                    activityStack.remove(activity)
                    return
                }
            }
        }
    }

    /**
     * 返回到上一个 Activity 并结束当前 Activity
     */
    fun backToPreviousActivity() {
        if (!activityStack.empty()) {
            val activity = activityStack.pop()
            if (!activity.isFinishing) activity.finish()
        }
    }

    /**
     * 根据类名 判断是否是当前的 Activity
     * @param cls Class<*> 类名
     * @return Boolean
     */
    fun isCurrentActivity(cls: Class<*>): Boolean {
        val currentActivity = getCurrentActivity()
        return if (currentActivity != null) currentActivity.javaClass == cls else false
    }

    /**
     * 获取当前的 Activity
     */
    fun getCurrentActivity(): Activity? =
        if (!activityStack.empty()) activityStack.lastElement() else null

    /**
     * 结束一个栈内指定类名的 Activity
     * @param cls Class<*>
     */
    fun finishActivity(cls: Class<*>) {
        activityStack.forEach {
            if (it.javaClass == cls) {
                if (!it.isFinishing) it.finish()
                return
            }
        }
    }

    /**
     * 弹出其他 Activity
     */
    fun popOtherActivity() {
        val activityList = activityStack.toList()
        getCurrentActivity()?.run {
            activityList.forEach { activity ->
                if (this != activity) {
                    activityStack.remove(activity)
                    activity.finish()
                }
            }
        }
    }

    /**
     * 返回到指定 Activity
     */
    fun backToSpecifyActivity(activityClass: Class<*>) {
        val activityList = activityStack.toList()
        // 获取栈最上面的Activity
        val lastElement = activityStack.lastElement()
        activityList.forEach {
            // 如果栈内存在该Activity就进行下一步操作
            if (it.javaClass == activityClass) {
                // 判断最上面的Activity是不是指定的Activity 不是就finish
                if (lastElement.javaClass == activityClass) {
                    return
                } else {
                    activityStack.remove(lastElement)
                    lastElement.finish()
                }
            }
        }
    }
}