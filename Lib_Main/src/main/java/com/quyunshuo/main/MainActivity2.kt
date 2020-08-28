package com.quyunshuo.main

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.base.ktx.toast
import com.quyunshuo.common.constant.RouteKey
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.databinding.MainActivityMain2Binding

@Route(path = RouteUrl.MainActivity2)
class MainActivity2 :
    BaseActivity<MainActivityMain2Binding, MainViewModel>(MainViewModel::class.java) {

    // 通过name来映射URL中的不同参数
    @Autowired(name = RouteKey.KEY_NAME)
    lateinit var name: String

    override fun initViewBinding(): MainActivityMain2Binding =
        MainActivityMain2Binding.inflate(layoutInflater)

    override fun initView() {
        toast(name)
    }
}