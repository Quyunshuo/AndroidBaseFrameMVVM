package com.quyunshuo.androidbaseframemvvm.common.helper

import com.quyunshuo.androidbaseframemvvm.common.helper.ResponseExceptionEnum as ExceptionType

/**
 * 请求响应异常，主要为各种code码专门定义的异常
 *
 * @property type ResponseExceptionEnum 异常类型枚举，用于标记该异常的类型
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 2:57 下午
 */
class ResponseException(val type: ExceptionType) : Exception()

/**
 * 空异常，表示该异常已经被处理过了，不需要再做额外处理了
 *
 * @author Qu Yunshuo
 * @since 2021/7/9 3:11 下午
 */
class ResponseEmptyException : Exception()