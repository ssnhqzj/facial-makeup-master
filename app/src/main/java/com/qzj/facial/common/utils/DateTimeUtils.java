package com.qzj.facial.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

/**
 * 日期时间工具类
 */
public class DateTimeUtils {

    /**
     * long转指定格式字符串
     * @param dateFormat 格式
     * @param millSec long
     * @return 字符串时间
     */
    public static String longToDate(String dateFormat, long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date= new Date(millSec);
        return sdf.format(date);
    }

    /**
     * string转date
     * @param strTime 字符串时间
     * @param formatType 格式
     * @return date
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType, Locale.getDefault());
        return formatter.parse(strTime);
    }

    /**
     * date转long
     * @param date 时间
     * @return long
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * String转long
     * @param strTime 字符串时间
     * @param formatType 格式
     * @return long时间
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) throws ParseException {
        Date date = stringToDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            return dateToLong(date);
        }
    }

    /**
     * 获取long中的十和分
     * @param millSec long时间
     * @return  字符串时间
     */
    public static String longToDateForHM(long millSec) {
        return longToDate("HH:mm",millSec);
    }

    /**
     * 获取long中的十分秒
     * @param millSec long时间
     * @return  字符串时间
     */
    public static String longToDateForHMS(long millSec) {
        return longToDate("HH:mm:ss",millSec);
    }


    /**
     * 时分字符串转long
     * @param strTime 字符串时间
     * @return long时间
     */
    public static long stringToLongForHM(String strTime) {
        try {
            return stringToLong(strTime,"HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 时分字符串转long
     * @param strTime 字符串时间
     * @return long时间
     */
    public static long stringToLongForHMS(String strTime) {
        try {
            return stringToLong(strTime,"HH:mm:ss");
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * long时间转换成只有时分的long时间格式
     * @param time long时间
     * @return long
     */
    public static long longToLongForHM(long time){
        try {
            String timeHM = longToDateForHM(time);
            if (timeHM != null && !"".equals(timeHM)) {
                return stringToLong(timeHM,"HH:mm");
            }else{
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 提取long中的时分
     */
    @SuppressWarnings("unused")
    public static long extractHms(long time) {
        String HHmm = longToDateForHM(time);
        return stringToLongForHM(HHmm);
    }

    /**
     * 筛选出空闲时间段
     */
    public static ArrayList<long[]> filterFreeTime(ArrayList<long[]> timeBucket) {
        ArrayList<long[]> splitTimes = new ArrayList<>();
        ArrayList<Long> breakTimes = new ArrayList<>();

        long startTime = stringToLongForHM("00:00");
        long endTime = stringToLongForHM("24:00");

        breakTimes.add(startTime);
        breakTimes.add(endTime);

        if (timeBucket == null) {
            long[] time = new long[2];
            time[0] = startTime;
            time[1] = endTime;

            splitTimes.add(time);
            return splitTimes;
        }

        for (long[] tb : timeBucket) {
            if (tb != null && tb.length == 2 && tb[1] > tb[0]) {
                breakTimes.add(tb[0]);
                breakTimes.add(tb[1]);
            }
        }

        Collections.sort(breakTimes);

        for(int i=0; i<breakTimes.size()/2; i++) {
            long[] times = new long[2];
            times[0] = breakTimes.get(2*i);
            times[1] = breakTimes.get(2*i+1);

            splitTimes.add(times);
        }

        ArrayList<long[]> indexList = new ArrayList<>();
        for(int i=0;i<splitTimes.size();i++){
            long[] times = splitTimes.get(i);

            for(int j=0;j<timeBucket.size();j++){
                long[] times0 = timeBucket.get(j);
                if(times[0]<=times[1] && times[0]>=times0[0] && times[1]<=times0[1]){
                    indexList.add(times);
                }
            }
        }

        Iterator<long[]> it = splitTimes.iterator();
        while(it.hasNext()){
            long[] item = it.next();
            if(indexList.contains(item)){
                it.remove();
            }
        }

        for(long[] item : splitTimes){
            if(item != null && item.length==2){
                System.out.println("==================== ["+item[0]+"--"+item[1]+"]");
            }
        }

        return splitTimes;

    }

    /**
     * 分割时间
     * 将"18:30"分割成new String[]{"18","30"}
     */
    public static String[] splitTimeText(String time) {
        String[] result = new String[2];

        if (time == null || time.equals("")){
             result[0] = "00";
             result[1] = result[0];

            return result;
        }

        time = time.trim();
        int index = time.indexOf(":");
        if(index > 0) {
            String hour = time.substring(0,index);
            String min = time.substring(index + 1, time.length());
            result[0] = hour;
            result[1] = min;
        }

        return result;
    }

}
