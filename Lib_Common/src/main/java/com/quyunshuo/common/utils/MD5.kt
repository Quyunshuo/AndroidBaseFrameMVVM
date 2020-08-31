package com.quyunshuo.common.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @Author: QuYunShuo
 * @Time: 2020/8/31
 * @Class: MD5
 * @Remark:百度翻译MD5加密
 */
object MD5 {

    fun encode(password: String): String {
        try {
            val instance: MessageDigest = MessageDigest.getInstance("MD5")  // 获取md5加密对象
            val digest: ByteArray =
                instance.digest(password.toByteArray())                     // 对字符串加密，返回字节数组
            val sb = StringBuffer()
            for (b in digest) {
                val i: Int = b.toInt() and 0xff                             // 获取低八位有效值
                var hexString = Integer.toHexString(i)                      // 将整数转化为16进制
                if (hexString.length < 2) {
                    hexString = "0$hexString"                               // 如果是一位的话，补0
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }
}
