package com.quyunshuo.base.ktx

import android.view.View

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/17
 * @Class: ViewKtx
 * @Remark: View相关的扩展方法
 */


fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}
