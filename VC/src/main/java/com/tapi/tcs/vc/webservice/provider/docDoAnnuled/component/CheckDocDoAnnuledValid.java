package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.component;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DoAnnuledDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequestDTO;

public class CheckDocDoAnnuledValid {
	/**
	 * 检查入参
	 * 
	 * @param request
	 * @return
	 */
	public   static  String[] checkParams(DocDoAnnuledRequest request) {
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
		if(StringUtils.isEmpty(request.getRequestHead().getSystemCode())){
			message[0]="10";
			message[1]="系统代码不能为空!";
			return  message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "10";
			message[1] = "请求体不能为空！";
			return message;
		}
		RequestHeadDTO requestHead = request.getRequestHead();
		DocDoAnnuledRequestDTO requsetBody = request.getRequestBody();
		StringBuffer errors = new StringBuffer();

		 // 单证流水列表
		 List<DoAnnuledDocNumInfoDTO> docNumInfoDTOs = requsetBody.getDocNumInfoDTOs();
		 //单证类型代码
		 String   docVerCode=requsetBody.getDocVerCode();
		 //操作员代码
		 String   operatorCode=requsetBody.getOperatorCode();
		 //操作员归属机构代码
		 String   orgCode=requsetBody.getOrgCode();
		 //印刷批次(非必填)
	     //String   pressBatchNo=requsetBody.getPressBatchNo();

		 if(StringUtils.isEmpty(docVerCode)){ 
			 errors.append("单证类型代码不能为空,");
		 }
		 if (SysConst.EXTERNAL_SYSTEM_CODE.indexOf(requestHead.getSystemCode())<0){
			 //非外部接口调用，操作员代码校验非空
			 if(StringUtils.isEmpty(operatorCode)){
				 errors.append("操作员不能为空,");
			 }
		 }
		 if (StringUtils.isEmpty(orgCode)) {
			errors.append("归属机构不能为空,");
		 }
		 if (docNumInfoDTOs == null || docNumInfoDTOs.size() == 0) {
				errors.append("单证流水列表不能为空,");
		 }

		for (DoAnnuledDocNumInfoDTO doAnnuledDocNumInfoDTO : docNumInfoDTOs) {
			if (StringUtils.isEmpty(doAnnuledDocNumInfoDTO.getDocNum())) {
				errors.append("单证流水号不能为空,");
				break;
			}
			
			List<BusinessNoDTO>  businessNoDTOs=doAnnuledDocNumInfoDTO.getBusinessNoDTOs();
			if (businessNoDTOs==null || businessNoDTOs.size()==0) {
				errors.append("业务号列表不能为空,");
				break;
			}
			for(BusinessNoDTO  businessNoDTO:businessNoDTOs){
				if (StringUtils.isEmpty(businessNoDTO.getBusinessNo())) {
					errors.append("业务号不能为空,");
					break;
				}
			}
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
