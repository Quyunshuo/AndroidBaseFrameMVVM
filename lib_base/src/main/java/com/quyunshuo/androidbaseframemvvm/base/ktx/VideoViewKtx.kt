package com.quyunshuo.androidbaseframemvvm.base.ktx

import android.media.MediaPlayer
import android.view.ViewGroup
import android.widget.VideoView

/**
 * 根据视频的尺寸与容器尺寸比例，动态调整 [VideoView] 的尺寸以适应视频的尺寸
 * 解决 [VideoView] 尺寸比例与视频尺寸比例不一致导致视频拉伸的问题
 *
 * 容器可以是屏幕或者 [VideoView]
 *
 * 使用方式：
 * 1. 通过 [VideoView.setOnPreparedListener] 向 [VideoView] 设置 [MediaPlayer.OnPreparedListener] 监听
 * 2. 通过 [MediaPlayer.OnPreparedListener] 回调获取到视频的真实宽高，调用该方法传入参数进行适配
 * 3. 如果需要考虑横竖屏切换，请在横竖屏改变监听回调中再次调用该方法进行适配
 *
 * @receiver [VideoView]
 * @param containerW Float 容器的真实宽
 * @param containerH Float 容器的真实高
 * @param videoW Float 视频的真实宽
 * @param videoH Float 视频的真实高
 */
fun VideoView.resetVideoViewDimensions(
    containerW: Float,
    containerH: Float,
    videoW: Float,
    videoH: Float,
) {
    // 计算宽高比进行调整宽高
    this.layoutParams = if (videoW / containerW < videoH / containerH) {
        this.layoutParams.apply {
            width = ViewGroup.LayoutParams.WRAP_CONTENT
            height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    } else {
        this.layoutParams.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
    }
}