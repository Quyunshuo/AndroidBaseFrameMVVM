package com.quyunshuo.base.mvvm.v;

import android.view.LayoutInflater;

import com.quyunshuo.base.databinding.MyActivityBinding;

import org.jetbrains.annotations.NotNull;

/**
 * @author DBoy
 * @date 2020/9/26
 * Class 描述 :
 */
public class MyActivity extends MyBaseActivity<MyActivityBinding, MyViewModel> {

    @Override
    protected MyActivityBinding getViewBinding(LayoutInflater from) {
        return MyActivityBinding.inflate(from);
    }

    @Override
    protected void initViewAndData() {
        mViewModel.test();
    }
}
