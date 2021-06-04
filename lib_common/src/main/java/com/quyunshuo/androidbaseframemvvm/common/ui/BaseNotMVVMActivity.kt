package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.v.BaseFrameNotMVVMActivity
import com.quyunshuo.androidbaseframemvvm.base.utils.AndroidBugFixUtils

/**
 * 不是 MVVM 模式的基类
 *
 * @author Qu Yunshuo
 * @since 9/10/20
 */
abstract class BaseNotMVVMActivity<VB : ViewBinding> : BaseFrameNotMVVMActivity<VB>() {

    override fun onDestroy() {
        // 解决某些特定机型会触发的Android本身的Bug
        AndroidBugFixUtils().fixSoftInputLeaks(this)
        super.onDestroy()
    }
}