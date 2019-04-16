package com.tapi.tcs.vc.webservice.provider.docDoReprinted.component.impl;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.component.DocDoReprintedService;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.service.DocReprintService;

@WebService(targetNamespace = "http://service.tapi.com/vc/docDoReprinted/intf", serviceName = "DocDoReprintedService",
		endpointInterface = "com.tapi.tcs.vc.webservice.provider.docDoReprinted.component.DocDoReprintedService")
public class DocDoReprintedServiceImpl implements DocDoReprintedService {

	private DocReprintService docReprintService;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public DocReprintService getDocReprintService() {
		return docReprintService;
	}

	public void setDocReprintService(DocReprintService docReprintService) {
		this.docReprintService = docReprintService;
	}
	
	/**
	 * 单证重打
	 * @param request
	 * @return
	 */
	@Override
	public DocDoReprintedResponse docDoReprinted(DocDoReprintedRequest request) {
	
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoReprintedResponseDTO responseBody = new DocDoReprintedResponseDTO();
		DocDoReprintedResponse response = new DocDoReprintedResponse();
		
		String[] message = checkParams(request);
		if(!"0".equals(message[0])) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(message[1]);
			responseBody.setResultCode("3"); // 输入参数为空
			responseBody.setResultMessage("输入参数为空");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		try {
			response = docReprintService.executeDocRepirnt(request);
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(be.getMessage());
			responseBody.setResultCode("9");
			responseBody.setResultMessage(be.getMessage());
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			responseBody.setResultCode("9");
			responseBody.setResultMessage("内部错误，重打失败");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		return response;
	}

	/*
	 * 对请求对象进行非空验证
	 */
	private String[] checkParams(DocDoReprintedRequest request) {
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
		if (StringUtils.isEmpty(request.getRequestHead().getSystemCode())) {
			message[0] = "10";
			message[1] = "系统代码不能为空！";
			return message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "10";
			message[1] = "请求体不能为空！";
			return message;
		}

		StringBuffer errors = new StringBuffer();
		DocDoReprintedRequestDTO requestBody = request.getRequestBody();
		if (StringUtils.isBlank(requestBody.getOldDocVerCode())) {
			errors.append("旧单证类型代码不能为空，");
		}
		if (StringUtils.isBlank(requestBody.getOldDocNum())) {
			errors.append("旧单证流水号不能为空，");
		}
		if (StringUtils.isBlank(requestBody.getNewDocVerCode())) {
			errors.append("新单证类型代码不能为空，");
		}
		if (StringUtils.isBlank(requestBody.getNewDocNum())) {
			errors.append("新单证流水号不能为空，");
		}
		if (StringUtils.isBlank(requestBody.getBusinessNo())) {
			errors.append("业务号不能为空，");
		}
		if (StringUtils.isBlank(requestBody.getOrgCode())) {
			errors.append("机构代码不能为空。 ");
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
