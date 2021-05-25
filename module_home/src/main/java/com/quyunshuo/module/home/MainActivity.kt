package com.quyunshuo.module.home

import android.graphics.Color
import androidx.activity.viewModels
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseActivity
import com.quyunshuo.module.home.databinding.HomeActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * 首页
 *
 * @author Qu Yunshuo
 * @since 5/22/21 2:26 PM
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<HomeActivityMainBinding>() {

    /**
     * 通过 viewModels() + Hilt 获取 ViewModel 实例
     */
    private val mViewModel by viewModels<HomeViewModel>()

    override fun HomeActivityMainBinding.initView() {}

    override fun initLiveDataObserve() {
        // 订阅数据
        mViewModel.data.observe(this, {
            mBinding.vTvHello.text = it
            mBinding.vTvHello.setTextColor(Color.BLUE)
        })
    }

    override fun initRequestData() {
        // 模拟获取数据
        mViewModel.getData()
    }
}