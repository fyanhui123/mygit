package com.tapi.tcs.vc.webservice.provider.docNumInquiry.component;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.SingleLibEntity;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.service.QueryAvailableNumService;

@WebService(targetNamespace = "http://service.tapi.com/vc/docNumInquiry/intf", serviceName = "DocNumInquiryService", endpointInterface = "com.tapi.tcs.vc.webservice.provider.docNumInquiry.component.DocNumInquiryService")
public class DocNumInquiryServiceImpl implements DocNumInquiryService {
	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private QueryAvailableNumService queryAvailableNumService;

	@Override
	public DocNumInquiryResponse docNumInquiry(DocNumInquiryRequest request) {
		DocNumInquiryResponse response = new DocNumInquiryResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		try {
			// 校验输入的内容不能为空
			String[] message = this.checkParam(request);
			if (!"0".equals(message[0])) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message[1]);
				response.setResponseHead(responseHead);
				return response;
			}
			DocNumInquiryRequestDTO requestBody = request.getRequestBody();
			DocNumInquiryResponseDTO responseBody = queryAvailableNumService
					.availableNumInquiry(requestBody);
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
			response.setResponseHead(responseHead);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			response.setResponseHead(responseHead);
			return response;
		}
		return response;
	}

	/**
	 * 请求参数校验
	 * 
	 * @param request
	 * @return
	 */
	private String[] checkParam(DocNumInquiryRequest request) {
		String[] message = new String[2];
		if (request == null) {
			message[0] = "9";
			message[1] = "服务请求不能为空!";
			return message;
		}
		if (request.getRequestHead() == null) {
			message[0] = "9";
			message[1] = "请求头不能为空!";
			return message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "9";
			message[1] = "请求体不能为空!";
			return message;
		}
		DocNumInquiryRequestDTO body = request.getRequestBody();
		SingleLibEntity singleLibEntity = body.getSingleLibEntity();
		if (singleLibEntity == null) {
			message[0] = "9";
			message[1] = "查询条件不能为空!";
			return message;
		}
		if (singleLibEntity.getCurrentpage() == null
				|| singleLibEntity.getCurrentpage() == 0) {
			message[0] = "9";
			message[1] = "请传入正确的currentpage";
			return message;
		}
		if (singleLibEntity.getPagesize() == null
				|| singleLibEntity.getPagesize() == 0) {
			message[0] = "9";
			message[1] = "请传入正确的pagesize";
			return message;
		}
		if (StringUtils.isEmpty(singleLibEntity.getLastSynTime().toString())) {
			message[0] = "9";
			message[1] = "查询条件中上次同步时间不能为空！";
			return message;
		}
		message[0] = "0";
		message[1] = "成功";
		return message;
	}

	public void setQueryAvailableNumService(
			QueryAvailableNumService queryAvailableNumService) {
		this.queryAvailableNumService = queryAvailableNumService;
	}
}
