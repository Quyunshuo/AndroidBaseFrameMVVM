package com.quyunshuo.main

import android.widget.Toast
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.databinding.MainActivityMainBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: MainActivity
 * @Remark: 主界面Activity
 */
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding(): MainActivityMainBinding =
        MainActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        mViewModel.msg.observe(this, {
            mBinding.mTv.text = it
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mBinding.mBtn.setOnClickListener { mViewModel.getString() }
    }
}