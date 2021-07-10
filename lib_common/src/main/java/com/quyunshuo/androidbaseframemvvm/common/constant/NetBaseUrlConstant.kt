package com.quyunshuo.androidbaseframemvvm.common.constant

/**
 * 接口公共地址
 *
 * @author Qu Yunshuo
 * @since 4/17/21 3:27 PM
 */
internal object NetBaseUrlConstant {

    val MAIN_URL = "http://www.baidu.com"
    get() {
        if (field.isEmpty()){
            throw NotImplementedError("请求改你的 MAIN_URL 的值为自己的请求地址")
        }
       return  field
    }
}