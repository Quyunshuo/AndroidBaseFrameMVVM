package com.quyunshuo.androidbaseframemvvm.net

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络客户端提供者
 *
 * @author Qu Yunshuo
 * @since 4/17/21 2:35 PM
 */
object NetClientProvider {

    /**
     * 连接超时
     */
    private const val CONNECT_TIME_OUT = 15L * 1000L

    /**
     * 读取超时
     */
    private const val READ_TIME_OUT = 20L * 1000L

    /**
     * 日志拦截器
     */
    private val mLogInterceptor: Interceptor by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        HttpLoggingInterceptor().setLevel(level)
    }

    /**
     * OkHttpClient
     */
    private val mOkHttpClient: OkHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)    // 连接超时
            .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)          // 读取超时
            .addInterceptor(mLogInterceptor)                            // 日志拦截器
            .retryOnConnectionFailure(true)      // 失败重连
            .build()
    }

    /**
     * 项目服务器Retrofit
     */
    val mRetrofit: Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(NetBaseUrl.BASE_URLl)
            .addConverterFactory(GsonConverterFactory.create())         // Gson转换器
            .client(mOkHttpClient)
            .build()
    }
}