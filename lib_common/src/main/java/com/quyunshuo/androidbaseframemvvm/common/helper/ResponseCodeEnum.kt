package com.quyunshuo.androidbaseframemvvm.common.helper

/**
 * 请求响应code枚举抽象
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 2:56 下午
 */
interface IResponseCode {

    /**
     * 获取该枚举的code码
     * @return Int
     */
    fun getCode(): Int

    /**
     * 获取该枚举的描述
     * @return String
     */
    fun getMessage(): String
}

/**
 * 请求响应code的枚举
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 2:55 下午
 */
enum class ResponseCodeEnum : IResponseCode {

    // 通用异常
    ERROR {
        override fun getCode() = 100
        override fun getMessage() = "处理失败"
    },

    // 成功
    SUCCESS {
        override fun getCode() = 200
        override fun getMessage() = "成功"
    }
}