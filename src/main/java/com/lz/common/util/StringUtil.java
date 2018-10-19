package com.lz.common.util;

public class StringUtil {
	//double格式的String
	public static int StringToInt(String str){
		String[] strs = str.split("\\.");
		return Integer.parseInt(strs[0]);
	}
}

