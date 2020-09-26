package com.quyunshuo.androidbaseframemvvm;


import com.quyunshuo.androidbaseframemvvm.databinding.MyActivityLayoutBinding;
import com.quyunshuo.base.mvvm.v.BaseFrameActivity;

import org.jetbrains.annotations.NotNull;

/**
 * @author DBoy
 * @date 2020/9/26
 * Class 描述 :
 */
public class MyActivity extends BaseFrameActivity<MyActivityLayoutBinding, MyViewModel> {

    @Override
    protected void initView() {
        getMViewModel().test();
        getMBinding().testTv.setText("反射初始化 ViewBinding");
    }

    @Override
    protected void initViewObserve() {

    }

}
