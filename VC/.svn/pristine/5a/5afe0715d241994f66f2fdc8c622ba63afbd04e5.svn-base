package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.component;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.NewInvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.NewInvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.PolicyDTO;
public class ValidCheck{
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public static  String[] checkNumStatus(NewInvoiceDoUsedRequest request){
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
		 if(request.getRequestBody()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 
		 NewInvoiceDoUsedRequestDTO  body=request.getRequestBody(); 
		 StringBuilder sb=new StringBuilder("");
		 if(StringUtils.isEmpty(body.getDocVerCode())){
			 sb.append("发票类型代码不能为空!"); 
		 }
		 if(StringUtils.isEmpty(body.getPressBatchNo())){
			 sb.append("发票代码不能为空!"); 
		 }
		 if(StringUtils.isEmpty(body.getDocNum())&&body.getDocNum()!=null){
			 sb.append("发票号不能为空!"); 
		 }
		 if(StringUtils.isEmpty(body.getOrgCode())){
			 sb.append("核销机构代码不能为空!"); 
		 }
		 if(StringUtils.isEmpty(body.getVerifiedOprCode())){
			 sb.append("核销操作人代码不能为空!"); 
		 }
		 if("1".equals(body.getCounteractFlag())){
			 if(StringUtils.isEmpty(body.getCounteractedInvoiceCode())){
				 sb.append("被冲红的发票代码不能为空!"); 
			 }
			 if(StringUtils.isEmpty(body.getCounteractedInvoiceNo())){
				 sb.append("冲红的发票号码不能为空!"); 
			 }
		 }
		 List<PolicyDTO> list=body.getPolicyDTO();
		 if(list.size()<=0){
			 sb.append("保单信息不能为空!");  
		 }else{
		 for(int i=0;i<list.size();i++){
			 PolicyDTO policy=list.get(i);
			 if(StringUtils.isEmpty(policy.getPolicyNo())){
				 sb.append("第"+i+1+"个保单号不能为空"); 
			 }
			 if(StringUtils.isEmpty(policy.getPayNo())){
				 sb.append("第"+i+1+"个缴费期次不能为空"); 
			 }
			 if(StringUtils.isEmpty(policy.getEndorNo())){
				 sb.append("第"+i+1+"个批改序号不能为空"); 
			 }
		 } }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}
	
}
