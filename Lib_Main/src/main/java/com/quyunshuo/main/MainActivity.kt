package com.quyunshuo.main

import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.quyunshuo.base.ktx.toast
import com.quyunshuo.base.utils.EventBusRegister
import com.quyunshuo.base.utils.sendEvent
import com.quyunshuo.common.bean.TestBean
import com.quyunshuo.common.constant.RouteKey
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.main.databinding.MainActivityMainBinding
import org.greenrobot.eventbus.Subscribe

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/27
 * @Class: MainActivity
 * @Remark: 主界面Activity
 */
@EventBusRegister
@Route(path = RouteUrl.MainActivity)
class MainActivity :
    BaseActivity<MainActivityMainBinding, MainViewModel>(MainViewModel::class.java) {

    override fun initViewBinding(): MainActivityMainBinding =
        MainActivityMainBinding.inflate(layoutInflater)

    override fun initView() {
        mViewModel.msg.observe(this, {
            mBinding.mTv.text = it
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        mBinding.mBtn.setOnClickListener { mViewModel.getString() }
        mBinding.mIntentBtn.setOnClickListener {
            ARouter.getInstance().build(RouteUrl.MainActivity2)
                .withString(RouteKey.KEY_NAME, "ARouter").navigation()
        }
        sendEvent(TestBean("EventBus"))
    }

    @Subscribe
    fun onEvent(event: TestBean) {
        toast(event.msgTest)
    }
}