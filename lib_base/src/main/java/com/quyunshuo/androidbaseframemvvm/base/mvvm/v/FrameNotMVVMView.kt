package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import androidx.viewbinding.ViewBinding

/**
 * View层基类抽象
 *
 * @author Qu Yunshuo
 * @since 10/13/20
 */
interface FrameNotMVVMView<VB : ViewBinding> {
    /**
     * 初始化View
     */
    fun VB.initView()
}