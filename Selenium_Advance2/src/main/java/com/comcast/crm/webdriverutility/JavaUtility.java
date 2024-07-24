package com.comcast.crm.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber()
	{
		Random random=new Random();
		int randomNum = random.nextInt(5000);
		return randomNum;
	}
	
	public String getSystemDateYYYYMMDD()
	{
		Date dateobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateobj);
		return date;
	}
	public String getRequiredDate(int days)
	{	
		Date dateobj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dateobj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,days);
		String endDate = sim.format(cal.getTime());
		return endDate;
	}
	public String getCurrentTime() {
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		return time;
		
	}
}
