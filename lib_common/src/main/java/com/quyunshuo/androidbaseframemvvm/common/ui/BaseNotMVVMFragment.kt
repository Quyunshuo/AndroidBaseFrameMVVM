package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.v.BaseFrameNotMVVMFragment

/**
 * 不是 MVVM 模式的基类
 *
 * @author Qu Yunshuo
 * @since 9/10/20
 */
abstract class BaseNotMVVMFragment<VB : ViewBinding> : BaseFrameNotMVVMFragment<VB>()