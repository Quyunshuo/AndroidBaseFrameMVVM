package com.quyunshuo.androidbaseframemvvm.common.helper

/**
 * 请求响应异常枚举的抽象
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 2:56 下午
 */
interface ResponseExceptionEnumCode {

    /**
     * 获取该异常枚举的code码
     * @return Int
     */
    fun getCode(): Int

    /**
     * 获取该异常枚举的描述
     * @return String
     */
    fun getMessage(): String
}

/**
 * 请求响应异常的类型
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 2:55 下午
 */
enum class ResponseExceptionEnum : ResponseExceptionEnumCode {

    INTERNAL_SERVER_ERROR {
        override fun getCode() = 500
        override fun getMessage() = "服务器内部错误"
    },

    // 成功
    SUCCESS {
        override fun getCode() = 200
        override fun getMessage() = "成功"
    }
}