package com.quyunshuo.common.ui

import androidx.annotation.ColorInt
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.jaeger.library.StatusBarUtil
import com.quyunshuo.base.mvvm.v.BaseFrameActivity

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseActivity
 * @Remark: 项目相关的Activity基类
 */
abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : BaseFrameActivity<VB, VM>(){

    /**
     * 设置状态栏颜色
     */
    protected fun setStatusBarColor(@ColorInt color: Int) {
        StatusBarUtil.setColor(this, color, 0)
    }
}