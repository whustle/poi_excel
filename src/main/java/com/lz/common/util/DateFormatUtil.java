package com.lz.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年-MM月-dd日");
	public static String formatDate(Date date){
		String d = sdf.format(date);
		return d;
	}
}
