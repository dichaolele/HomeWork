package com.example.news.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtil {
    /**
     * 将日历的日期转换成字符串,格式为 20191205
     * @param calendar
     */
    public static String time2String(Calendar calendar){
        //SimpleDateFormat 格式化日期的
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(calendar.getTime());
    }
}
