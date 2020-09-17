package com.huntershenep.DCRANKS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static Date todaysDate() {
		Date today = new Date();
		return today;
	}
	
	public static String dateToString(Date date) {
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss"); 
		 String a = formatter.format(date); 
		 return a;
	}
	
	public static String dateToStringNoTime(Date date) {
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); 
		 String a = formatter.format(date); 
		 return a;
	}
	
	public static String dateToSQLDate(Date date) {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		 String a = formatter.format(date); 
		 return a;
	}
	
	public static Date stringToDate(String date) {
		Date aDate = null;
		try {
			aDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aDate;
	}

	public static Date addDaysToDate(Date date, int daysToAdd) {
		date.setDate(date.getDate() + daysToAdd);
		return date;
	}
	
	public static Date addDaysToTODAY(int daysToAdd) {
		Date date = new Date();
		date.setDate(date.getDate() + daysToAdd);
		return date;
	}
	
	public static boolean isExpired(Date futuredate) {
		Date now = new Date();
		
		
		if(now.after(futuredate)) {
			//now bigger than future date
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public static int daysLeft(Date date) {
		int daysLeft = 0;
		Date now = new Date();
		
		if(date.after(now)) {
			//now bigger than future date
			Date newa;
			newa.setDate(date - now);
		}
		
		return daysLeft;
	}
	
}
