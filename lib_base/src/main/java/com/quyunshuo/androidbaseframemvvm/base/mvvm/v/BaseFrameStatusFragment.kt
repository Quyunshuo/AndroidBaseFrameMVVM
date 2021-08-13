package com.quyunshuo.androidbaseframemvvm.base.mvvm.v

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.quyunshuo.androidbaseframemvvm.base.utils.status.ViewStatusHelper

/**
 * @author DBoy 2021/8/5 <p>
 * - 文件描述 : 基础状态管理Fragment
 */
open class BaseFrameStatusFragment : Fragment() {
    /**
     * 基础状态管理帮助类
     */
    private var mBaseStatusHelper: ViewStatusHelper? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //注册状态帮助类
        mBaseStatusHelper = onRegisterStatusHelper()
        //恢复状态数据
        mBaseStatusHelper?.onRestoreInstanceStatus(savedInstanceState)
    }

    /**
     * 保存状态
     */
    override fun onSaveInstanceState(outState: Bundle) {
        mBaseStatusHelper?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    /**
     * 注册状态管理帮助类,子类重写此方法以注册帮助类。
     * 每一层都有可能有自己的状态管理帮助类，所以继承重写的时候，需要将super的对象传入自己的帮助类构造函数中
     */
    protected open fun onRegisterStatusHelper(): ViewStatusHelper? {
        return null
    }
}