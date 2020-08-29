# Android项目框架(组件化 + Kotlin + MVVM + Jetpack )

note: 1. 测试打包脚本
      2. 对比开源项目flow的封装

资源文件相关{
    资源相关文件属于项目相关的，因此需要放在Common组件内，不要放在Base组件里
    String、Color、Style、layout、drawable、mipmap 公用的 放在Common组件内，不公用的放在各自组件内，命名以各组件的规则命名(build文件设置了规则)
}

协程相关{
    Activity/Fragment 中 可以使用 lifecycleScope,与Activity/Fragment绑定了生命周期 无需手动取消
    ViewModel 中 可以使用 viewModelScope,它与ViewModel绑定了生命周期 无需手动取消
    lifecycleScope和viewModelScope 默认的调度器是Main
}

第三方库{
    屏幕适配 AndroidAutoSize : https://github.com/JessYanCoding/AndroidAutoSize
    本地存储 MMKV : https://github.com/Tencent/MMKV
    路由    ARouter : https://github.com/alibaba/ARouter
}

网络{
    关于请求接口的代理接口,建议按接口的使用范围进行划分，比如公用接口单独写在一个接口里，Home页用的接口单独写在一个接口里...
    然后在NetRequest类中统一委托延时初始化实例，调用者直接通过NetRequest进行选择性调用相应的代理接口
}