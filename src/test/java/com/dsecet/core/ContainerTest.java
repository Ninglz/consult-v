package com.dsecet.core;

import com.dsecet.ApplicationConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * User: liuchang
 * Date: 14/12/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TransactionConfiguration
public class ContainerTest {

    @Test
    public void runContainerTest() {
        Assert.assertTrue(true);
    }
    
    public static void main(String[] args) {
//    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 
//		 java.util.Date timeFormDate;
//		try {
//			timeFormDate = sd.parse("2016-3-5 15:23:00");
//			System.out.println(timeFormDate.getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//    	long time = new Date().getTime();
//    	SimpleDateFormat sd = new SimpleDateFormat("E");
//    	System.out.println(sd.format(time));
//    	Calendar calendar = Calendar.getInstance();
//    	int i = calendar.get(Calendar.DAY_OF_WEEK);
//    	System.out.println(i);
    	Integer weekOfDate = getHourOfDate(new Date());
    	System.out.println(weekOfDate);
    	
	}
    
    public static String getDateToString(Long time) {
    	Date d = new Date(time);
    	SimpleDateFormat sf = new SimpleDateFormat("EEEE HH");
    	return sf.format(d);
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
