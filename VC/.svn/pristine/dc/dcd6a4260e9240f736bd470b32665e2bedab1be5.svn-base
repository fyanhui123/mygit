package com.tapi.tcs.vc.common.util;

public class StringUtil {

	/**
	 * 判断字符串是否为数值型
	 * @param number
	 * @return
	 */
	public static boolean isNumber(String number){
		number = number.trim();
		String prefix = "^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$";
		return number.matches(prefix);
	}
	
	/**
	 * 格式化号码长度，不够前面加0
	 * @param number
	 * @param len
	 * @return
	 */
	public static String formatNumberLength(String number, int len) {
        if(number == null || number.trim().equals(""))
        	number = "";
        StringBuffer sb;
        for(sb = new StringBuffer(); number.length() + sb.length() < len; sb.append("0"));
        return sb.append(number).toString();
    }
}
