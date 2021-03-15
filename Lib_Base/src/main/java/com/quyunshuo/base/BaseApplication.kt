package com.quyunshuo.base

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.multidex.MultiDexApplication
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: BaseApplication
 * @Remark: 自定义Application的基类
 */
abstract class BaseApplication : MultiDexApplication() {

    private val mCoroutineScope by lazy(mode = LazyThreadSafetyMode.NONE) { MainScope() }

    companion object {
        // 全局Context
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        // 策略初始化第三方依赖
        initDepends()
    }

    /**
     * 需要立即进行初始化的放在这里进行并行初始化
     * 需要必须在主线程初始化的放在[InitDepend.mainThreadDepends],反之放在[InitDepend.workerThreadDepends]
     * @return InitDepend 初始化方法集合
     */
    abstract fun initByFrontDesk(): InitDepend

    /**
     * 不需要立马初始化的放在这里进行后台初始化
     */
    abstract suspend fun initByBackstage()

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
            initByBackstage()
        }

        // 初始化需要被立即初始化的第三方 多线程并行，并阻塞至全部完成
        val measureTimeMillis = measureTimeMillis {
            mCoroutineScope.launch(Dispatchers.Main.immediate) {
                val depends = initByFrontDesk()

                // 1. 对非必须在主线程初始化的第三方依赖发起并行初始化
                // 并行job
                var jobs: MutableList<Deferred<String>>? = null
                if (depends.workerThreadDepends != null) {
                    jobs = mutableListOf()
                    depends.workerThreadDepends.forEach {
                        jobs.add(async(Dispatchers.Default) { it() })
                    }
                }

                // 2. 对必须在主线程初始化的第三方依赖进行初始化
                if (depends.mainThreadDepends != null) {
                    depends.mainThreadDepends.forEach { it() }
                }

                // 3. 等待每一个子线程初始化的依赖完成
                jobs?.forEach { it.await() }
            }
        }
        Log.d("ApplicationInit", "初始化完成 $measureTimeMillis ms")
    }

    /**
     * 需要立即进行初始化的依赖列表 有的依赖可能必须要在主线程进行初始化，就放在[mainThreadDepends]里面就可以
     * 其余的非必须要在主线程进行初始化的放在[workerThreadDepends]里面，这部分依赖会被后台线程并行初始化
     *
     * @property mainThreadDepends MutableList<() -> String>? 必须要在主线程初始化的依赖
     * @property workerThreadDepends MutableList<() -> String>? 非必须要在主线程初始化的依赖
     */
    data class InitDepend(
        val mainThreadDepends: MutableList<() -> String>?,
        val workerThreadDepends: MutableList<() -> String>?
    )
}