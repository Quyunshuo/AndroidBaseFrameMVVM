package com.quyunshuo.main.ui.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.R
import com.quyunshuo.main.databinding.MainActivityMainBinding

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: MainActivity
 * @Remark: 首页
 */
@Route(path = RouteUrl.MainActivity)
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding() = MainActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        setStatusBarColor(resources.getColor(R.color.common_theme_color))
        val sheetBehavior =
            BottomSheetBehavior.from(mBinding.mSelectFunctionLayout.mBottomSheetLayout).apply {
                state = BottomSheetBehavior.STATE_HIDDEN
                isHideable = true
            }
        mBinding.mSelectFunctionBtn.setOnClickListener {
            if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            } else {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        mBinding.mSelectFunctionLayout.mTranslationTv.setOnClickListener {
            // 跳转到翻译模块
            ARouter.getInstance()
                .build(RouteUrl.TranslationActivity)
                .navigation()
            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun initViewObserve() {}
}