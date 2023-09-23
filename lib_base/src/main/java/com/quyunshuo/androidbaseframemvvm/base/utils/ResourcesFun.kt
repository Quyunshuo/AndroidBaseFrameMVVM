package com.quyunshuo.androidbaseframemvvm.base.utils

import androidx.annotation.StringRes
import com.quyunshuo.androidbaseframemvvm.base.BaseApplication.Companion.application as app

fun getString(@StringRes stringRes: Int): String {
    return app.getString(stringRes)
}