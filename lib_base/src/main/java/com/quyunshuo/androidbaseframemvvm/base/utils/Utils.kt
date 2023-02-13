package com.quyunshuo.androidbaseframemvvm.base.utils

import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 使用 Flow 做的简单的轮询
 * 请使用单独的协程来进行管理该 Flow
 * Flow 仍有一些操作符是实验性的 使用时需添加 @InternalCoroutinesApi 注解
 * @param intervals 轮询间隔时间/毫秒
 * @param block 需要执行的代码块
 */
suspend fun startPolling(intervals: Long, block: () -> Unit) {
    flow {
        while (true) {
            delay(intervals)
            emit(0)
        }
    }
        .catch { Log.e("flow", "startPolling: $it") }
        .flowOn(Dispatchers.Main)
        .collect { block.invoke() }
}
/**************************************************************************************************/

/**
 * 发送普通EventBus事件
 */
fun sendEvent(event: Any) = EventBusUtils.postEvent(event)

/**************************************************************************************************/
/**
 * 阿里路由不带参数跳转
 * @param routerUrl String 路由地址
 */
fun aRouterJump(routerUrl: String) {
    ARouter.getInstance().build(routerUrl).navigation()
}

/**************************************************************************************************/
/**
 * toast
 * @param msg String 文案
 * @param duration Int 时间
 */
fun toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    ToastUtils.showToast(msg, duration)
}

/**
 * toast
 * @param msgId Int String资源ID
 * @param duration Int 时间
 */
fun toast(msgId: Int, duration: Int = Toast.LENGTH_SHORT) {
    ToastUtils.showToast(msgId, duration)
}