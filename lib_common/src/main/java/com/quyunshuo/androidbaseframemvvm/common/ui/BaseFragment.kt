package com.quyunshuo.androidbaseframemvvm.common.ui

import androidx.viewbinding.ViewBinding
import com.quyunshuo.androidbaseframemvvm.base.mvvm.v.BaseFrameFragment

/**
 * Fragment基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFragment<VB : ViewBinding> : BaseFrameFragment<VB>()