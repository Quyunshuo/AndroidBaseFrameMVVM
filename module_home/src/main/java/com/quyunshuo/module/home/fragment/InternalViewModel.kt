//package com.quyunshuo.module.home.fragment
//
//import android.util.Log
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
//import com.quyunshuo.androidbaseframemvvm.base.utils.status.ViewStatusHelper
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
///**
// * @author DBoy 2021/7/6 <p>
// * - 文件描述 : ViewModel再ViewPager2的Fragment中会随着Fragment执行[Fragment.onDestory]一同销毁。
// * 所以一些需要长期保存的变量数据，不适合保存再ViewModel，考虑使用[ViewStatusHelper]保存页面上部分数据，
// * 页面恢复的时候再交给ViewModel处理,例如[recreatedCont]
// */
//@HiltViewModel
//class InternalViewModel @Inject constructor() :
//    BaseViewModel() {
//
//    @Inject
//    lateinit var repository: InternalRepository
//
//    /**
//     * 重建计数
//     */
//    val recreatedCont = MutableLiveData<Int>()
//
//    /**
//     * 首个数据
//     */
//    val firstData = MutableLiveData<String>()
//
//    /**
//     * 累加重建次数
//     */
//    fun increase(size: Int) {
//        recreatedCont.value = size
//    }
//
//    /**
//     * 获取数据
//     */
//    fun getData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getData()
//                .catch {
//                    Log.d("DJC", "getData: ")
//                }
//                .onStart { changeStateView(loading = true) }
//                .collect {
//                    changeStateView(hide = true)
//                    delay(200)
//                    firstData.postValue(it)
//                }
//        }
//    }
//
//}
