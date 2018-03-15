package com.example.ghuser.weatherapp.common;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ghuser on 1/24/2018.
 */

public class Common {
    public static String API_KEY = "f6bac2feebbe63936215abb396b9bfd4";
    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";
    public static String API_LINK2 = "http://api.openweathermap.org/data/2.5/forecast";

    @NonNull
    public static String apiRequest(String city_name){
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?q=%s&APPID=%s",city_name,API_KEY));
        return sb.toString();
    }

    @NonNull
    public static String apiRequest2(String city_name,String country_code){
        StringBuilder sb = new StringBuilder(API_LINK2);
        sb.append(String.format("?q=%s,%s&APPID=%s",city_name,country_code,API_KEY));
        return sb.toString();
    }

    public static String unixTimeStampToDateTime(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(String icon){
        return String.format("http://openweathermap.org/img/w/%s.png",icon);
    }

    public static String getDatenow(){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM YYYY HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
