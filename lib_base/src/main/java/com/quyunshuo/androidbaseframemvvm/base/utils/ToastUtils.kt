package com.quyunshuo.androidbaseframemvvm.base.utils

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.StringRes
import com.quyunshuo.androidbaseframemvvm.base.BaseApplication

private val mToastHandler by lazy { Handler(Looper.getMainLooper()) }

private var mToast: Toast? = null

@JvmOverloads
fun toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    postToast(text, duration)
}

@JvmOverloads
fun toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    postToast(getString(id), duration)
}

private fun postToast(text: String, duration: Int) {
    mToastHandler.post {
        setToast(text, duration)
        mToast?.show()
    }
}

private fun setToast(text: String, duration: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.context, text, duration)
        } else {
            mToast?.duration = duration
            mToast?.setText(text)
        }
    } else {
        if (mToast != null) {
            mToast?.cancel()
            mToast = null
        }
        mToast = Toast.makeText(BaseApplication.context, text, duration)
    }
}