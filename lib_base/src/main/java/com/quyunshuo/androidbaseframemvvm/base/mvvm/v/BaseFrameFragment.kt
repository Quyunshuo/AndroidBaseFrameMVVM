package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.androidbaseframemvvm.base.utils.BindingReflex
import com.quyunshuo.androidbaseframemvvm.base.utils.EventBusRegister
import com.quyunshuo.androidbaseframemvvm.base.utils.EventBusUtils
import com.quyunshuo.androidbaseframemvvm.base.utils.status.ViewStatusHelper
import com.quyunshuo.androidbaseframemvvm.base.utils.status.imp.BaseFrameViewStatusHelperImp

/**
 * Fragment基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFrameFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFrameStatusFragment(), FrameView<VB> {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    protected abstract val mViewModel: VM

    /**
     * 基础UI状态管理工具
     */
    private lateinit var mBaseStatusHelper: BaseFrameViewStatusHelperImp


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.register(this)

        mBinding.initView()
        initObserve()
        initRequestData()
    }

    override fun isRecreate(): Boolean = mBaseStatusHelper.isRecreate

    override fun onRegisterStatusHelper(): ViewStatusHelper? {
        mBaseStatusHelper = BaseFrameViewStatusHelperImp(super.onRegisterStatusHelper())
        return mBaseStatusHelper
    }

    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.unRegister(
            this
        )
        super.onDestroy()
    }
}