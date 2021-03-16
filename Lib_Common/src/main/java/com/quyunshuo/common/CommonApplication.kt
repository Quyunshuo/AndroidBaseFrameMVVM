package com.quyunshuo.common

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.base.BaseApplication
import com.quyunshuo.base.BaseApplication.InitDepend
import com.quyunshuo.base.utils.ActivityStackManager
import com.quyunshuo.base.utils.ProcessUtils
import com.quyunshuo.base.utils.SpUtils
import com.tencent.bugly.crashreport.CrashReport

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: CommonApplication
 * @Remark: 项目相关的Application
 */
open class CommonApplication : BaseApplication(), Application.ActivityLifecycleCallbacks {

    /**
     * 项目当前的版本状态
     */
    val versionStatus: String by lazy { getString(R.string.VERSION_STATUS) }

    override fun onCreate() {
        super.onCreate()
        // 全局监听 Activity 生命周期
        registerActivityLifecycleCallbacks(this)
    }

    /**
     * 需要立即进行初始化的放在这里进行并行初始化
     * 需要必须在主线程初始化的放在[InitDepend.mainThreadDepends],反之放在[InitDepend.workerThreadDepends]
     * @return InitDepend 初始化方法集合
     */
    override fun initByFrontDesk(): InitDepend {
        val worker = mutableListOf<() -> String>()
        // 以下只需要在主进程当中初始化 按需要调整
        if (ProcessUtils.isMainProcess(this)) {
            worker.add { initMMKV() }
            worker.add { initARouter() }
        }
        worker.add { initTencentBugly() }
        return InitDepend(null, worker)
    }

    /**
     * 不需要立马初始化的放在这里进行后台初始化
     */
    override suspend fun initByBackstage() {}

    /**
     * 腾讯 MMKV 初始化
     */
    private fun initMMKV(): String {
        val result = SpUtils.initMMKV(this)
        return "MMKV -->> $result"
    }

    /**
     * 阿里路由 ARouter 初始化
     */
    private fun initARouter(): String {
        // 测试环境下打开ARouter的日志和调试模式 正式环境需要关闭
        if (versionStatus == "VERSION_STATUS_ALPHA" || versionStatus == "VERSION_STATUS_BETA") {
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
        return "ARouter -->> init complete"
    }

    /**
     * 初始化 腾讯Bugly
     * 测试环境应该与正式环境的日志收集渠道分隔开
     */
    private fun initTencentBugly(): String {
        // 第三个参数为SDK调试模式开关
        CrashReport.initCrashReport(
            this,
            getString(R.string.BUGLY_APP_ID),
            versionStatus == "VERSION_STATUS_ALPHA" || versionStatus == "VERSION_STATUS_BETA"
        )
        return "Bugly -->> init complete"
    }

    override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        ActivityStackManager.addActivityToStack(activity)
    }

    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
    override fun onActivityDestroyed(activity: Activity) {
        ActivityStackManager.popActivityToStack(activity)
    }
}