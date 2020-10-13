package com.quyunshuo.base.mvvm.v

/**
 * @Author: QuYunShuo
 * @Time: 2020/10/13
 * @Class: FrameView
 * @Remark: View层基类抽象
 */
interface FrameView {
    /**
     * 初始化View
     */
    fun initView()

    /**
     * 订阅LiveData
     */
    fun initViewObserve()
}