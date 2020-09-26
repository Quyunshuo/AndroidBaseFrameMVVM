package com.quyunshuo.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.base.utils.EventBusRegister
import com.quyunshuo.base.utils.EventBusUtils
import java.lang.reflect.ParameterizedType

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseFrameActivity
 * @Remark: Activity基类 与项目无关
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : ViewModel> :
    AppCompatActivity() {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        val vbClass: Class<VB> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val inflate = vbClass.getMethod("inflate", LayoutInflater::class.java)
        inflate.invoke(null, layoutInflater) as VB
    }

    protected val mViewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        val vmClass: Class<VM> =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
        ViewModelProvider(this).get(vmClass)
    }

    protected abstract fun initView()
    protected abstract fun initViewObserve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.register(this)
        initView()
        initViewObserve()
    }

    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.unRegister(
            this
        )
        super.onDestroy()
    }
}