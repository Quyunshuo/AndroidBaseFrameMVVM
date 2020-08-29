package com.quyunshuo.common.net.api

import com.quyunshuo.common.bean.TestBean
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/29
 * @Class: ApiService
 * @Remark: Home接口
 */
interface ApiHomeService {

    // Retrofit 2.6版本开始支持协程 只需要将抽象方法写成挂起函数即可
    @GET()
    suspend fun getTestData(@Query("test") test: String): TestBean

}