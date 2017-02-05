package com.qzj.facial.common.utils;

import java.util.Calendar;

/**
 * 日期时间工具类
 */
@SuppressWarnings("unused")
public class CalenderUtils {

    /**
     * 取得当月天数
     * */
    public static int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        // 把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        // 日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthLastDay(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        // 把日期设置为当月第一天
        a.set(Calendar.DATE, 1);
        // 日期回滚一天，也就是最后一天
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取当前日期时间
     * [0] 年
     * [1] 月
     * [2] 日
     * [3] 时
     * [4] 分
     * [5] 秒
     */
   public static int[] getCurrentDate(){
       int[] dateArr = new int[6];
       Calendar ca = Calendar.getInstance();
       dateArr[0] = ca.get(Calendar.YEAR);
       dateArr[1] = ca.get(Calendar.MONTH);
       dateArr[2] = ca.get(Calendar.DATE);
       dateArr[3] = ca.get(Calendar.HOUR);
       dateArr[4] = ca.get(Calendar.MINUTE);
       dateArr[5] = ca.get(Calendar.SECOND);

       return dateArr;
    }

}
