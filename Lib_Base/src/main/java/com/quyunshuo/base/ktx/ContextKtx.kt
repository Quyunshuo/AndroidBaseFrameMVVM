package com.quyunshuo.base.ktx

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/17
 * @Class: ContextKtx
 * @Remark: Context相关的扩展方法
 */

/**
 * Toast
 * @param text CharSequence 类型文本
 */
fun Context.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

/**
 * Toast
 * @param resId String 类型资源id
 */
fun Context.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

/**
 * 居中Toast
 * @param text CharSequence 类型文本
 */
fun Context.centerToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, text, duration)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * 居中Toast
 * @param resId String 类型资源id
 */
fun Context.centerToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, resId, duration)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

/**
 * dp 转 px
 */
fun Context.dp2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * px 转 dp
 */
fun Context.px2dp(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * sp 转 px
 */
fun Context.sp2px(spValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (spValue * scale + 0.5f).toInt()
}

/**
 * px 转 sp
 */
fun Context.px2sp(pxValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (pxValue / scale + 0.5f).toInt()
}