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
     * 初始化LiveData的订阅关系
     */
    fun initLiveDataObserve()

    /**
     * 初始化界面创建时的数据请求,尝试在此方法内调用[isRecreate]进行重建判断，防止数据重复获取
     */
    fun initRequestData()

    /**
     * 页面是否重建，fragment被回收重新展示的时候为true，系统环境发生变化activity重新创建时为true
     */
    fun isRecreate(): Boolean
}