package com.quyunshuo.translation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.text.InputType
import com.alibaba.android.arouter.facade.annotation.Route
import com.quyunshuo.base.ktx.gone
import com.quyunshuo.base.ktx.toast
import com.quyunshuo.base.ktx.visible
import com.quyunshuo.common.constant.RouteUrl
import com.quyunshuo.common.ui.BaseActivity
import com.quyunshuo.translation.databinding.TranslationActivityTranslationBinding
import kotlinx.android.synthetic.main.translation_activity_translation.*

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: TranslationActivity
 * @Remark: 翻译主页
 */
@Route(path = RouteUrl.TranslationActivity)
class TranslationActivity :
    BaseActivity<TranslationActivityTranslationBinding, TranslationViewModel>(TranslationViewModel::class.java) {

    override fun initViewBinding() = TranslationActivityTranslationBinding.inflate(layoutInflater)

    override fun initView() {
        // 设置EditText
        mBinding.mEdit.run {
            inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
            isSingleLine = false
            setHorizontallyScrolling(false)
        }
        // 拷贝
        mBinding.mCopyImg.setOnClickListener {
            val clipboardManager: ClipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboardManager.setPrimaryClip(ClipData.newPlainText(mShowTv.text, mShowTv.text))
            toast("已复制")
        }
        // 清空输入框
        mBinding.mCleanImg.setOnClickListener { mBinding.mEdit.setText("") }
        // 翻译按钮监听
        mBinding.mTranslationBtn.setOnClickListener {
            if (mEdit.text.toString().isNotEmpty()) {
                // 请求翻译
                mViewModel.requestTranslation(mEdit.text.toString())
            }
        }
    }

    override fun initViewObserve() {
        // 观察 ViewModel 的 translationLiveData,当其发生改变时,做出响应
        mViewModel.translationLiveData.observe(this, { mBinding.mShowTv.text = it })
        // Loading 图
        mViewModel.isLoading.observe(this, {
            if (it) {
                mBinding.mLoadingLayout.visible()
            } else {
                mBinding.mLoadingLayout.gone()
            }
        })
    }
}