package com.quyunshuo.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.base.mvvm.v.BaseFrameNotMVVMFragment

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/10
 * @Class: BaseNotMVVMFragment
 * @Remark: 不是 MVVM 模式的基类
 */
abstract class BaseNotMVVMFragment<VB : ViewBinding> : BaseFrameNotMVVMFragment<VB>()