package com.quyunshuo.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseFrameActivity
 * @Remark: Activity基类 与项目无关
 */
abstract class BaseFrameActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var mBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = initViewBinding()
        setContentView(mBinding.root)
    }

    protected abstract fun initViewBinding(): VB
    protected abstract fun initView()
}