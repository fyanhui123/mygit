package com.tapi.tcs.vc.webservice.provider.docValidInquiry.service.impl;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.vc.visausage.service.DocValidQueryService;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.service.facade.DocValidInquiryService;

public class DocValidInquiryServiceImpl implements DocValidInquiryService {

	
	private  DocValidQueryService docValidQueryService;
	
	@Override
	public DocValidInquiryResponse docValidInquiry(DocValidInquiryRequest request) {
		DocValidInquiryResponse response = new DocValidInquiryResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocValidInquiryResponseDTO responseBody = new DocValidInquiryResponseDTO();
		
		String[] message = checkParams(request);
		if(!"0".equals(message[0])){
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(message[1]);
			responseBody.setStatus("5");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		try {
			response = docValidQueryService.docValidInquiry(request.getRequestBody());
		} catch (Exception e) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			responseBody.setStatus("5");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}
		return response;
	}
	
	/**
	 * 检查参数
	 * @param request
	 * @return
	 */
	private String[] checkParams(DocValidInquiryRequest request){
		String[] message = new String[2];
		if(request==null){
			message[0] = "10";
			message[1] = "服务请求不能为空！";
			return message;
		}
		if(request.getRequestHead()==null){
			message[0] = "10";
			message[1] = "请求头不能为空！";
			return message;
		}
		if(request.getRequestBody()==null){
			message[0] = "10";
			message[1] = "请求体不能为空！";
			return message;
		}
		
		DocValidInquiryRequestDTO body = request.getRequestBody();
		StringBuilder errors = new StringBuilder();
		/*适用场景：
		单证打印核销：5个输入条件必需输入
		无线出单：单证版本号、业务号为空，其它条件必需输入
		单证正本打印销号：5个输入条件必需输入*/
		
		String sceneFlag = body.getSceneFlag();
		String docVerCode = body.getDocVerCode();
		String businessNo = body.getBusinessNo();
		String docBeginSerialNo = body.getDocBeginSerialNo();
		String docEndSerialNo = body.getDocEndSerialNo();
		String operator = body.getOperator();
		String comCode = body.getComCode();

		if(StringUtils.isEmpty(sceneFlag)){
			message[0] = "10";
			message[1] = "适用场景标记不能为空！";
			return message;
		}else if("0".equals(sceneFlag)||"2".equals(sceneFlag)){
			// 用什么符号分隔
			if(StringUtils.isEmpty(docVerCode)){
				errors.append("单证类型代码不能为空,");
			}
			if(StringUtils.isEmpty(businessNo)){
				errors.append("业务号不能为空,");
			}
			if(StringUtils.isEmpty(docBeginSerialNo)){
				errors.append("单证起始流水号不能为空,");
			}
			if(StringUtils.isEmpty(docEndSerialNo)){
				errors.append("单证终止流水号不能为空,");
			}
			if(StringUtils.isEmpty(operator)){
				errors.append("操作员不能为空,");
			}
			if(StringUtils.isEmpty(comCode)){
				errors.append("归属机构不能为空,");
			}
		}else if("1".equals(sceneFlag)){
			if(StringUtils.isEmpty(docBeginSerialNo)){
				errors.append("单证起始流水号不能为空,");
			}
			if(StringUtils.isEmpty(docEndSerialNo)){
				errors.append("单证终止流水号不能为空,");
			}
			if(StringUtils.isEmpty(operator)){
				errors.append("操作员不能为空,");
			}
			if(StringUtils.isEmpty(comCode)){
				errors.append("归属机构不能为空,");
			}
		}else{
			message[0] = "10";
			message[1] = "适用场景标记错误！";
			return message;
		}
		
		//流水号长度校验
		
		if(StringUtils.isNotEmpty(errors.toString())){
			message[0] = "10";
			message[1] = errors.substring(0, errors.length()-1);
			return message;
		}
		message[0]="0";
		return message;
	}

	public void setDocValidQueryService(DocValidQueryService docValidQueryService) {
		this.docValidQueryService = docValidQueryService;
	}
}
