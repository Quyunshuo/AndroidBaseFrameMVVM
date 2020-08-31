package com.quyunshuo.translation

import com.quyunshuo.base.mvvm.m.BaseRepository
import com.quyunshuo.common.constant.AppConstant
import com.quyunshuo.common.net.NetRequest
import com.quyunshuo.common.utils.MD5

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: TranslationRepository
 * @Remark:
 */
class TranslationRepository : BaseRepository() {

    /**
     * 获取翻译结果
     * @param original 需要翻译的文本
     */
    fun getTranslation(original: String) =
        flowRequest<String> {
            // 以当前时间做为随机数
            val sign = System.currentTimeMillis().toString()
            // 发起请求
            val response = NetRequest.translationService.sendBaiDuTranslation(
                original,
                "auto",
                "en",
                AppConstant.translationAppId,
                sign,
                MD5.encode("${AppConstant.translationAppId}${original}${sign}${AppConstant.translationSecurityKry}")
            )
            // 将翻译结果发射出去
            emit(response.trans_result[0].dst)
        }
}