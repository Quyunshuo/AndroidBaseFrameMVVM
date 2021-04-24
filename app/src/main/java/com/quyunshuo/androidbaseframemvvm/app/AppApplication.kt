package com.quyunshuo.androidbaseframemvvm.app

import android.app.Application
import android.content.Context
import com.google.auto.service.AutoService
import com.quyunshuo.androidbaseframemvvm.base.app.ApplicationLifecycle
import com.quyunshuo.androidbaseframemvvm.base.app.InitDepend
import org.greenrobot.eventbus.EventBus

/**
 * App壳
 *
 * @author Qu Yunshuo
 * @since 4/23/21 6:08 PM
 */
@AutoService(ApplicationLifecycle::class)
class AppApplication : ApplicationLifecycle {

    /**
     * 同[Application.attachBaseContext]
     * @param context Context
     */
    override fun onAttachBaseContext(context: Context) {
        // 开启EventBusAPT,优化反射效率 当组件作为App运行时需要将添加的Index注释掉 因为找不到对应的类了
        EventBus
            .builder()
//            .addIndex(MainEventIndex())
            .installDefaultEventBus()
    }

    /**
     * 同[Application.onCreate]
     * @param application Application
     */
    override fun onCreate(application: Application) {}

    /**
     * 同[Application.onTerminate]
     * @param application Application
     */
    override fun onTerminate(application: Application) {}

    /**
     * 需要立即进行初始化的放在这里进行并行初始化
     * 需要必须在主线程初始化的放在[InitDepend.mainThreadDepends],反之放在[InitDepend.workerThreadDepends]
     * @return InitDepend 初始化方法集合
     */
    override fun initByFrontDesk(): InitDepend = InitDepend(mutableListOf(), mutableListOf())

    /**
     * 不需要立即初始化的放在这里进行后台初始化
     */
    override fun initByBackstage() {}
}