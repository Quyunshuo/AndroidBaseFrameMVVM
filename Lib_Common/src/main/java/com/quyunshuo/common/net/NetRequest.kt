package com.quyunshuo.common.net

import com.quyunshuo.common.net.api.ApiCommonService
import com.quyunshuo.common.net.api.ApiBaiDuTranslationService

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/29
 * @Class: NetRequest
 * @Remark: 对ApiService动态代理对象统一管理
 */
object NetRequest {

    // 公共接口
    val commonService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator.create(ApiCommonService::class.java)
    }

    // 百度翻译接口
    val translationService by lazy(mode = LazyThreadSafetyMode.NONE) {
        NetServiceCreator.createBaiDu(ApiBaiDuTranslationService::class.java)
    }
}