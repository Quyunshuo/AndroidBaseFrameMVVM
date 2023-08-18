package com.quyunshuo.androidbaseframemvvm.base.ktx

import android.app.Activity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle

/**
 * 设置当前 [Activity] 是否允许截屏操作
 *
 * @receiver [Activity]
 * @param isAllow Boolean 是否允许截屏
 */
fun Activity.isAllowScreenCapture(isAllow: Boolean) {
    if (isAllow) {
        window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
    } else {
        window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
}

/**
 * 判断当前 Activity 是否是 [Lifecycle.State.RESUMED]
 */
fun AppCompatActivity.isResumed(): Boolean = lifecycle.currentState == Lifecycle.State.RESUMED
