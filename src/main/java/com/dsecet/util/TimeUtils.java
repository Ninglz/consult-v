package com.dsecet.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author: Fablenas
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtils{

    public static final String DATA_TIME_FROMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FROMAT = "yyyy-MM-dd";

    public static final long currentMillis(){
        return Clock.systemUTC().millis();
    }

    public static final long plusMonths(long instant, long months){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusMonths(months).toInstant().toEpochMilli();
    }

    public static final long plusDays(long instant, long days){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusDays(days).toInstant().toEpochMilli();
    }

    public static final long plusSeconds(long instant, long seconds){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusSeconds(seconds).toInstant().toEpochMilli();
    }

    public static final String plusCurrentSeconds(long seconds){
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).plusSeconds(seconds).toLocalDateTime().format(DateTimeFormatter.ofPattern(DATA_TIME_FROMAT));
    }

    public static final long plusMinutes(long instant, long minutes){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).plusMinutes(minutes).toInstant().toEpochMilli();
    }

    public static final long minusDays(long instant, long days){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).minusDays(days).toInstant().toEpochMilli();
    }

    public static final LocalDate millisToDate(long millis){
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static final String millisToDateStr(Long millis){
        if(millis == null)
            return "";
        return Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern(DATA_FROMAT));
    }


    public static final long countDays(long deltaMillis) {
        return Duration.ofMillis(deltaMillis).toDays();
    }

    public static final long diffDays(long before, long end) {
        return countDays(end - before);
    }

    public static final int getDayOfMonth(long instant) {
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).getDayOfMonth();
    }

   public static final String currentDate(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern(DATA_FROMAT));
    }

    public static final String currentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATA_TIME_FROMAT));
    }

    public static final long startOfDayOfMilli(Long millis) {
        return LocalDateTime
                .of(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MIDNIGHT)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static final String millisToDateTime(Long millis, String pattern){
        LocalDateTime time = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static final String millisToDate(Long millis, String pattern){
        if(0L == millis){return "";}
        LocalDate date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static final long endOfDayOfMilli(Long millis) {
        return LocalDateTime
                .of(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate(), LocalTime.MAX)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static final Long parseDateToMilli(String strDate, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try{
            return sdf.parse(strDate).toInstant().toEpochMilli();
        }catch(ParseException e){
            // todo fix
            return null;
        }
    }
    
    public static String getDateToString(Long time) {
    	Date d = new Date(time);
    	SimpleDateFormat sf = new SimpleDateFormat(DATA_TIME_FROMAT);
    	return sf.format(d);
    }
    
    public static final long minusMinutes(long instant, long minutes){
        return Instant.ofEpochMilli(instant).atZone(ZoneId.systemDefault()).minusMinutes(minutes).toInstant().toEpochMilli();
    }
    
    public static Integer getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        Locale.setDefault(Locale.CHINA);
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w == 0){
        	w = 7;
        }
        return w;
    }
    public static Integer getHourOfDate(Date dt) {
    	Calendar cal = Calendar.getInstance();
    	Locale.setDefault(Locale.CHINA);
    	cal.setTime(dt);
    	int i = cal.get(Calendar.HOUR_OF_DAY);
    	return i;
    }
}
