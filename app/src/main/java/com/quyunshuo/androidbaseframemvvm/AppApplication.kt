package com.quyunshuo.androidbaseframemvvm

import androidx.multidex.MultiDex
import com.quyunshuo.base.BaseApplication

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: AppApplication
 * @Remark: 壳App的Application 负责需要写在App包下的初始化逻辑
 */
class AppApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}