package com.quyunshuo.androidbaseframemvvm

import com.quyunshuo.common.CommonApplication
import org.greenrobot.eventbus.EventBus

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: AppApplication
 * @Remark: 壳App的Application 负责需要写在App包下的初始化逻辑
 */
class AppApplication : CommonApplication() {

    override fun onCreate() {
        // 开启EventBusAPT,优化反射效率
        EventBus
            .builder()
//            .addIndex()
            .installDefaultEventBus()
        super.onCreate()
    }
}