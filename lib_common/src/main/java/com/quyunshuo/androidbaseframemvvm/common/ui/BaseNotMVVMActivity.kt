package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.v.BaseFrameNotMVVMActivity

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/10
 * @Class: BaseNotMVVMActivity
 * @Remark: 不是 MVVM 模式的基类
 */
abstract class BaseNotMVVMActivity<VB : ViewBinding> : BaseFrameNotMVVMActivity<VB>()