package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service.impl;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequestDTO;

public class DocVerInquiryCheck {
	
	public String[] checkDocInquiry(DocVerInquiryOldRequest request){
		String[] message=new String[2];
		if(request==null){
			message[0] = "999";
			message[1] = "服务请求不能为空！";
			return message;
		}
		if(request.getRequestHead()==null){
			message[0] = "999";
			message[1] = "请求头不能为空！";
			return message;
		}
		if(request.getRequestBody()==null){
			message[0] = "999";
			message[1] = "请求体不能为空！";
			return message;
		}
		
		DocVerInquiryOldRequestDTO body=request.getRequestBody();
		StringBuilder sb = new StringBuilder("");
		//险类ID必填效验
		/*if(body.getInsuTypeID() == null 
				|| body.getInsuTypeID() <= 0){
			sb.append("险类ID不能为空！");
		}*/
		//险种ID
		if(body.getInsuKindID() == null
				|| body.getInsuKindID() <= 0){
			sb.append("险种ID不能为空！");
		}
		//当前操作员归属机构ID
		/*if(body.getOrgID() == null
				|| body.getOrgID() <= 0){
			sb.append("当前操作员归属机构ID不能为空！");
		}*/
		//部门id不能为空
		if(body.getDepID()==null || body.getDepID()<=0){
			sb.append("当前操作员归属部门ID不能为空！");
		}
		if(StringUtils.isBlank(body.getDepCode())){
			sb.append("当前操作员归属部门代码不能为空！");
		}
		//单证种类ID列表
		if(body.getDocTypeIDList() == null
				|| body.getDocTypeIDList().size() < 1){
			sb.append("单证种类ID列表不能为空！");
		}
		if(StringUtils.isNotEmpty(sb.toString())){
			message[0] = "999";
			message[1] = sb.toString();
			return message;
		}else{
			message[0] = "000";
		}
		return message;
	}

}
