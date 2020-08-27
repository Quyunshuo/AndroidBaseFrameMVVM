package com.quyunshuo.main

import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.databinding.MainActivityMainBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: MainActivity
 * @Remark: 主界面Activity
 */
class MainActivity : BaseActivity<MainActivityMainBinding>() {

    override fun initViewBinding(): MainActivityMainBinding =
        MainActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        mBinding.mTv.text = "Hello MVVM"
    }
}