package com.czht.smartpark.common.util;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class DateUtil {

    public static Date getDate() {
        return new Date();
    }

    public static String getDate(String pattern) {
        return new DateTime().toString(pattern);
    }

    /**
     * 格式化
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return new DateTime(date).toString(pattern);
    }

    /**
     * 格式化时间
     * @param dateTimeStr
     * @param pattern
     * @return
     */
    public static String format(String dateTimeStr, String pattern){
        if(dateTimeStr == null || dateTimeStr.equals("")) return "";
        DateTime date = DateTime.parse(dateTimeStr, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        return date.toString(pattern);
    }

    /**
     * 字符串转换成指定格式的日期
     * @param dateTimeStr
     * @return
     */
    public static Date parse(String dateTimeStr) {
        DateTime date = DateTime.parse(dateTimeStr, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        return date.toDate();
    }

    /**
     * 获得上周周一的时间
     * @return
     */
    public static String getPreMonday(){

        LocalDate d = LocalDate.now();
        return d.minusWeeks(1).dayOfWeek().withMinimumValue().toString("yyyy-MM-dd");
    }

    /**
     * 获得上周周日的时间
     * @return
     */
    public static String getPreSunday(){

        LocalDate d = LocalDate.now();
        return d.minusWeeks(1).dayOfWeek().withMaximumValue().toString("yyyy-MM-dd");
    }

    /**
     * 这周周一
     * @return
     */
    public static String getMonday(){

        return DateTime.now().dayOfWeek().withMinimumValue().toString("yyyy-MM-dd");
    }

    /**
     * 这周周日
     * @return
     */
    public static String getSunday(){

        return DateTime.now().dayOfWeek().withMaximumValue().toString("yyyy-MM-dd");
    }

    /**
     * 今天周几
     * @return
     */
    public static int getWeek(){
        return DateTime.now().dayOfWeek().get();
    }

    public static void main(String[] args) {
        System.out.println(format("2019-02-21 15:41:45", "yyyy-MM-dd"));
    }

}
