package com.quyunshuo.androidbaseframemvvm.base.app

/**
 * 需要立即进行初始化的依赖列表 有的依赖可能必须要在主线程进行初始化，就放在[mainThreadDepends]里面就可以
 * 其余的非必须要在主线程进行初始化的放在[workerThreadDepends]里面，这部分依赖会被后台线程并行初始化
 *
 * @property mainThreadDepends MutableList<() -> String> 必须要在主线程初始化的依赖
 * @property workerThreadDepends MutableList<() -> String> 非必须要在主线程初始化的依赖
 *
 * @author Qu Yunshuo
 * @since 4/24/21 5:20 PM
 */
data class InitDepend(
    val mainThreadDepends: MutableList<() -> String>,
    val workerThreadDepends: MutableList<() -> String>
)