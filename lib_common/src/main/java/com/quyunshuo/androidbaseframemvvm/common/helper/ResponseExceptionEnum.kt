package com.quyunshuo.androidbaseframemvvm.common.helper

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
    }
}