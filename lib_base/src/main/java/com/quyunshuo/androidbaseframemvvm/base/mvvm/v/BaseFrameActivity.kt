package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import com.quyunshuo.androidbaseframemvvm.base.utils.BindingReflex
import com.quyunshuo.androidbaseframemvvm.base.utils.EventBusRegister
import com.quyunshuo.androidbaseframemvvm.base.utils.EventBusUtils
import com.quyunshuo.androidbaseframemvvm.base.utils.network.AutoRegisterNetListener
import com.quyunshuo.androidbaseframemvvm.base.utils.network.NetworkStateChangeListener
import com.quyunshuo.androidbaseframemvvm.base.utils.network.NetworkTypeEnum
import com.quyunshuo.androidbaseframemvvm.base.utils.status.ViewStatusHelper
import com.quyunshuo.androidbaseframemvvm.base.utils.status.imp.BaseFrameViewStatusHelperImp
import com.quyunshuo.androidbaseframemvvm.base.utils.toast

/**
 * Activity基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : BaseViewModel> : BaseFrameStatusActivity(),
    FrameView<VB>, NetworkStateChangeListener {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    protected abstract val mViewModel: VM

    /**
     * 基础UI状态管理工具 保存了是否重建的状态信息
     */
    private lateinit var mStatusHelper: BaseFrameViewStatusHelperImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
        ARouter.getInstance().inject(this)
        // 注册EventBus
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.register(this)

        setStatusBar()
        mBinding.initView()
        initNetworkListener()
        initObserve()
        initRequestData()
    }

    /**
     * 初始化网络状态监听
     * @return Unit
     */
    private fun initNetworkListener() {
        lifecycle.addObserver(AutoRegisterNetListener(this))
    }

    /**
     * 设置状态栏
     * 子类需要自定义时重写该方法即可
     * @return Unit
     */
    open fun setStatusBar() {}

    /**
     * 网络类型更改回调
     * @param type Int 网络类型
     * @return Unit
     */
    override fun networkTypeChange(type: NetworkTypeEnum) {}

    /**
     * 网络连接状态更改回调
     * @param isConnected Boolean 是否已连接
     * @return Unit
     */
    override fun networkConnectChange(isConnected: Boolean) {
        toast(if (isConnected) "网络已连接" else "网络已断开")
    }

    override fun isRecreate(): Boolean = mStatusHelper.isRecreate

    override fun onRegisterStatusHelper(): ViewStatusHelper? {
        mStatusHelper = BaseFrameViewStatusHelperImp(super.onRegisterStatusHelper())
        return mStatusHelper
    }

    override fun onDestroy() {
        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.unRegister(
            this
        )
        super.onDestroy()
    }
}