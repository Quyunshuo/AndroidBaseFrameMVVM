package com.quyunshuo.main.ui.splash

import com.quyunshuo.base.mvvm.vm.BaseViewModel

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: SplashViewModel
 * @Remark:
 */
class SplashViewModel : BaseViewModel<SplashRepository>() {

    override fun initRepository() = SplashRepository()
}