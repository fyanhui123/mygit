package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.component;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.BillDTO;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.NewInvoiceDocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.NewInvoiceDocDoAnnuledRequestBody;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.PolicyDTO;

public class ValidCheck{
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public static  String[] checkNumStatus(NewInvoiceDocDoAnnuledRequest request){
		 String[] message=new String[2];
		 if(request==null){
			 message[0]="10";
			 message[1]="服务请求不能为空!";
			 return  message;
		 }
		 if(request.getHeadDTO()==null){
			 message[0]="10";
			 message[1]="请求头不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getrEQSERIALNO())){
			 message[0]="10";
			 message[1]="交易流水号不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getrEQUESTTYPE())){
			 message[0]="10";
			 message[1]="请求类型不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getHeadDTO().getfLOWINTIME())){
			 message[0]="10";
			 message[1]="请求时间不能为空!";
			 return  message;
		 }
		 if(request.getBody()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 
		 NewInvoiceDocDoAnnuledRequestBody  body=request.getBody(); 
		 StringBuilder sb=new StringBuilder("");
		 if(StringUtils.isEmpty(body.getDocVerCode())){
			 sb.append("发票类型代码不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getPressBatchNo())){
			 sb.append("发票代码不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getDocNum())){
			 sb.append("发票号不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getOrgcode())){
			 sb.append("核销机构不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getTakeCode())){
			 sb.append("核销操作人代码不能为空！"); 
		 }
		 if("1".equals(body.getCounteractFlag())){
			 if(StringUtils.isEmpty(body.getCounteractedInvoiceCode())){
				 sb.append("被冲红的发票代码不能为空!"); 
			 }
			 if(StringUtils.isEmpty(body.getCounteractedInvoiceNo())){
				 sb.append("冲红的发票号码不能为空!"); 
			 }
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}
	
}
