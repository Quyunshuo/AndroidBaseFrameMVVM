package com.quyunshuo.base.mvvm.v

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.base.utils.EventBusRegister
import com.quyunshuo.base.utils.EventBusUtils

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/10
 * @Class: BaseFrameNotMVVMActivity
 * @Remark: 不使用 MVVM 的 Activity 基类
 */
abstract class BaseFrameNotMVVMActivity<VB : ViewBinding> : AppCompatActivity() {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) { initViewBinding() }

    protected abstract fun initViewBinding(): VB
    protected abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.register(this)
        initView()
    }

    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.unRegister(
            this
        )
        super.onDestroy()
    }
}