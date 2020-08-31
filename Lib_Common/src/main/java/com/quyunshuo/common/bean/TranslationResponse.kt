package com.quyunshuo.common.bean

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: TranslationResponse
 * @Remark: 百度普通翻译Response
 */
data class TranslationResponse(
    val from: String,
    val to: String,
    val trans_result: List<TransResult>
)

data class TransResult(
    val dst: String,
    val src: String
)