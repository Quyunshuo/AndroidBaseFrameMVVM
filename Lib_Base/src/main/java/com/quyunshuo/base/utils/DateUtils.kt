package com.quyunshuo.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 时间工具类
 * ________________________________________________________________________________________
 * |字母	|日期或时间元素	       | 表示	           | 示例                                  |
 * |:--:|:--------------------:|:-----------------:|:------------------------------------:|
 * |G	|Era 标志符	           | Text	           | AD                                   |
 * |y	|年	                   | Year              | 1996; 96                             |
 * |M	|年中的月份	           | Month	           | July; Jul; 07                        |
 * |w	|年中的周数	           | Number            | 27                                   |
 * |W	|月份中的周数	           | Number            | 2                                    |
 * |D	|年中的天数	           | Number            | 189                                  |
 * |d	|月份中的天数	           | Number            | 10                                   |
 * |F	|月份中的星期	           | Number            | 2                                    |
 * |E	|星期中的天数	           | Text	           | Tuesday; Tue                         |
 * |a	|Am/pm 标记	           | Text	           | PM                                   |
 * |H	|一天中的小时数(0-23)    |  Number            | 0                                    |
 * |k	|一天中的小时数(1-24）   |  Number            | 24                                   |
 * |K	|am/pm 中的小时数(0-11) |  Number            | 0                                    |
 * |h	|am/pm 中的小时数(1-12) |  Number            | 12                                   |
 * |m	|小时中的分钟数	       | Number            | 30                                   |
 * |s	|分钟中的秒数	           | Number            | 55                                   |
 * |S	|毫秒数	               | Number            | 978                                  |
 * |z	|时区	               | General time zone | Pacific Standard Time; PST; GMT-08:00|
 * |Z	|时区	               | RFC 822 time zone | -0800                                |
 * ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
 * @author Qu Yunshuo
 * @since 2020/9/8
 */
object DateUtils {

    /**
     * 获取时间格式化String
     * @param timestamp 时间戳
     * @param dateFormat 日期格式
     * @return 格式化后的字符串
     */
    fun getDateFormatString(timestamp: Long, dateFormat: String): String =
        SimpleDateFormat(dateFormat, Locale.CHINESE).format(Date(timestamp))

    /**
     * 将固定格式[dateFormat]的时间字符串[dateString]转换为时间值
     */
    fun getDateStringToDate(dateString: String, dateFormat: String): Long? {
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.CHINESE)
        var date: Date? = null
        try {
            date = simpleDateFormat.parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date?.time
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

    /**
     * String 转化 Calendar
     * @param string String
     * @param format String
     */
    fun stringToCalendar(string: String, format: String): Calendar? {
        val sdf = SimpleDateFormat(format, Locale.CHINESE)
        var calendar: Calendar
        try {
            val date: Date = sdf.parse(string) ?: return null
            calendar = Calendar.getInstance()
            calendar.time = date
        } catch (e: ParseException) {
            e.printStackTrace()
            calendar = Calendar.getInstance()
        }
        return calendar
    }

    /**
     * String 转化Date
     * @param str String
     * @param format String
     * @return Date
     */
    fun strToDate(str: String, format: String): Date? {
        val sdf = SimpleDateFormat(format, Locale.CHINESE)
        return try {
            sdf.parse(str)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    /**
     * 判断两个时间是否是同一天
     * @param cal1 Calendar
     * @param cal2 Calendar
     * @return Boolean
     */
    fun isSameDay(cal1: Calendar, cal2: Calendar): Boolean {
        return cal1[0] == cal2[0] && cal1[1] == cal2[1] && cal1[6] == cal2[6]
    }
}