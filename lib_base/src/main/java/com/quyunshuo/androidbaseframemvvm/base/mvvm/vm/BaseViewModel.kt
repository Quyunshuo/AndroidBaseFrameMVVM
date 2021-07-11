package com.quyunshuo.androidbaseframemvvm.base.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.quyunshuo.androidbaseframemvvm.base.utils.StateLayoutEnum
import kotlin.jvm.Throws

/**
 * ViewModel 基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * 控制状态视图的LiveData
     */
    val stateViewLD = MutableLiveData<StateLayoutEnum>()

    /**
     * 更改状态视图的状态
     *
     * @param hide Boolean 是否进行隐藏状态视图
     * @param loading Boolean 是否显示加载中视图
     * @param error Boolean 是否显示错误视图
     * @param noData Boolean 是否显示没有数据视图
     * @return Unit
     * @throws IllegalArgumentException 如果入参没有传入任何参数或者为true的参数 >1 时，会抛出[IllegalArgumentException]
     */
    @Throws(IllegalArgumentException::class)
    protected fun changeStateView(
        hide: Boolean = false,
        loading: Boolean = false,
        error: Boolean = false,
        noData: Boolean = false
    ) {
        // 对参数进行校验
        var count = 0
        if (hide) count++
        if (loading) count++
        if (error) count++
        if (noData) count++
        when {
            count == 0 -> throw IllegalArgumentException("必须设置一个参数为true")
            count > 1 -> throw IllegalArgumentException("只能有一个参数为true")
        }

        // 修改状态
        when {
            hide -> stateViewLD.postValue(StateLayoutEnum.HIDE)
            loading -> stateViewLD.postValue(StateLayoutEnum.LOADING)
            error -> stateViewLD.postValue(StateLayoutEnum.ERROR)
            noData -> stateViewLD.postValue(StateLayoutEnum.NO_DATA)
        }
    }
}