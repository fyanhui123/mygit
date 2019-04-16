package com.tapi.tcs.vc.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class URLUtils {

	public static String getUrlResourceStr(String url) {
		BufferedReader br = null;
		try {
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static JSONObject getUrlResourceObj(String url) {
		String aa=getUrlResourceStr(url);
		return JSONObject.parseObject(aa);
	}
}
