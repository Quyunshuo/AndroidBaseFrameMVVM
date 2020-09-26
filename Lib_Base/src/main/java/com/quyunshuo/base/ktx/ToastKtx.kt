package com.quyunshuo.base.ktx

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/17
 * @Class: ToastKtx
 * @Remark: Toast相关的扩展方法
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
 * Toast
 * @param text CharSequence 类型文本
 */
fun Fragment.toast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(text, duration)
}

/**
 * Toast
 * @param resId String 类型资源id
 */
fun Fragment.toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.toast(resId, duration)
}

/**
 * 居中Toast
 * @param text CharSequence 类型文本
 */
fun Fragment.centerToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    context?.centerToast(text, duration)
}

/**
 * 居中Toast
 * @param resId String 类型资源id
 */
fun Fragment.centerToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    context?.centerToast(resId, duration)
}