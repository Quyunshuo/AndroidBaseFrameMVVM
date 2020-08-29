package com.quyunshuo.common.net

import com.quyunshuo.base.BaseApplication
import com.quyunshuo.base.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/29
 * @Class: NetServiceCreator
 * @Remark: Retrofit动态代理对象获取封装
 */
object NetServiceCreator {

    private const val BASE_URL = ""

    private const val CONNECT_TIME_OUT = 15L

    private const val READ_TIME_OUT = 20L

    private val BODY by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val NONE by lazy(mode = LazyThreadSafetyMode.NONE) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val okHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)         // 连接超时
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)               // 读取超时
            .addInterceptor(if (BuildConfig.DEBUG) BODY else NONE)      // 请求日志拦截器
            .addInterceptor(ChuckInterceptor(BaseApplication.context))  // 请求日志拦截器(UI)
            .retryOnConnectionFailure(true)       // 失败重连
            .build()
    }

    private val retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())     // Gson转换器
            .client(okHttpClient)
            .build()
    }

    /**
     * 获取service动态代理对象
     * @param serviceClass 接口Class对象
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 获取service动态代理对象
     * 范型实化
     */
    inline fun <reified T> create(): T = create(T::class.java)
}