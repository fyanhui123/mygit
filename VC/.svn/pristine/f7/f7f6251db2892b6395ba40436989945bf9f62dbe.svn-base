package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocSerialNoDTO;

public class CheckDocNumStatus {

	public static String[] check(DocNumStatusInquiryOldRequest request) {
		String[] message = new String[2];
		if (request == null) {
			message[0] = "5";
			message[1] = "服务请求不能为空!";
			return message;
		}
		if (request.getRequestHead() == null) {
			message[0] = "5";
			message[1] = "请求头不能为空!";
			return message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "5";
			message[1] = "请求体不能为空!";
			return message;
		}

		DocNumStatusInquiryOldRequestDTO requestBody = request.getRequestBody();
		StringBuilder sb = new StringBuilder("");
		
		// 单证类型ID
		if (requestBody.getDocVerID() == null || requestBody.getDocVerID() <= 0l) {
			sb.append("单证类型代码不能为空！");
		}
		// 操作员ID
		if (requestBody.getOperatorID() == null || requestBody.getOperatorID() <= 0l) {
			sb.append("操作员代码不能为空！");
		}
		// 发票代码
		if(StringUtils.isBlank(requestBody.getInvoiceVersion())){
			sb.append("发票代码不能为空！");
		}
		// 操作员归属机构ID
		/*if (StringUtils.isEmpty(requestBody.getOrgID())) {
			sb.append("操作员归属机构不能为空！");
		}*/
		if(requestBody.getDepID()==null || requestBody.getDepID()<=0){
			sb.append("部门ID不能为空！");
		}
		if(StringUtils.isBlank(requestBody.getDepCode())){
			sb.append("部门代码不能为空！");
		}
		// 单证起始流水号
		/*if (StringUtils.isEmpty(requestBody.getStartNum())) {
			sb.append("单证起始流水号不能为空！");
		}*/
		// 单证终止流水号
		/*if (StringUtils.isEmpty(requestBody.getEndNum())) {
			sb.append("单证终止流水号不能为空！");
		}*/
		List<DocSerialNoDTO> docSerialNoDtos = requestBody.getDocSerialNoDTOs();
		if(docSerialNoDtos==null || docSerialNoDtos.size()==0){
			sb.append("流水号区间段不能为空！");
		}else{
			for(DocSerialNoDTO docSerialNoDTO : docSerialNoDtos){
				if(StringUtils.isEmpty(docSerialNoDTO.getDocNum())){
					sb.append("流水号不能为空！");
					break;
				}
			}
		}
		if(StringUtils.isNotEmpty(sb.toString())){
			message[0] = "5";
			message[1] = sb.toString();
			return message;
		}else{
			message[0] = "0";
		}
		return message;
	}
}
