package com.quyunshuo.androidbaseframemvvm.base.utils

import android.content.pm.PackageInfo
import android.os.Build
import com.quyunshuo.androidbaseframemvvm.base.BaseApplication

/**
 * App 相关工具类
 *
 * @author Qu Yunshuo
 * @sine 2023/2/13 23:15
 */
class AppUtils {

    /**
     * 获取当前 App 版本号
     *
     * @return Long
     */
    fun getAppVersionCode(): Long {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getAppPackageInfo().longVersionCode
        } else {
            getAppPackageInfo().versionCode.toLong()
        }
    }

    /**
     * 获取当前 App 版本名
     *
     * @return String
     */
    fun getAppVersionName(): String = getAppPackageInfo().versionName

    /**
     * 获取当前 App 的 [PackageInfo]
     *
     * @return PackageInfo
     */
    fun getAppPackageInfo(): PackageInfo {
        return BaseApplication.context
            .packageManager
            .getPackageInfo(BaseApplication.context.packageName, 0)
    }
}