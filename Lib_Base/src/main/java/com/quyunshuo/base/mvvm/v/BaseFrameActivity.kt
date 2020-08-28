package com.quyunshuo.base.mvvm.v

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseFrameActivity
 * @Remark: Activity基类 与项目无关
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : ViewModel>(private val vmClass: Class<VM>) :
    AppCompatActivity() {

    protected val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this).get(vmClass)
    }

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) { initViewBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        initView()
    }

    protected abstract fun initViewBinding(): VB
    protected abstract fun initView()
}