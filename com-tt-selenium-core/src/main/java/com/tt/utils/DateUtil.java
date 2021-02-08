package com.tt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static String dateFormat = "dd-MMM-yyyy HH:mm:ss";
	public static String getCurrentDate(String format) {
		String output = "";
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		output = sdf.format(d);
		return output;
	}
	
	public static String getCurrentDate()
	{
		return getCurrentDate(dateFormat);
	}
	
	public static String getDate(String incrementType,int factor) {
		String output = "";
		Calendar cal = Calendar.getInstance();
		
		if(incrementType.equalsIgnoreCase("MONTH")) {
			cal.add(Calendar.MONTH, factor);
		}
		else if(incrementType.equalsIgnoreCase("DAY")) {
			cal.add(Calendar.DATE, factor);
		}
		else if(incrementType.equalsIgnoreCase("YEAR")) {
			cal.add(Calendar.YEAR, factor);
		}
		else if(incrementType.equalsIgnoreCase("HOUR")) {
			cal.add(Calendar.HOUR, factor);
		}
		else if(incrementType.equalsIgnoreCase("MINUTE")) {
			cal.add(Calendar.MINUTE, factor);
		}
		else if(incrementType.equalsIgnoreCase("SEC")) {
			cal.add(Calendar.SECOND, factor);
		}
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		output = sdf.format(d);
		
		return output;
	}
	
	public static long timeDiff(String before,String after) {
		long diff = 0;
		try {
			Date d1 = new SimpleDateFormat(dateFormat).parse(before);
			Date d2 = new SimpleDateFormat(dateFormat).parse(after);
			
			long beforeTime = d1.getTime();
			long afterTime = d2.getTime();
			System.out.println("before:"+beforeTime);
			System.out.println("after:"+afterTime);
			
			diff = (afterTime-beforeTime)/1000;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return diff;
	}
	
	public static void main(String args[]) {
		System.out.println(DateUtil.getCurrentDate());
		System.out.println(DateUtil.getDate("MONTH", 2));
		
		//"dd-MMM-yyy:HH:mm:ss"
		System.out.println("Time Difference is: "+DateUtil.timeDiff("30-Jan-2021 10:40:28", "31-Jan-2021 10:40:28"));
	}
}
