package com.quyunshuo.androidbaseframemvvm.app

import com.quyunshuo.androidbaseframemvvm.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus

/**
 * App壳
 *
 * @author Qu Yunshuo
 * @since 4/23/21 6:08 PM
 */
@HiltAndroidApp
class AppApplication : BaseApplication() {

    override fun onCreate() {
        // 开启EventBusAPT,优化反射效率 当组件作为App运行时需要将添加的Index注释掉 因为找不到对应的类了
        EventBus
            .builder()
//            .addIndex(MainEventIndex())
            .installDefaultEventBus()
        super.onCreate()
    }
}