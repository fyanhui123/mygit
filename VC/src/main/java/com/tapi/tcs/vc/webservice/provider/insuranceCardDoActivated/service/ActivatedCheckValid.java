package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.service;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequestDTO;

public class ActivatedCheckValid {

	/**
	 *    校验入参
	 * 
	 * @param request
	 * @return
	 */
	public static String[] validParams(InsuranceCardDoActivatedRequest request) {
		String[] message = new String[2];
		if (request == null) {
			message[0] = "10";
			message[1] = "服务请求不能为空！";
			return message;
		}
		if (request.getRequestHead() == null) {
			message[0] = "10";
			message[1] = "请求头不能为空！";
			return message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "10";
			message[1] = "请求体不能为空！";
			return message;
		}

		InsuranceCardDoActivatedRequestDTO requsetBody = request.getRequestBody();

		StringBuffer errors = new StringBuffer();
		
		 Date date=requsetBody.getActiveTime();
		 
		 if(StringUtils.isBlank(requsetBody.getDocVerCode())){ 
			 errors.append("单证类型代码不能为空,");
		 }
		 
		 if(StringUtils.isBlank(requsetBody.getCardNO())){
			 errors.append("卡号不能为空,");
		 }
		 if(StringUtils.isBlank(requsetBody.getBusinessNo())){
			 errors.append("业务号不能为空,");
		 }
		
		 if(StringUtils.isBlank(requsetBody.getActiveType())){
			 errors.append("激活方式不能为空,");
		 }
		 
		 if(date == null){
			 errors.append("激活时间不能为空,");
		 }
		 
			if (errors.length() > 0) {
				message[0] = "10";
				message[1] = errors.substring(0, errors.length() - 1);
				return message;
			}
			message[0] = "0";
			return message;
	}
}
