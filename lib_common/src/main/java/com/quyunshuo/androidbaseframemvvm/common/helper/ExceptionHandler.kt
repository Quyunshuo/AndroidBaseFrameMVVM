package com.quyunshuo.androidbaseframemvvm.common.helper

import com.quyunshuo.androidbaseframemvvm.base.utils.toast
import kotlin.jvm.Throws
import com.quyunshuo.androidbaseframemvvm.common.helper.ResponseExceptionEnum as ExceptionType

/**
 * 请求异常处理
 *
 * 该方法主要做两件事:
 *
 * - 1.做统一的code码处理
 * - 2.未进行统一处理的code码会被转换为自定义异常[ResponseException]抛出
 *
 * 使用方式为：进行统一处理的异常进行抛出[ResponseEmptyException]，未进行处理的抛出[ResponseException]
 *
 * @param code Int code码
 * @throws ResponseException 未进行处理的异常会进行抛出，让ViewModel去做进一步处理
 */
@Throws(ResponseException::class)
fun responseExceptionHandler(code: Int) {
    // 进行异常的处理
    when (code) {
        ExceptionType.INTERNAL_SERVER_ERROR.getCode() -> {
            toast(ExceptionType.INTERNAL_SERVER_ERROR.getMessage())
            throw ResponseEmptyException()
        }
    }
}