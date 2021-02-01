package com.quyunshuo.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: QuYunShuo
 * @Time: 2020/9/8
 * @Class: DateUtils
 * @Remark: 时间工具类
 */
object DateUtils {
    /**
     * 获取时间格式化String
     * @param timestamp 时间戳
     * @param dateFormat 日期格式
     */
    fun getDateFormatString(timestamp: Long, dateFormat: String): String =
        SimpleDateFormat(dateFormat, Locale.CHINESE).format(Date(timestamp))

    /**
     * 将固定格式[dateFormat]的时间字符串[dateString]转换为时间值
     */
    fun getDateStringToDate(dateString: String, dateFormat: String): Long {
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.CHINESE)
        var date = Date()
        try {
            date = simpleDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.time
    }

    /**
     * 将计时毫秒值[millisecond]转换为时分秒
     */
    fun getGapTime(millisecond: Long): String {
        val hours = millisecond / (1000 * 60 * 60)
        val minutes = (millisecond - hours * (1000 * 60 * 60)) / (1000 * 60)
        val second = (millisecond - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / 1000
        var diffTime: String
        diffTime = if (minutes < 10) {
            "$hours:0$minutes"
        } else {
            "$hours:$minutes"
        }
        diffTime = if (second < 10) {
            "$diffTime:0$second"
        } else {
            "$diffTime:$second"
        }
        return diffTime
    }

    /**
     * 获取以当前日期为基准的某一时间段的日期
     * @param isFuture Boolean 真为未来时间 假为以前的时间
     * @param interval Int 间隔时间 以当前时间为基准 距今天前n天或后n天开始 就是n 0是当前日期
     * @param size String 时间区间长度  比如获取五天的时间 就是5 当前日期也算一天
     * @return List<String> 日期集合 顺序为日期的新旧程度
     * @throws RuntimeException 如果[interval]小于0或者[size]小于1会抛出[RuntimeException]
     *
     * 示例：获取后天开始 为期七天的时间就是 getExcerptDate(true, 2, 7)
     *      获取昨天开始再往前7天的时间 getExcerptDate(false, 1, 7)
     */
    fun getExcerptDate(
        isFuture: Boolean,
        interval: Int,
        size: Int,
        dateFormat: String
    ): List<String> {
        if (interval < 0) throw RuntimeException("\"interval\" it can't be less than 0")
        if (size < 1) throw RuntimeException("\"size\" it can't be less than 1")
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.CHINESE)
        val calendar = Calendar.getInstance()
        val currentDayOfYear = calendar.get(Calendar.DAY_OF_YEAR)
        val currentYear = calendar.get(Calendar.YEAR)
        val dateList = mutableListOf<String>()
        if (isFuture) {
            (interval until interval + size).forEach {
                val timestamp = getSomedayDate(it, calendar, currentDayOfYear, currentYear)
                dateList.add(simpleDateFormat.format(timestamp))
            }
        } else {
            (-interval downTo -interval - size + 1).forEach {
                val timestamp = getSomedayDate(it, calendar, currentDayOfYear, currentYear)
                dateList.add(simpleDateFormat.format(timestamp))
            }
        }
        return dateList
    }

    /**
     * 获取距离今天的某一天的时间戳
     * @param numberOfDaysBetween Int 间隔今天的天数 正数为未来时间 负数为以前的时间
     * @param calendar Calendar Calendar对象 使用依赖注入方式 提高对象的复用性
     * @param currentDayOfYear Int 当前时间在当年的天 使用Calendar获取
     * @param currentYear Int 当前年 使用Calendar获取
     * @return Long 时间戳
     */
    fun getSomedayDate(
        numberOfDaysBetween: Int,
        calendar: Calendar,
        currentDayOfYear: Int,
        currentYear: Int
    ): Long {
        calendar.set(Calendar.DAY_OF_YEAR, currentDayOfYear)
        calendar.set(Calendar.YEAR, currentYear)
        calendar.set(
            Calendar.DAY_OF_YEAR,
            calendar.get(Calendar.DAY_OF_YEAR) + numberOfDaysBetween
        )
        return calendar.time.time
    }
}