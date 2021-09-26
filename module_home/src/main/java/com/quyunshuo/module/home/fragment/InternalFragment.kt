//package com.quyunshuo.module.home.fragment
//
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.viewModels
//import com.quyunshuo.androidbaseframemvvm.base.utils.status.ViewStatusHelper
//import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragment
//import com.quyunshuo.module.home.databinding.HomeFragmentInternalLayoutBinding
//import dagger.hilt.android.AndroidEntryPoint
//
///**
// * @author DBoy 2021/7/6 <p>
// * - 文件描述 : 测试fragment
// */
//@AndroidEntryPoint
//class InternalFragment : BaseFragment<HomeFragmentInternalLayoutBinding, InternalViewModel>() {
//
//    override val mViewModel by viewModels<InternalViewModel>()
//
//    /**
//     * 页面状态数据管理帮助类
//     */
//    private lateinit var mInternalFragmentStatusHelper: InternalFragmentStatusHelper
//
//    /***
//     * 注册帮助类
//     */
//    override fun onRegisterStatusHelper(): ViewStatusHelper? {
//        mInternalFragmentStatusHelper = InternalFragmentStatusHelper(super.onRegisterStatusHelper())
//        return mInternalFragmentStatusHelper
//    }
//
//    override fun HomeFragmentInternalLayoutBinding.initView() {}
//
//
//    override fun initObserve() {
//        mViewModel.increase(mInternalFragmentStatusHelper.rebuildSize)
//        val s = arguments?.getString("What") ?: ""
//        mBinding.toolBarTitle.text = s
//        mViewModel.recreatedCont.observe(viewLifecycleOwner) {
//            mBinding.recreateContTv.text = "重建次数 $it"
//        }
//        mViewModel.firstData.observe(viewLifecycleOwner) {
//            mBinding.loadDataTv.text = it
//        }
////        mViewModel.isLoading.observe(viewLifecycleOwner) {
////            mBinding.loadingStatusTv.text = if (it) {
////                "正在加载..."
////            } else {
////                "加载完成！"
////            }
////        }
//    }
//
//    override fun initRequestData() {
//        //当页面重建的时候不再重新请求数据，且当前页面数据数据有且没有刷新逻辑的情况下不再请求数据。
//        if (isRecreate() && mViewModel.firstData.value != null) {
//            return
//        }
//        mViewModel.getData()
//    }
//
//    /**
//     * 当前Fragment重建帮助类
//     */
//    internal class InternalFragmentStatusHelper(parentViewStatusHelper: ViewStatusHelper?) : ViewStatusHelper(parentViewStatusHelper) {
//        /**
//         * 重建次数
//         */
//        var rebuildSize = 0
//
//        private val KEY_REBUILD = "com.quyunshuo.module.home.fragment.InternalFragment.InternalFragmentStatusHelper.rebuild"
//
//        override fun onRestoreInstanceStatus(savedInstanceState: Bundle?) {
//            super.onRestoreInstanceStatus(savedInstanceState)
//            rebuildSize = (savedInstanceState?.getInt(KEY_REBUILD) ?: 0) + 1
//        }
//
//        override fun onSaveInstanceState(bundle: Bundle) {
//            super.onSaveInstanceState(bundle)
//            bundle.putInt(KEY_REBUILD, rebuildSize)
//        }
//    }
//
//}