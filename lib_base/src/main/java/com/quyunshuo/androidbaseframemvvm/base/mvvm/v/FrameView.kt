package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import androidx.viewbinding.ViewBinding

/**
 * View层基类抽象
 *
 * @author Qu Yunshuo
 * @since 10/13/20
 */
interface FrameView<VB : ViewBinding> {

    /**
     * 初始化View
     */
    fun VB.initView()

    /**
     * 订阅LiveData
     */
    fun initObserve()

    /**
     * 用于在页面创建时进行请求接口
     */
    fun initRequestData()

    /**
     * 页面是否重建，fragment被回收重新展示的时候为true，系统环境发生变化activity重新创建时为true
     */
    fun isRecreate(): Boolean
}