package com.quyunshuo.common.ui

import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.quyunshuo.base.mvvm.v.BaseFrameFragment

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseFragment
 * @Remark: 项目相关的Fragment基类
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : BaseFrameFragment<VB, VM>()