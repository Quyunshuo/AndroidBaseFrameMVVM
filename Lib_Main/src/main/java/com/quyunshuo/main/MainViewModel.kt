package com.quyunshuo.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyunshuo.base.mvvm.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/28
 * @Class: MainViewModel
 * @Remark:
 */
class MainViewModel : BaseViewModel<MainRepository>() {

    val msg: MutableLiveData<String> = MutableLiveData<String>()

    override fun initRepository(): MainRepository = MainRepository()

    fun getString() {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.getString().collectLatest {
                msg.postValue(it)
            }
        }
    }
}