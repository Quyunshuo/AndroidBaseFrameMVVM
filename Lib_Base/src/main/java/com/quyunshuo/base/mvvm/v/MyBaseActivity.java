package com.quyunshuo.base.mvvm.v;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.ParameterizedType;

/**
 * @author DBoy
 * @date 2020/9/26
 * Class 描述 :
 */
public abstract class MyBaseActivity<V extends ViewBinding, M extends ViewModel> extends AppCompatActivity {

    protected V viewBinding;

    protected M mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = getViewBinding(LayoutInflater.from(this));
        setContentView(viewBinding.getRoot());

        //init ViewModel | getActualTypeArguments[0] =是第一个泛型参数 [1] = 是类的第二个泛型参数
        Class<M> tClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        mViewModel = new ViewModelProvider(this).get(tClass);

        initViewAndData();
    }

    protected abstract V getViewBinding(LayoutInflater from);

    protected abstract void initViewAndData();

}
