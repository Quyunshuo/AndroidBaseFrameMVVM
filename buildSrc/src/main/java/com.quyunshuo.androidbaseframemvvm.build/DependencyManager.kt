package com.quyunshuo.androidbaseframemvvm.build

/**
 * 依赖库管理
 */
object Version {
    // AndroidX--------------------------------------------------------------
    const val AppCompat = "1.2.0"
    const val CoreKtx = "1.3.1"
    const val ConstraintLayout = "2.0.1"                // 约束布局
    const val TestExtJunit = "1.1.2"
    const val TestEspresso = "3.3.0"
    const val ActivityKtx = "1.1.0"
    const val FragmentKtx = "1.2.5"
    const val MultiDex = "2.0.1"

    // Android---------------------------------------------------------------
    const val Junit = "4.13"

    // Kotlin----------------------------------------------------------------
    const val Kotlin = "1.4.31"                         // Kotlin
    const val Coroutines = "1.4.3"                      // 协程

    // JetPack---------------------------------------------------------------
    const val LifecycleViewModel = "2.2.0"
    const val LifecycleRuntimeKtx = "2.2.0"
    const val LifecycleViewModelKtx = "2.2.0"

    // GitHub----------------------------------------------------------------
    const val OkHttp = "3.14.9"                         // OkHttp
    const val OkHttpInterceptorLogging = "3.12.0"       // OkHttp 请求Log拦截器
    const val Retrofit = "2.9.0"                        // Retrofit
    const val RetrofitConverterGson = "2.9.0"           // Retrofit Gson 转换器
    const val Gson = "2.8.6"                            // Gson
    const val MMKV = "1.2.2"                            // 腾讯 MMKV 替代SP
    const val AutoSize = "1.2.1"                        // 屏幕适配
    const val Glide = "4.11.0"                          // Glide
    const val ARoute = "1.5.1"                          // 阿里路由
    const val ARouteCompiler = "1.5.1"                  // 阿里路由 APT
    const val RecyclerViewAdapter = "3.0.4"             // RecyclerViewAdapter
    const val StatusBar = "1.5.1"                       // 状态栏
    const val EventBus = "3.2.0"                        // 事件总线
    const val Bugly = "3.3.7"                           // Bugly 异常上报
    const val BuglyNative = "3.8.0"                     // Bugly native异常上报
    const val PermissionX = "1.3.0"                     // 权限申请
    const val LeakCanary = "2.4"                        // 检测内存泄漏
    const val Chuck = "1.1.0"                           // OkHttp 请求信息拦截器(UI)
}

object AndroidX {
    const val AndroidJUnitRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val AppCompat = "androidx.appcompat:appcompat:${Version.AppCompat}"
    const val CoreKtx = "androidx.core:core-ktx:${Version.CoreKtx}"
    const val ConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.ConstraintLayout}"
    const val TestExtJunit = "androidx.test.ext:junit:${Version.TestExtJunit}"
    const val TestEspresso = "androidx.test.espresso:espresso-core:${Version.TestEspresso}"
    const val ActivityKtx = "androidx.activity:activity-ktx:${Version.ActivityKtx}"
    const val FragmentKtx = "androidx.fragment:fragment-ktx:${Version.FragmentKtx}"
    const val MultiDex = "androidx.multidex:multidex:${Version.MultiDex}"
}

object Android {
    const val Junit = "junit:junit:${Version.Junit}"
}

object JetPack {
    const val LifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel:${Version.LifecycleViewModel}"
    const val LifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.LifecycleRuntimeKtx}"
    const val LifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LifecycleViewModelKtx}"
}

object Kotlin {
    const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.Kotlin}"
    const val CoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Coroutines}"
    const val CoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Coroutines}"
}

object GitHub {
    const val OkHttp = "com.squareup.okhttp3:okhttp:${Version.OkHttp}"
    const val OkHttpInterceptorLogging =
        "com.squareup.okhttp3:logging-interceptor:${Version.OkHttpInterceptorLogging}"
    const val Retrofit = "com.squareup.retrofit2:retrofit:${Version.Retrofit}"
    const val RetrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${Version.RetrofitConverterGson}"
    const val Gson = "com.google.code.gson:gson:${Version.Gson}"
    const val MMKV = "com.tencent:mmkv-static:${Version.MMKV}"
    const val AutoSize = "me.jessyan:autosize:${Version.AutoSize}"
    const val Glide = "com.github.bumptech.glide:glide:${Version.Glide}"
    const val GlideCompiler = "com.github.bumptech.glide:compiler:${Version.Glide}"
    const val ARoute = "com.alibaba:arouter-api:${Version.ARoute}"
    const val ARouteCompiler = "com.alibaba:arouter-compiler:${Version.ARouteCompiler}"
    const val RecyclerViewAdapter =
        "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.RecyclerViewAdapter}"
    const val StatusBar = "com.jaeger.statusbarutil:library:${Version.StatusBar}"
    const val EventBus = "org.greenrobot:eventbus:${Version.EventBus}"
    const val EventBusAPT = "org.greenrobot:eventbus-annotation-processor:${Version.EventBus}"
    const val Bugly = "com.tencent.bugly:crashreport:${Version.Bugly}"
    const val BuglyNative = "com.tencent.bugly:nativecrashreport:${Version.BuglyNative}"
    const val PermissionX = "com.permissionx.guolindev:permissionx:${Version.PermissionX}"
    const val LeakCanary = "com.squareup.leakcanary:leakcanary-android:${Version.LeakCanary}"
    const val Chuck = "com.readystatesoftware.chuck:library:${Version.Chuck}"
    const val ChuckNo = "com.readystatesoftware.chuck:library-no-op:${Version.Chuck}"
}