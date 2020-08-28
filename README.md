# Android项目框架(组件化 + Kotlin + MVVM + Jetpack )
note: 1. 测试打包脚本

协程相关{
    Activity/Fragment 中 可以使用 lifecycleScope,与Activity/Fragment绑定了生命周期 无需手动取消
    ViewModel 中 可以使用 viewModelScope,它与ViewModel绑定了生命周期 无需手动取消
    lifecycleScope和viewModelScope 默认的调度器是Main
}