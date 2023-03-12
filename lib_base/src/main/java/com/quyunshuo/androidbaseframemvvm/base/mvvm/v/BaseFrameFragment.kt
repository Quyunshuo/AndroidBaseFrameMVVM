package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.androidbaseframemvvm.base.utils.BindingReflex
import com.quyunshuo.androidbaseframemvvm.base.utils.RegisterEventBus
import com.quyunshuo.androidbaseframemvvm.base.utils.EventBusUtils

/**
 * Fragment基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFrameFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment(),
    FrameView<VB> {

    /**
     * 私有的 ViewBinding 此写法来自 Google Android 官方
     */
    private var _binding: VB? = null

    protected val mBinding get() = _binding!!

    protected abstract val mViewModel: VM

    /**
     * 是否有 [RegisterEventBus] 注解，避免重复调用 [Class.isAnnotation]
     */
    private var mHaveRegisterEventBus = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BindingReflex.reflexViewBinding(javaClass, layoutInflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)

        // 根据子类是否有 RegisterEventBus 注解決定是否进行注册 EventBus
        if (javaClass.isAnnotationPresent(RegisterEventBus::class.java)) {
            mHaveRegisterEventBus = true
            EventBusUtils.register(this)
        }
        _binding?.initView()
        initObserve()
        initRequestData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        // 根据子类是否有 RegisterEventBus 注解决定是否进行注册 EventBus
        if (mHaveRegisterEventBus) {
            EventBusUtils.unRegister(this)
        }
        super.onDestroy()
    }
}