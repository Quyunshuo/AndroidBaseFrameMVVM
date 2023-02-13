package com.quyunshuo.androidbaseframemvvm.base.ktx

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * 开启一个线程调度模式为[Dispatchers.IO]的协程 有默认的异常处理器
 *
 * **sample:**
 * ```
 * class SampleViewModel : ViewModel() {
 *
 *     fun sample() {
 *         launchIO {
 *             // 协程体
 *         }
 *         launchIO(exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
 *             // exception handling
 *         }) {
 *             // 协程体
 *         }
 *     }
 * }
 * ```
 *
 * @receiver ViewModel
 *
 * @param exceptionHandler CoroutineExceptionHandler 异常处理器
 * @param block suspend CoroutineScope.() -> Unit 协程体
 * @return Job
 */
fun ViewModel.launchIO(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.IO + exceptionHandler, block = block)

/**
 * 开启一个线程调度模式为[Dispatchers.Default]的协程 有默认的异常处理器
 *
 * **sample:**
 * ```
 * class SampleViewModel : ViewModel() {
 *
 *     fun sample() {
 *         launchDefault {
 *             // 协程体
 *         }
 *         launchDefault(exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
 *             // exception handling
 *         }) {
 *             // 协程体
 *         }
 *     }
 * }
 * ```
 *
 * @receiver ViewModel
 *
 * @param exceptionHandler CoroutineExceptionHandler 异常处理器
 * @param block suspend CoroutineScope.() -> Unit 协程体
 * @return Job
 */
fun ViewModel.launchDefault(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.Default + exceptionHandler, block = block)

/**
 * 开启一个线程调度模式为[Dispatchers.Main]的协程 有默认的异常处理器
 *
 * **sample:**
 * ```
 * class SampleViewModel : ViewModel() {
 *
 *     fun sample() {
 *         launchMain {
 *             // 协程体
 *         }
 *         launchMain(exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
 *             // exception handling
 *         }) {
 *             // 协程体
 *         }
 *     }
 * }
 * ```
 *
 * @receiver ViewModel
 *
 * @param exceptionHandler CoroutineExceptionHandler 异常处理器
 * @param block suspend CoroutineScope.() -> Unit 协程体
 * @return Job
 */
fun ViewModel.launchMain(
    exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    },
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(Dispatchers.Main + exceptionHandler, block = block)