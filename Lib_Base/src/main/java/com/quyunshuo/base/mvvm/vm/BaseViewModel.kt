package com.quyunshuo.base.mvvm.vm

import androidx.lifecycle.ViewModel
import com.quyunshuo.base.mvvm.m.BaseRepository

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseViewModel
 * @Remark: ViewModel 基类
 */
abstract class BaseViewModel<R : BaseRepository> : ViewModel() {

    protected val mRepository: R by lazy { initRepository() }

    protected abstract fun initRepository(): R
}