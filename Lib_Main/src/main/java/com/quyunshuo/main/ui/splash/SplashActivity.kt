package com.quyunshuo.main.ui.splash

import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.databinding.MainActivitySplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.jessyan.autosize.internal.CancelAdapt

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class:
 * @Remark:
 */
@Route(path = RouteUrl.SplashActivity)
class SplashActivity :
    BaseActivity<MainActivitySplashBinding, SplashViewModel>(SplashViewModel::class.java),
    CancelAdapt {

    override fun initViewBinding() = MainActivitySplashBinding.inflate(layoutInflater)

    override fun initView() {
        jumpMain()
    }

    /**
     * 延时跳转到首页
     */
    private fun jumpMain() {
        lifecycleScope.launch(Dispatchers.Default) {
            delay(1000L)
            ARouter.getInstance()
                .build(RouteUrl.MainActivity)
                .navigation()
            delay(100L)
            finish()
        }
    }
}