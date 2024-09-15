package com.wwk.badminton.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeUtil {
    //获取当前时间
    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = format.format(date);
        return formattedDate;
    }
    //比较两个时间大小
    public static boolean compareTime(String time1,String time2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);
        if (dateTime1.isBefore(dateTime2)) {
            return  true;
        } else if (dateTime1.isAfter(dateTime2)) {
            return false;
        } else {
            return false;
        }
    }
    //判断当前时间是否在两个时间范围内
    public static boolean isBetweenTime(String time1,String time2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(time1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(time2, formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(getTime(), formatter);
        System.out.println(dateTime1.isBefore(localDateTime));
        System.out.println(dateTime2.isAfter(localDateTime));
        if (dateTime1.isBefore(localDateTime) && dateTime2.isAfter(localDateTime)){
            return true;
        }else return false;
    }
    //获取当前时间以年月日格式
    public static String getNyrTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(date);
        return formattedDate;
    }

    //获取可用时间
    public static List<String> getAvailableTime(){
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = currentTime.withMinute(0).withSecond(0).withNano(0).plusHours(1); // 获取当前所在的整点小时
        System.out.println(startTime);
        startTime = startTime.isBefore(LocalTime.of(9, 0)) ? LocalTime.of(9, 0) : startTime; // 如果当前时间早于9点，则从9点开始
        LocalTime endTime = startTime.plusHours(1).withMinute(0).withSecond(0).withNano(0); // 结束时间为下一个整点小时
        List<String> availableTimeRanges = new ArrayList<>();

        while (startTime.isBefore(LocalTime.of(21, 0)) && startTime.isBefore(endTime)) {
            if (endTime.isAfter(LocalTime.of(21, 0))) {
                endTime = LocalTime.of(21, 0); // 如果结束时间超过21点，则设置为21点
            }
            availableTimeRanges.add(startTime + " - " + endTime);
            if(endTime.isBefore(LocalTime.of(21, 0))) endTime = endTime.plusHours(1);
            else {startTime = startTime.plusHours(1);
                endTime = startTime.plusHours(1);
            }
        }
        return availableTimeRanges;
    }
    //比较时分
    public static Integer compareHourAndMinutes(String time1,String time2) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);

            if (date1.before(date2)) {
                return -1;

            } else if (date1.after(date2)) {
                return 1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

    }

    //获得完整日期
    public static String getFullDate(String timeInput ) {
        LocalTime time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 将时间和日期合并为 LocalDateTime 对象
        LocalDateTime dateTime = LocalDateTime.of(currentDate, time);

        // 创建日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 格式化日期时间
        String formattedDateTime = dateTime.format(formatter);

        return formattedDateTime;
    }



}
