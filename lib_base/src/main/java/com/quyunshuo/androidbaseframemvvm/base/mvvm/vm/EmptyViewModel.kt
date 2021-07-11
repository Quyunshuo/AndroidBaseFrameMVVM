package com.quyunshuo.androidbaseframemvvm.base.mvvm.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 空的ViewModel 主要给现阶段不需要ViewModel的界面使用
 *
 * @author Qu Yunshuo
 * @since 2021/7/10 11:04 上午
 */
@HiltViewModel
class EmptyViewModel @Inject constructor() : BaseViewModel()