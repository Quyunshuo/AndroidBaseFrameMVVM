package com.quyunshuo.translation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.quyunshuo.base.mvvm.vm.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: TranslationViewModel
 * @Remark:
 */
class TranslationViewModel : BaseViewModel<TranslationRepository>() {

    override fun initRepository() = TranslationRepository()

    // 私有的可变 LiveData
    private val _translationLiveData = MutableLiveData<String>()

    // 对外暴露不可变的 LiveData

    val translationLiveData: LiveData<String> = _translationLiveData

    /**
     * 请求翻译
     * @param original 需要翻译的文本
     */
    fun requestTranslation(original: String) {
        viewModelScope.launch {
            mRepository.getTranslation(original)
                .onStart {
                    // 请求开始之前的操作
                    isLoading.value = true
                }
                .catch {
                    // 捕获并处理上游抛出的异常
                    _translationLiveData.value = "error"
                    isLoading.value = false
                }
                .onCompletion {
                    // 已完成
                    isLoading.value = false
                }
                .collectLatest {
                    // 获取结果
                    _translationLiveData.value = it
                }
        }
    }
}