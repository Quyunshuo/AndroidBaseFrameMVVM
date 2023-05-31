package com.quyunshuo.androidbaseframemvvm.base.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.quyunshuo.androidbaseframemvvm.base.utils.ActivityStackManager
import com.quyunshuo.androidbaseframemvvm.base.utils.ForegroundBackgroundHelper

/**
 * Activity生命周期监听
 *
 * @author Qu Yunshuo
 * @since 4/20/21 9:10 AM
 */
class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {

    private val TAG = "ActivityLifecycle"

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        ActivityStackManager.addActivityToStack(activity)
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityCreated")
    }

    override fun onActivityStarted(activity: Activity) {
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityStarted")
        ForegroundBackgroundHelper.onActivityStarted()
    }

    override fun onActivityResumed(activity: Activity) {
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityResumed")
    }

    override fun onActivityPaused(activity: Activity) {
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityPaused")
    }

    override fun onActivityStopped(activity: Activity) {
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityStopped")
        ForegroundBackgroundHelper.onActivityStopped()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivitySaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        ActivityStackManager.popActivityToStack(activity)
        Log.e(TAG, "${activity.javaClass.simpleName} --> onActivityDestroyed")
    }
}