package com.nacos.alibaba.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Dateutils {

    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDD_B = "yyyy/MM/dd";

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD);


    public static String format(LocalDateTime date, Optional<String> format){
        String dateStr = null;
        if(format.isPresent()){
            dateStr = DateTimeFormatter.ofPattern(format.get()).format(date);
        } else {
            dateStr = dtf.format(date);
        }
        return dateStr;
    }


    public static void main(String[] args) {
//        String dateStr = format(LocalDateTime.now(),Optional.empty());
        String dateStr = format(LocalDateTime.now(),Optional.of(YYYY_MM_DD_HH_MM_SS));
        System.out.println(dateStr);
    }

}
