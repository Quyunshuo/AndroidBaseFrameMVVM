package com.quyunshuo.module.home

import com.quyunshuo.androidbaseframemvvm.base.mvvm.m.BaseRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 * 首页M层
 *
 * @author Qu Yunshuo
 * @since 5/25/21 5:42 PM
 */
class HomeRepository @Inject constructor() : BaseRepository() {

    /**
     * 模拟获取数据
     */
    suspend fun getData() = flowRequest<String> {
        delay(1000L)
        emit("Hello Hilt")
    }
}