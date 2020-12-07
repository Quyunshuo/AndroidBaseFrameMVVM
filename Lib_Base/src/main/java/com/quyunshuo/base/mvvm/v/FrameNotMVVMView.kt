package com.quyunshuo.base.mvvm.v

import androidx.viewbinding.ViewBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/10/13
 * @Class: FrameNotMVVMView
 * @Remark: View层基类抽象
 */
interface FrameNotMVVMView<VB : ViewBinding> {
    /**
     * 初始化View
     */
    fun VB.initView()
}