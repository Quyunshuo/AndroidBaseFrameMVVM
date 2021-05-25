package com.quyunshuo.androidbaseframemvvm.base.mvvm.m

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 仓库层 Repository 基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
open class BaseRepository {

    /**
     * 发起请求封装
     * @param requestBlock 请求的整体逻辑
     * @return Flow<T>
     */
    protected fun <T> flowRequest(requestBlock: suspend FlowCollector<T>.() -> Unit) =
        flow(block = requestBlock).flowOn(Dispatchers.IO)     // 通过 flowOn 切换到 io 线程
}