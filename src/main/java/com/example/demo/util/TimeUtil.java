package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {


    public static long getCurrentTime(){

        return fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
    }

    public static String getTimeInterval(long start, long end){
        System.out.println("前后时间差为： " + String.valueOf(end - start) + "ms");
        return  String.valueOf(end - start) + "ms";
    }

    public static long fromDateStringToLong(String inVal) {
        Date date = null;
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
        try {
            date = inputFormat.parse(inVal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
