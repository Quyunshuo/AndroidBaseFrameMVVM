package com.quyunshuo.androidbaseframemvvm.base.utils

/**
 * 辅助注册 EventBus 的注解
 *
 * - **使用方式：**
 * 在基类中的 `onCreate()`、`onDestroy()` 生命周期回调中去判断当前 Class 对象是否使用了该注解，
 * 然后根据结果去注册或反注册
 *
 * - **为什么不统一注册：**
 * 统一注册会在 EventBus 内部集合中留存，每次发送事件时，会遍历集合，过多无用的注册会导致速度变慢，
 * 所以最好的方式就是根据需要进行注册，避免无意义的全部注册
 *
 * - **sample:**
 * ```
 * abstract class BaseActivity : AppCompatActivity() {
 *
 *     //  是否有 [RegisterEventBus] 注解 ， 避免重复调用 [Class.isAnnotation]
 *     private var mHaveRegisterEventBus = false
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         // 根据子类是否有 RegisterEventBus 注解決定是否进行注册 EventBus
 *         if (javaClass.isAnnotationPresent(RegisterEventBus::class.java)) {
 *             mHaveRegisterEventBus = true
 *             EventBusUtils.register(this)
 *         }
 *     }
 *
 *     override fun onDestroy() {
 *         // 根据子类是否有 RegisterEventBus 注解决定是否进行注册 EventBus
 *         if (mHaveRegisterEventBus) {
 *             EventBusUtils.unRegister(this)
 *         }
 *         super.onDestroy()
 *     }
 * }
 *
 * // 子类：
 * @RegisterEventBus
 * class SampleActivity : BaseActivity()
 * ```
 *
 * @author Qu Yunshuo
 * @since 2020/8/29
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class RegisterEventBus