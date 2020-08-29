package com.quyunshuo.main

import com.quyunshuo.base.mvvm.m.BaseRepository
import com.quyunshuo.common.net.NetRequest

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

    /**
     * 模拟使用网络请求接口
     * 需要写成挂起函数
     */
    suspend fun mockRequest() =
        flowRequest<String> {
            // 发起请求
            // 并行请求可以使用 async await
            // 例如
            // // 创建一个新的协程进行请求
            //    val deferredRealtime = async {
            //        SendRequest.getRealtimeWeather(lng, lat)
            //    }
            //    // 创建一个新的协程进行请求
            //    val deferredDaily = async {
            //        SendRequest.getDailyWeather(lng, lat)
            //    }
            //    // 对两个请求获取结果
            //    val realtimeResponse = deferredRealtime.await()
            //    val dailyResponse = deferredDaily.await()
            // 这两个协程是并行的  不是串行

            val testData = NetRequest.homeService.getTestData("mock")

            // 处理请求结果
            // {if testData ... }

            // 将请求结果或者是调用者需要的数据进行发射出去
            emit(testData.msgTest)
        }
}