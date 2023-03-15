package com.quyunshuo.androidbaseframemvvm.base.view

import android.view.View

/**
 * 带有防抖效果的单击监听
 *
 * @param mDelayTime Int 防抖间隔时间，单位是毫秒，默认值 500ms
 * @param mListener (v: View) -> Unit 具体的点击事件
 *
 * @author Qu Yunshuo
 * @since 2023/3/15 23:39
 */
class OnSingleClickListener(
    private val mDelayTime: Int = 500,
    private val mListener: (v: View) -> Unit
) : View.OnClickListener {

    /**
     * 上次有效点击的时间
     */
    private var mLastClickTime = 0L
    override fun onClick(v: View) {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - mLastClickTime >= mDelayTime) {
            mLastClickTime = currentTimeMillis
            mListener.invoke(v)
        }
    }
}