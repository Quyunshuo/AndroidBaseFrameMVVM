package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.v.BaseFrameNotMVVMActivity

/**
 * 不是 MVVM 模式的基类
 *
 * @author Qu Yunshuo
 * @since 9/10/20
 */
abstract class BaseNotMVVMActivity<VB : ViewBinding> : BaseFrameNotMVVMActivity<VB>()