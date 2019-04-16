package com.tapi.tcs.vc.webservice.provider.docDoUsed.component;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequestDTO;

public class ValidCheck{
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public static  String[] checkNumStatus(DocDoUsedRequest request){
		 String[] message=new String[2];
		 if(request==null){
			 message[0]="10";
			 message[1]="服务请求不能为空!";
			 return  message;
		 }
		 if(request.getRequestHead()==null){
			 message[0]="10";
			 message[1]="请求头不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getRequestHead().getSystemCode())){
			 message[0]="10";
			 message[1]="系统代码不能为空!";
			 return  message;
		 }
		 if(request.getRequestBody()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 
		 RequestHeadDTO requestHead = request.getRequestHead();
		 DocDoUsedRequestDTO  body=request.getRequestBody(); 
		 StringBuilder sb=new StringBuilder("");

		 //  body.getPressBatchNo() 发票核销时、不能为空
		 if(StringUtils.isEmpty(body.getDocVerCode())){
		     sb.append("单证类型代码不能为空！");
		 }
		 
		 if (SysConst.EXTERNAL_SYSTEM_CODE.indexOf(requestHead.getSystemCode())<0){
			 //非外部接口调用，操作员代码校验非空
			 if(StringUtils.isEmpty(body.getOperatorCode())){
				 sb.append("操作员代码不能为空！");
			 }
		 }
		 if(StringUtils.isEmpty(body.getOrgCode())){
		     sb.append("操作员归属机构代码不能为空！");
		 }
		 if(StringUtils.isEmpty(body.getDocTypeCode())){
		     sb.append("单证种类代码不能为空！");
		 }
		
		 // 单证流水列表
		 List<DoUsedDocNumInfoDTO> docNumInfoDTOs = body.getDocNumInfoDTOs();
		 if(docNumInfoDTOs.size()== 0 || docNumInfoDTOs==null ){
		     sb.append("单证流水号列表！");
		 }
		 
		 for (DoUsedDocNumInfoDTO doUsedDocNumInfoDTO : docNumInfoDTOs) {
				if (StringUtils.isEmpty(doUsedDocNumInfoDTO.getDocNum())) {
					sb.append("单证流水号不能为空,");
					break;
				}
				
				List<DoUsedBusinessNoDTO>  businessNoDTOs=doUsedDocNumInfoDTO.getBusinessNoDTOs();
				if (businessNoDTOs.size()==0 ||  businessNoDTOs==null) {
					sb.append("业务号列表不能为空,"); 
					break;
				}
				for(DoUsedBusinessNoDTO  businessNoDTO:businessNoDTOs){
					if (StringUtils.isEmpty(businessNoDTO.getBusinessNo())) {
						sb.append("业务号不能为空,");
						break;
					}
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
