# <p align="center"> AndroidBaseFrameMVVM 🐽</p>

<p align="center"> AndroidBaseFrameMVVM 是一个 Android 项目 MVVM 架构 开箱即用的框架 </p>

<p align="center"> 该框架基于 Kotlin + Flow + Jetpack + MVVM + 组件化 + Repository 模式实现</p>

<p align="center"> 该框架存在的意义一方面是秉承着我和大部分程序猿/媛"懒"的天性,实现可复用、不用重复搭项目架构、开箱微小修改即可上手新开项目,另一方面也是想把自己会的东西写出来,供其他学习这方面知识的同学借鉴和参考 </p>

<p align="center"> 下面展示该框架的架构图 👾</p>

<p align="center"><img src="https://github.com/Quyunshuo/AndroidBaseFrameMVVM/blob/master/img/img1.jpg"/> </p>

<p align="center"> 谷歌 Android 团队 Jetpack 视图模型 👾</p>

<p align="center"><img src="https://github.com/Quyunshuo/AndroidBaseFrameMVVM/blob/master/img/img2.png"/> </p>

## 框架技术栈

* 组件化 架构
* MVVM 架构
* Repository 设计模式
* [Kotlin](https://github.com/JetBrains/kotlin)
* [Kotlin-Coroutines-Flow](https://github.com/JetBrains/kotlin)
* [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
* [Android KTX](https://developer.android.com/kotlin/ktx)
* [OkHttp](https://github.com/square/okhttp):网络请求
* [Retrofit](https://github.com/square/retrofit):网络请求
* [MMKV](https://github.com/Tencent/MMKV):腾讯基于 mmap 内存映射的 key-value 本地存储组件
* [Glide](https://github.com/bumptech/glide):快速高效的Android图片加载库
* [ARoute](https://github.com/alibaba/ARouter):阿里用于帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦
* [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper):一个强大并且灵活的RecyclerViewAdapter
* [StatusBarUtil](https://github.com/laobie/StatusBarUtil):状态栏
* [EventBus](https://github.com/greenrobot/EventBus):适用于Android和Java的发布/订阅事件总线
* [Bugly](https://bugly.qq.com/v2/index):腾讯异常上报及热更新(只集成了异常上报)
* [PermissionX](https://github.com/guolindev/PermissionX):郭霖权限请求框架
* [Chuck](https://github.com/jgilfelt/chuck):适用于Android OkHttp 客户端的应用内HTTP拦截器 显示请求信息
* [LeakCanary](https://github.com/square/leakcanary):Android的内存泄漏检测库

## 使用方式
* 1.下载本项目删除无用的文件
* 2.修改项目包名及各组件包结构,修改 AppName
* 3.填写自己的 Bugly key 在 BaseApplication initialize() 方法中
* 这样就可以使用了,当然可以删除不用的第三方,或者添加相应要使用的第三方,具体规范看下面的框架解读

## 框架解读

**组件化相关**
* 本框架采用的是组件化架构,核心组件就是 Base 和 Common ,这两个组件都属于公共组件,负责为功能业务组件提供支持
* Base 组件主要集成了各种需要使用的第三方库和依赖或者公用的 aar/jar,并将依赖向依赖该组件的组件传递,需要集成的依赖,全部集成在 Base 组件内,Base 组件也提供了各种基类封装以及工具类、扩展函数、顶层函数,这些都应该是项目无关性的,可以达到 Base 模块直接拷贝复用的效果
* Common 组件主要是与项目有关的公用库,比如网络接口,全局常量, bean 类等,和项目有关的东西因该放在 Common 组件内,不要侵入 Base 组件,因为和项目有关的东西一旦放在了 Base 组件内,想要直接拷贝复用 Base 组件就不可能了,肯定会有一堆和项目相关的东西,项目的资源文件或者公用的资源文件最好统一放在 Common 组件内,方便公用,方便管理
* Base 和 Common 都属于公共组件,区别就在于 Base 比 Common 更底层,偏于与项目无关性,而 Common 是与项目有关性
* 项目的依赖版本管理和项目参数等配置统一写在了 buildSrc 文件夹内,内部维护了几个 kt 文件进行对依赖库版本及项目参数统一存放管理
* 功能组件应该依赖 Common 组件,壳 App 依赖所有的功能组件,要尽量避免各组件互相依赖,壳 App 内不要写东西,只当一个壳负责集成各个组件,每个组件都应该在 build.gradle 文件内设置资源命名规范,目的是为了避免资源冲突
```
android {
    resourcePrefix "资源名前缀"
}
```
* 各个功能业务组件可以单独运行,通过 buildSrc/BuildConfig.kt 中的 isAppMode 参数控制,项目业务复杂起来后,就需要为每个组件单独编写供其正常单独运行的逻辑代码

**MVVM相关**
* MVVM 采用 Jetpack 组件 + Repository 设计模式 实现,所使用的 Jetpack 并不是很多,像 DataBinding、Hilt(不支持多模块)、Room 等并没有使用,如果需要可以添加。采用架构模式目的就是为了解偶代码,对代码进行分层,各模块各司其职,所以既然使用了架构模式那就要遵守好规范
* Repository 仓库层负责数据的提供,ViewModel 无需关心数据的来源,Repository 内避免使用 LiveData,框架里使用了 Kotlin 协程的 Flow 进行处理请求或访问数据库,最后将数据发射到 ViewModel 调用者,Flow上游负责提供数据,下游也就是ViewModel获取到数据使用LiveData进行存储,View层订阅LiveData,实现数据驱动视图
* 三者的依赖都是单向依赖,View -> ViewModel -> Repository

## 结语
* 本人是一个刚大学毕业的 Android 开发者,技术相对来说比较薄弱,写这个框架主要是为了在以后自己使用、对自己技术做一个总结、对其他想学习的同学提供一个例子、也是想把一些东西开源出去,让这个社区更加的美好。文档中可能有些描述不太专业和生硬,我也很少写这些东西,所以表达起来很生疏。
* 如果你绝对该项目对你有用,那就请给一个 star🌟吧🥰