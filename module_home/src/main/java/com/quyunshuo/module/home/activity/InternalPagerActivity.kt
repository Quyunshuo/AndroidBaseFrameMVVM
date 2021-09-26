//package com.quyunshuo.module.home.activity
//
//import android.os.Bundle
//import androidx.activity.viewModels
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentActivity
//import com.quyunshuo.androidbaseframemvvm.base.mvvm.vm.EmptyViewModel
//import com.quyunshuo.androidbaseframemvvm.common.ui.BaseActivity
//import com.quyunshuo.androidbaseframemvvm.common.ui.BaseFragmentStateAdapter
//import com.quyunshuo.module.home.databinding.HomeActivityInternalLayoutBinding
//import com.quyunshuo.module.home.fragment.InternalFragment
//import dagger.hilt.android.AndroidEntryPoint
//import kotlin.random.Random
//
///**
// * @author DBoy 2021/7/6 <p>
// * - 文件描述 : ViewPager2+fragment 模拟Fragment页面重建。
// */
//@AndroidEntryPoint
//class InternalPagerActivity : BaseActivity<HomeActivityInternalLayoutBinding, EmptyViewModel>() {
//
//    override val mViewModel: EmptyViewModel by viewModels()
//
//    private val mCreateFragmentData = mutableListOf<String>()
//
//    private var mAdapter: InternalPagerFragmentAdapter? = null
//
//
//    override fun HomeActivityInternalLayoutBinding.initView() {
//        addFragment.setOnClickListener {
//            //添加一个随机页面
//            mAdapter?.addData("Pager ID:${Random.nextInt()}")
//        }
//        removeFragment.setOnClickListener {
//            //移除当前展示页面
////            mAdapter?.removeData("更多")
//            mAdapter?.removeData(viewPager.currentItem)
//        }
//        initPager()
//    }
//
//    private fun initPager() {
//        mCreateFragmentData.add("首页")
//        mCreateFragmentData.add("我的")
//        mCreateFragmentData.add("设置")
//        mCreateFragmentData.add("更多")
//        mCreateFragmentData.add("动态")
//        mAdapter = InternalPagerFragmentAdapter(this, mCreateFragmentData)
//        mBinding.viewPager.adapter = mAdapter
//
//    }
//
//    override fun initObserve() {}
//
//    override fun initRequestData() {}
//
//    class InternalPagerFragmentAdapter(activity: FragmentActivity, data: MutableList<String> = mutableListOf()) :
//        BaseFragmentStateAdapter<String>(activity, data) {
//        override fun createFragment(item: String, position: Int): Fragment {
//            val bundle = Bundle().apply {
//                putString("What", item)
//            }
//            return when (item) {
//                "首页" -> {
//                    //假装首页
//                    InternalFragment()
//                }
//                "我的" -> {
//                    //假装我的
//                    InternalFragment()
//                }
//                "设置" -> {
//                    //假装设置
//                    InternalFragment()
//                }
//                "更多" -> {
//                    //假装更多
//                    InternalFragment()
//                }
//                else -> {
//                    //另外动态item创建类型
//                    InternalFragment()
//                }
//            }.apply {
//                //设置传递参数bundle
//                arguments = bundle
//            }
//        }
//
//
//    }
//
//}