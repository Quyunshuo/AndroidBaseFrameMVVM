package com.quyunshuo.androidbaseframemvvm.base.app

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDexApplication
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Application 基类
 *
 * @author Qu Yunshuo
 * @since 4/24/21 5:30 PM
 */
class BaseApplication : MultiDexApplication() {

    private val mCoroutineScope by lazy(mode = LazyThreadSafetyMode.NONE) { MainScope() }

    private val mLoadModuleProxy by lazy(mode = LazyThreadSafetyMode.NONE) { LoadModuleProxy() }

    companion object {
        // 全局Context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        @SuppressLint("StaticFieldLeak")
        lateinit var application: BaseApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        context = base
        application = this
        mLoadModuleProxy.onAttachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        // 全局监听 Activity 生命周期
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())

        mLoadModuleProxy.onCreate(this)

        // 策略初始化第三方依赖
        initDepends()
    }

    /**
     * 初始化第三方依赖
     *
     * 步骤：
     * * 1. 首先开启一个后台协程对不会立即使用的第三方进行初始化
     * * 2. 对需要被立即使用的第三方进行初始化
     * * 2.1 首先是并行对非必须要在主线程初始化的依赖进行初始化 此时不用管初始化是否完成 紧接着进行下一步
     * * 2.2 对必须要在主线程初始化的依赖进行初始化 由于是在主线程 所以后面的操作等待初始化完成 这部分时间不能浪费掉 这就是为什么先并行初始化非主线程的 因为这部分时间会被利用上
     * * 2.3 等待所有并行初始化的job完成就结束了整个初始化过程
     */
    private fun initDepends() {
        // 开启一个 Default Coroutine 进行初始化不会立即使用的第三方
        mCoroutineScope.launch(Dispatchers.Default) {
            mLoadModuleProxy.initByBackstage()
        }

        // 初始化需要被立即初始化的第三方 多线程并行，并阻塞至全部完成
        val measureTimeMillis = measureTimeMillis {
            mCoroutineScope.launch(Dispatchers.Main.immediate) {
                val depends = mLoadModuleProxy.initByFrontDesk()

                // 1. 对非必须在主线程初始化的第三方依赖发起并行初始化
                // 并行job
                var jobs: MutableList<Deferred<String>>? = null
                if (depends.workerThreadDepends.isNotEmpty()) {
                    jobs = mutableListOf()
                    depends.workerThreadDepends.forEach {
                        jobs.add(async(Dispatchers.Default) { it() })
                    }
                }

                // 2. 对必须在主线程初始化的第三方依赖进行初始化
                if (depends.mainThreadDepends.isNotEmpty()) {
                    depends.mainThreadDepends.forEach { it() }
                }

                // 3. 等待每一个子线程初始化的依赖完成
                jobs?.forEach { it.await() }
            }
        }
        Log.d("ApplicationInit", "初始化完成 $measureTimeMillis ms")
    }

    override fun onTerminate() {
        super.onTerminate()
        mLoadModuleProxy.onTerminate(this)
        mCoroutineScope.cancel()
    }
}