package com.quyunshuo.main.ui.main

import com.quyunshuo.base.mvvm.vm.BaseViewModel

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: MainViewModel
 * @Remark:
 */
class MainViewModel : BaseViewModel<MainRepository>() {

    override fun initRepository() = MainRepository()
}