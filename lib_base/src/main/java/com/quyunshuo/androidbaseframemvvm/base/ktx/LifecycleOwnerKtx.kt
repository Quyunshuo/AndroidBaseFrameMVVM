package com.quyunshuo.androidbaseframemvvm.base.ktx

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * 对LiveData订阅的简化封装
 *
 * 使用示例
 * ```
 *  override fun initObserve() {
 *      observeLiveData(mViewModel.stateViewLD, ::processStateViewLivaData)
 *  }
 *
 *  private fun processStateViewLivaData(data: StateLayoutEnum) {
 *      ...
 *  }
 * ```
 *
 * @receiver LifecycleOwner
 * @param liveData LiveData<T> 需要进行订阅的LiveData
 * @param action action: (t: T) -> Unit 处理订阅内容的方法
 * @return Unit
 */
inline fun <T> LifecycleOwner.observeLiveData(
    liveData: LiveData<T>,
    crossinline action: (t: T) -> Unit
) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}