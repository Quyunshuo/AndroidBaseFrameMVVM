package com.quyunshuo.androidbaseframemvvm.base.ktx

import android.animation.Animator
import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup
import com.quyunshuo.androidbaseframemvvm.base.view.OnSingleClickListener

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/1
 * @Class: ViewKtx
 * @Remark: View相关的扩展方法
 */

/*************************************** View可见性相关 ********************************************/
/**
 * 隐藏View
 * @receiver View
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * 显示View
 * @receiver View
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * View不可见但存在原位置
 * @receiver View
 */
fun View.invisible() {
    visibility = View.INVISIBLE
}

/**
 * 设置 View 为 [View.VISIBLE]
 * 如果 [isVisible] 值为true，将 [View.setVisibility] 设置为 [View.VISIBLE],反之为 [View.GONE]
 *
 * @receiver View
 * @param isVisible Boolean 是否显示
 */
fun View.setVisible(isVisible: Boolean) {
    if (isVisible) visible() else gone()
}

/**
 * 设置 View 为 [View.GONE]
 * 如果 [isGone] 值为true，将 [View.setVisibility] 设置为 [View.GONE],反之为 [View.VISIBLE]
 *
 * @receiver View
 * @param isGone Boolean 是否隐藏
 */
fun View.setGone(isGone: Boolean) {
    if (isGone) visible() else gone()
}

/*************************************** View宽高相关 ********************************************/
/**
 * 设置 View 的高度
 * @receiver View
 * @param height Int 目标高度
 * @return View
 */
fun View.height(height: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置View的宽度
 * @receiver View
 * @param width Int 目标宽度
 * @return View
 */
fun View.width(width: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    layoutParams = params
    return this
}

/**
 * 设置View的宽度和高度
 * @receiver View
 * @param width Int 要设置的宽度
 * @param height Int 要设置的高度
 * @return View
 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置宽度，带有过渡动画
 * @param targetValue 目标宽度
 * @param duration 时长
 * @param action 可选行为
 * @return 动画
 */
fun View.animateWidth(
    targetValue: Int, duration: Long = 400, listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
): ValueAnimator? {
    var animator: ValueAnimator? = null
    post {
        animator = ValueAnimator.ofInt(width, targetValue).apply {
            addUpdateListener {
                width(it.animatedValue as Int)
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
    return animator
}

/**
 * 设置高度，带有过渡动画
 * @param targetValue 目标高度
 * @param duration 时长
 * @param action 可选行为
 * @return 动画
 */
fun View.animateHeight(
    targetValue: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
): ValueAnimator? {
    var animator: ValueAnimator? = null
    post {
        animator = ValueAnimator.ofInt(height, targetValue).apply {
            addUpdateListener {
                height(it.animatedValue as Int)
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
    return animator
}

/**
 * 设置宽度和高度，带有过渡动画
 * @param targetWidth 目标宽度
 * @param targetHeight 目标高度
 * @param duration 时长
 * @param action 可选行为
 * @return 动画
 */
fun View.animateWidthAndHeight(
    targetWidth: Int,
    targetHeight: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
): ValueAnimator? {
    var animator: ValueAnimator? = null
    post {
        val startHeight = height
        val evaluator = IntEvaluator()
        animator = ValueAnimator.ofInt(width, targetWidth).apply {
            addUpdateListener {
                widthAndHeight(
                    it.animatedValue as Int,
                    evaluator.evaluate(it.animatedFraction, startHeight, targetHeight)
                )
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
    return animator
}

/*************************************** View其他 ********************************************/
/**
 * 获取View id
 */
fun View.getViewId(): Int {
    var id = id
    if (id == View.NO_ID) {
        id = View.generateViewId()
    }
    return id
}

/**
 * 给 [View] 设置带有防抖效果的点击事件
 *
 * @receiver [View]
 * @param delayTime Int 防抖间隔时间，单位是毫秒，默认值 500ms
 * @param listener (v: View) -> Unit 具体的点击事件
 * @see OnSingleClickListener
 */
fun View.setOnSingleClickListener(delayTime: Int = 500, listener: (v: View) -> Unit) {
    setOnClickListener(OnSingleClickListener(delayTime, listener))
}