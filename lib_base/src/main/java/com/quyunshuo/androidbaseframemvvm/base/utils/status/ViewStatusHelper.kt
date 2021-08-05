package com.quyunshuo.androidbaseframemvvm.base.utils.status

import android.os.Bundle

/**
 * @author DBoy 2021/8/5 <p>
 * - 文件描述 : 采用了一种链式调用，所有对象持有自己父级帮助类，进行场景回复时先恢复链头的数据
 */
abstract class ViewStatusHelper(val parentViewStatusHelper: ViewStatusHelper?) {

    open fun onRestoreInstanceStatus(savedInstanceState: Bundle?) {
        parentViewStatusHelper?.onRestoreInstanceStatus(savedInstanceState)
    }

    open fun onSaveInstanceState(bundle: Bundle) {
        parentViewStatusHelper?.onSaveInstanceState(bundle)
    }
}