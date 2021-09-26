package com.quyunshuo.androidbaseframemvvm.base.ktx

import android.text.InputFilter
import android.widget.EditText

/**
 * EditText相关扩展方法
 *
 * @author Qu Yunshuo
 * @since 2020/9/17
 */

/**
 * 过滤掉空格和回车
 */
fun EditText.filterBlankAndCarriageReturn() {
    val filterList = mutableListOf<InputFilter>()
    filterList.addAll(filters)
    filterList.add(InputFilter { source, _, _, _, _, _ -> if (source == " " || source == "\n") "" else null })
    filters = filterList.toTypedArray()
}