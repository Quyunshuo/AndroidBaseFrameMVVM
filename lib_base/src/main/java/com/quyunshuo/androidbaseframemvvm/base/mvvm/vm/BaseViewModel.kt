package com.quyunshuo.androidbaseframemvvm.base.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel 基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseViewModel : ViewModel() {

    // Loading 状态
    val isLoading = MutableLiveData(false)

    // 请求异常
    val requestError = MutableLiveData<Throwable?>()
}