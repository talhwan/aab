package com.thc.sprboot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NowDate {
	public String getNow(){
		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//현재시간
		return simpleDateFormat.format(nowDate);
	}

	public String getDue(int second){
		String nowDateString = "";

		Date nowDate = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		//시간 더하기
		cal.add(Calendar.SECOND, second);
		nowDateString = simpleDateFormat.format(cal.getTime());

		return nowDateString;
	}

	public int getDiff(String d2, String d1) throws ParseException {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 문자열 -> Date
		Date date1 = null;
		if(d1 == null){
			date1 = new Date();
		} else {
			date1 = simpleDateFormat.parse(d1);
		}
		Date date2 = simpleDateFormat.parse(d2);

		// Date -> 밀리세컨즈
		long timeMil1 = date1.getTime();
		long timeMil2 = date2.getTime();

		// 비교
		long diff = timeMil2 - timeMil1;
		long diffSec = diff / 1000;

		return (int)diffSec;
	}
}