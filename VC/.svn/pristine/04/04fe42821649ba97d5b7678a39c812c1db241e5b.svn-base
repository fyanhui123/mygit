package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.component;
import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequest;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequestBody;
public class ValidCheck{
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public static  String[] checkNumStatus(CancelInvoiceRequest request){
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
		 
		 CancelInvoiceRequestBody  body=request.getBody(); 
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
		 if(StringUtils.isEmpty(body.getAmount())){
			 sb.append("合计金额不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getCancelDate())){
			 sb.append("作废机构不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getCanceTakeCode())){
			 sb.append("作废人代码不能为空！"); 
		 }
		 if(StringUtils.isEmpty(body.getTakeOrgcode())){
			 sb.append("所属机构不能为空！"); 
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
