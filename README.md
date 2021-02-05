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

## Demo
* 项目里有一个demo分支,这是我写的一个小例子,可以结合Demo去熟悉这个框架

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
* MVVM 采用 Jetpack 组件 + Repository 设计模式 实现,所使用的 Jetpack 并不是很多,像 DataBinding、Hilt、Room 等并没有使用,如果需要可以添加。采用架构模式目的就是为了解偶代码,对代码进行分层,各模块各司其职,所以既然使用了架构模式那就要遵守好规范
* Repository 仓库层负责数据的提供,ViewModel 无需关心数据的来源,Repository 内避免使用 LiveData,框架里使用了 Kotlin 协程的 Flow 进行处理请求或访问数据库,最后将数据发射到 ViewModel 调用者,Flow上游负责提供数据,下游也就是ViewModel获取到数据使用LiveData进行存储,View层订阅LiveData,实现数据驱动视图
* 三者的依赖都是单向依赖,View -> ViewModel -> Repository  

# <p align="center"> 示例代码及注意事项🐽</p>

## ViewModel
`ViewModel` 类旨在以注重生命周期的方式存储和管理界面相关的数据。`ViewModel` 类让数据可在发生屏幕旋转等配置更改后继续留存。  
**使用方式:**
```
class MainViewModel : ViewModel(){   
}

class MainActivity : AppCompatActivity() {
	// 获取无参构造的ViewModel实例
    val mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
}
```
**资料:**  
官方文档: [https://developer.android.com/topic/libraries/architecture/viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel)  
Android ViewModel，再学不会你砍我: [https://juejin.im/post/6844903919064186888](https://juejin.im/post/6844903919064186888)
## LiveData
`LiveData`是一种可观察的数据存储器类。与常规的可观察类不同，`LiveData` 具有生命周期感知能力，意指它遵循其他应用组件（如 `Activity`、`Fragment` 或 `Service`）的生命周期。这种感知能力可确保 `LiveData` 仅更新处于活跃生命周期状态的应用组件观察者  
LiveData 分为可变值的`MutableLiveData`和不可变值的`LiveData`  
**常用方法:**
```
fun test() {
        val liveData = MutableLiveData<String>()
        // 设置更新数据源
        liveData.value = "LiveData"
        // 将任务发布到主线程以设置给定值
        liveData.postValue("LiveData")
        // 获取值
        val value = liveData.value
        // 观察数据源更改(第一个参数应是owner:LifecycleOwner 比如实现了LifecycleOwner接口的Activity)
        liveData.observe(this, {
            // 数据源更改后触发的逻辑
        })
    }
```
**资料:**  
官方文档: [https://developer.android.com/topic/libraries/architecture/livedata](https://developer.android.com/topic/libraries/architecture/livedata)

## Lifecycle
`Lifecycle` 是一个类，用于存储有关组件（如 `Activity` 或 `Fragment`）的生命周期状态的信息，并允许其他对象观察此状态。  
`LifecycleOwner` 是单一方法接口，表示类具有 `Lifecycle`。它具有一种方法（即 `getLifecycle()`），该方法必须由类实现。  
实现 `LifecycleObserver` 的组件可与实现 `LifecycleOwner` 的组件无缝协同工作，因为所有者可以提供生命周期，而观察者可以注册以观察生命周期。

**资料:**  
官方文档: [https://developer.android.com/topic/libraries/architecture/lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)

## ViewBinding
通过视图绑定功能，您可以更轻松地编写可与视图交互的代码。在模块中启用视图绑定之后，系统会为该模块中的每个 XML 布局文件生成一个绑定类。绑定类的实例包含对在相应布局中具有 ID 的所有视图的直接引用。  
在大多数情况下，视图绑定会替代 `findViewById`  
**使用方式:**  
按模块启用`ViewBinding`
```
// 模块下的build.gradle文件
android {
	// 开启ViewBinding
    // 高版本AS
    buildFeatures {
        viewBinding = true
    }
    // 低版本AS 最低3.6
    viewBinding {
        enabled = true
    }
}
```
`Activity` 中 `ViewBinding` 的使用
```
// 之前设置视图的方法
setContentView(R.layout.activity_main)

// 使用ViewBinding后的方法
val mBinding = ActivityMainBinding.inflate(layoutInflater)
setContentView(mBinding.root)

// ActivityMainBinding类是根据布局自动生成的 如果没有请先build一下项目
// ViewBinding会将控件id转换为小驼峰命名法,所以为了保持一致规范,在xml里声明id时也请使用小驼峰命名法
// 比如你有一个id为mText的控件,可以这样使用
mBinding.mText.text = "ViewBinding"
```
`Fragment` 中 `ViewBinding` 的使用
```
// 原来的写法
return inflater.inflate(R.layout.fragment_blank, container, false)

// 使用ViewBinding的写法
mBinding = FragmentBlankBinding.inflate(inflater)
return mBinding.root
```
**资料:**  
官方文档: [https://developer.android.com/topic/libraries/view-binding](https://developer.android.com/topic/libraries/view-binding)  
CSDN: [https://blog.csdn.net/u010976213/article/details/104501830?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-5&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-5](https://blog.csdn.net/u010976213/article/details/104501830?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-5&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-5)

## ARoute
`ARoute`是阿里巴巴的一个用于帮助 Android App 进行组件化改造的框架 —— 支持模块间的路由、通信、解耦  
**使用方式:**
```
// 1.在需要进行路由跳转的Activity或Fragment上添加 @Route 注解
@Route(path = "/test/activity")
public class YourActivity extend Activity {
    ...
}

// 2.发起路由跳转
ARouter.getInstance()
    .build("目标路由地址")
    .navigation()
    
// 3.携带参数跳转
ARouter.getInstance()
	.build("目标路由地址")
    .withLong("key1", 666L)
    .withString("key3", "888")
    .withObject("key4", new Test("Jack", "Rose"))
    .navigation()

// 4.接收参数
@Route(path = RouteUrl.MainActivity2)
class MainActivity : AppCompatActivity() {

    // 通过name来映射URL中的不同参数
    @Autowired(name = "key")
    lateinit var name: String
    
	override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入 ARouter会自动对字段进行赋值，无需主动获取
        ARouter.getInstance().inject(this)
    }
}

// 5.获取Fragment
Fragment fragment = (Fragment) ARouter.getInstance().build("/test/fragment").navigation();
```
**资料:**   
官方文档:[https://github.com/alibaba/ARouter](https://github.com/alibaba/ARouter)

## 屏幕适配 AndroidAutoSize
屏幕适配使用的是 JessYan 大佬的 今日头条屏幕适配方案终极版  
GitHub: [https://github.com/JessYanCoding/AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize)  
**使用方式:**
```
// 在清单文件中声明
<manifest>
    <application> 
    // 主单位使用dp 没设置副单位
        <meta-data
            android:name="design_width_in_dp"
            android:value="360"/>
        <meta-data
            android:name="design_height_in_dp"
            android:value="640"/>           
     </application>           
</manifest>

// 默认是以竖屏的宽度为基准进行适配
// 如果是横屏项目要适配Pad(Pad适配尽量使用两套布局 因为手机和Pad屏幕宽比差距很大 无法完美适配)
<manifest>
    <application>            
    // 以高度为基准进行适配 (还需要手动代码设置以高度为基准进行适配) 目前以高度适配比宽度为基准适配 效果要好
        <meta-data
            android:name="design_height_in_dp"
            android:value="400"/>           
     </application>           
</manifest>

// 在Application 中设置
// 屏幕适配 AndroidAutoSize 以横屏高度为基准进行适配
AutoSizeConfig.getInstance().isBaseOnWidth = false
```
## EventBus APT
事件总线这里选择的还是`EventBus`,也有很多比较新的事件总线框架,还是选择了这个直接上手的
在框架内我对`EventBus`进行了基类封装,自动注册和解除注册,在需要注册的类上添加`@EventBusRegister`注解即可,无需关心内存泄漏及没及时解除注册的情况,基类里已经做了处理
```
@EventBusRegister
class MainActivity : AppCompatActivity() {
}
```
很多使用`EventBus`的其实都没有发现APT的功能,这是`EventBus3.0`的重大更新,使用`EventBus APT`可以编译期生成订阅类,这样就可以避免使用低效率的反射,很多人不知道这个更新,用着3.0的版本,实际上却是2.0的效率
项目中已经在各模块中开启了`EventBus APT`,`EventBus`会在编译器对各模块生成订阅类,需要我们手动代码去集成这些订阅类
```
// 因为App包依赖了所有的模块所以选择在App包下的Application中进行设置
// 开启EventBus APT
EventBus
     .builder()
	 .addIndex("各模块生成的订阅类的实例 类名在 moduleBase.gradle 脚本中进行了设置 比如 Lib_Main 生成的订阅类就是 MainEventIndex")
     .installDefaultEventBus()
```

## PermissionX 
`PermissionX` 是郭霖的一个权限申请框架  
**使用方式:**
```
PermissionX.init(this)
     .permissions("需要申请的权限")
     .request { allGranted, grantedList, deniedList -> }
```
**资料:**  
GitHub: [https://github.com/guolindev/PermissionX](https://github.com/guolindev/PermissionX)
## 组件化资源命名冲突
组件化方案中有一个坑就是资源命名冲突的问题,小则导致合并清单文件时资源丢失,大则直接导致崩溃
为了解决这个问题,需要在`build.gradle`文件中,对各模块声明资源前缀规则
```
android {
	// 前缀最好以组件包名规定
    resourcePrefix "main_"
}
```
## Kotlin 协程
`Kotlin Coroutines` 是一套解决异步编程的方案,在 Android 平台就是一个线程框架,用了都说好
**使用示例:**
* `Activity` 中
```
// 在 Activity 中 可以使用 lifecycleScope 协程作用域 进行开启协程 
// lifecycleScope 是 LifecycleOwner 的扩展属性 使用 lifecycleScope 你就无需关心Activity声明周期的问题 无需关心因为生命周期导致的协程泄漏的问题 可以说是真方便
// 使用示例
lifecycleScope.launch(Dispatchers.Default) {
    delay(1000L)
    ARouter.getInstance()
        .build(RouteUrl.MainActivity)
        .navigation()
    delay(100L)
    finish()
}
// launch() 函数开启了一个协程 调度器默认是Main 这里我指定了 Dispatchers.Default 
// 使用默认调度器可以直接使用 launch{}
// Activity 中还有一个协程作用域可以使用 就是 MainScope() 它会返回一个协程作用域实例 使用这个就需要我们手动去处理声明周期的问题了
```
* `ViewModel` 中使用
```
// 在 ViewModel 中可以使用
viewModelScope.launch {}
// 默认的调度器也是Main 同样也不需要我们去做生命周期的处理
```
* `Flow`  
`Flow`类似于`RxJava`,它也有一系列的操作符  
**资料:**  
Google 推荐在 MVVM 架构中使用 Kotlin Flow: [https://juejin.im/post/6854573211930066951](https://juejin.im/post/6854573211930066951)  
即学即用Kotlin - 协程: [https://juejin.im/post/6854573211418361864](https://juejin.im/post/6854573211418361864)  
Kotlin Coroutines Flow 系列(1-5): [https://juejin.im/post/6844904057530908679](https://juejin.im/post/6844904057530908679)  

## 结语
* 本人是一个刚大学毕业的 Android 开发者,技术相对来说比较薄弱,写这个框架主要是为了在以后自己使用、对自己技术做一个总结、对其他想学习的同学提供一个例子、也是想把一些东西开源出去,让这个社区更加的美好。文档中可能有些描述不太专业和生硬,我也很少写这些东西,所以表达起来很生疏。
* 如果你绝对该项目对你有用,那就请给一个 star🌟吧🥰