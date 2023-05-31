package com.quyunshuo.androidbaseframemvvm.base.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.quyunshuo.androidbaseframemvvm.base.BaseApplication

/**
 * 剪切板工具类
 *
 * @author Qu Yunshuo
 * @since 2023/5/31 10:27
 */
object ClipboardUtils {

    /**
     * 复制内容到剪切板
     *
     * @param text String 内容
     * @param label String 标签，用于区分内容
     */
    fun copyToClipboard(text: String, label: String = "") {
        val clipboard =
            BaseApplication.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.setPrimaryClip(clip)
    }
}