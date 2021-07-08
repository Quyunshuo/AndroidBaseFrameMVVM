package com.quyunshuo.module.home.activity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.quyunshuo.androidbaseframemvvm.common.ui.BaseActivity
import com.quyunshuo.module.home.databinding.HomeActivityInternalLayoutBinding
import com.quyunshuo.module.home.fragment.InternalFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author DBoy 2021/7/6 <p>
 * - 文件描述 : ViewPager2+fragment 模拟Fragment页面重建。
 */
@AndroidEntryPoint
class InternalPagerActivity : BaseActivity<HomeActivityInternalLayoutBinding>() {

    override fun HomeActivityInternalLayoutBinding.initView() {
        initPager()
    }

    private fun initPager() {
        val fragments = mutableListOf<Fragment>(
            InternalFragment(),
            InternalFragment(),
            InternalFragment(),
            InternalFragment(),
            InternalFragment(),
            InternalFragment(),
            InternalFragment()
        )
        mBinding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = fragments.size
            override fun createFragment(position: Int): Fragment = fragments[position]
        }

    }

    override fun initLiveDataObserve() {

    }

    override fun initRequestData() {

    }

}