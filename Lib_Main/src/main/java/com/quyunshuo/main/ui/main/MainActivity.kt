package com.quyunshuo.main.ui.main

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.jaeger.library.StatusBarUtil
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.R
import com.quyunshuo.main.databinding.MainActivityMainBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: MainActivity
 * @Remark: 首页
 */
@Route(path = RouteUrl.MainActivity)
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding() = MainActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        StatusBarUtil.setColor(this, resources.getColor(R.color.common_theme_color))
    }
}