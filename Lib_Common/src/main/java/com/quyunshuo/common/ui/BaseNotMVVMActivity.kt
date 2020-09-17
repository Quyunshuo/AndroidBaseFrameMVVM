package com.quyunshuo.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.base.mvvm.v.BaseFrameNotMVVMActivity

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/10
 * @Class: BaseNotMVVMActivity
 * @Remark: 不是 MVVM 模式的基类
 */
abstract class BaseNotMVVMActivity<VB : ViewBinding> : BaseFrameNotMVVMActivity<VB>()