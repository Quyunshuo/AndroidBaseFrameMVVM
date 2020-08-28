package com.quyunshuo.main

import com.quyunshuo.base.mvvm.m.BaseRepository

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/28
 * @Class: MainRepository
 * @Remark:
 */
class MainRepository : BaseRepository() {

    /**
     * 模拟请求或读取数据库
     */
    suspend fun getString() =
        flowRequest<String> {
            emit("嘿嘿")
        }
}