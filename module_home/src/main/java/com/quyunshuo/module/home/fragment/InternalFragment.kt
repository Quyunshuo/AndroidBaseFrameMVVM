package com.quyunshuo.module.home.fragment

import androidx.fragment.app.viewModels
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
import com.quyunshuo.module.home.databinding.HomeFragmentInternalLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author DBoy 2021/7/6 <p>
 * - 文件描述 : 测试fragment
 */
@AndroidEntryPoint
class InternalFragment : BaseFragment<HomeFragmentInternalLayoutBinding>() {

    private val mViewModel by viewModels<InternalViewModel>()

    override fun HomeFragmentInternalLayoutBinding.initView() {

    }

    override fun initLiveDataObserve() {
        mViewModel.recreatedCont.observe(viewLifecycleOwner) {
            mBinding.recreateContTv.text = "重建次数 $it"
        }
        mViewModel.firstData.observe(viewLifecycleOwner) {
            mBinding.loadDataTv.text = it
        }
        mViewModel.isLoading.observe(viewLifecycleOwner) {
            mBinding.loadingStatusTv.text = if (it) {
                "正在加载..."
            } else {
                "加载完成！"
            }
        }
    }

    override fun initRequestData() {
        //每次重建都会累加数据
        mViewModel.increase()
        //当页面重建的时候不再重新请求数据，且当前页面数据数据有且没有刷新逻辑的情况下不再请求数据。
        if (isRecreate() && mViewModel.firstData.value != null) {
            return
        }
        mViewModel.getData()
    }


}