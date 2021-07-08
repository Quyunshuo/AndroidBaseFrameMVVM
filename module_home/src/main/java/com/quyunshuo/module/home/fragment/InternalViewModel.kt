package com.quyunshuo.module.home.fragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author DBoy 2021/7/6 <p>
 * - 文件描述 :
 */
@HiltViewModel
class InternalViewModel @Inject constructor(private val repository: InternalRepository) : BaseViewModel() {

    /**
     * 重建计数
     */
    val recreatedCont = MutableLiveData<Int>()

    /**
     * 首个数据
     */
    val firstData = MutableLiveData<String>()

    /**
     * 累加重建次数
     */
    fun increase() {
        val value = recreatedCont.value ?: 0
        recreatedCont.value = value + 1
    }

    /**
     * 获取数据
     */
    fun getData() {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.getData()
                .catch {
                    Log.d("DJC", "getData: ")
                }.collect {
                    isLoading.postValue(false)
                    delay(200)
                    firstData.postValue(it)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("DJC","InternalViewModel Clear")
    }

}
