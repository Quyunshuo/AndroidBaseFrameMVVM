package com.quyunshuo.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.base.utils.SpUtils
import com.tencent.bugly.crashreport.CrashReport

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseApplication
 * @Remark: 自定义Application的基类
 */
open class BaseApplication : Application() {

    companion object {
        // 全局Context
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        initialize()
    }

    /**
     * 进行一些初始化的操作
     */
    protected open fun initialize() {
        // 腾讯 MMKV 初始化
        SpUtils.initMMKV(this)

        // 阿里路由 ARouter 初始化
        if (BuildConfig.DEBUG) {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)

        // Bugly 初始化 第三个参数为SDK调试模式开关
        CrashReport.initCrashReport(this, "申请的id", BuildConfig.DEBUG)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}