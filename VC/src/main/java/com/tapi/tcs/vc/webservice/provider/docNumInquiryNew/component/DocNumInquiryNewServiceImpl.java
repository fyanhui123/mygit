package com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.component;

import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewResponse;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.DocNumInquiryNewResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.SingleLibEntity;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.service.QueryAvailableNumNewService;

@WebService(targetNamespace = "http://service.tapi.com/vc/docNumInquiryNew/intf", serviceName = "DocNumInquiryNewService", endpointInterface = "com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.component.DocNumInquiryNewService")
public class DocNumInquiryNewServiceImpl implements DocNumInquiryNewService {
	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private QueryAvailableNumNewService queryAvailableNumNewService;

	@Override
	public DocNumInquiryNewResponse docNumInquiryNew(DocNumInquiryNewRequest request) {
		DocNumInquiryNewResponse response = new DocNumInquiryNewResponse();
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
			DocNumInquiryNewRequestDTO requestBody = request.getRequestBody();
			DocNumInquiryNewResponseDTO responseBody = queryAvailableNumNewService
					.availableNumInquiryNew(requestBody);
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
	private String[] checkParam(DocNumInquiryNewRequest request) {
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
		DocNumInquiryNewRequestDTO body = request.getRequestBody();
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
		
		Date lastSynTime = singleLibEntity.getLastSynTime();
		//使用人不为空则不校验上次同步时间
		if(StringUtils.isEmpty(singleLibEntity.getOpAndProxyId())){
			if (null == lastSynTime) {
				message[0] = "9";
				message[1] = "查询条件中上次同步时间不能为空！";
				return message;
			}
		}		

		//单证类型必传
		if (StringUtils.isEmpty(singleLibEntity.getDocVerCode())) {
			message[0] = "9";
			message[1] = "查询条件中单证类型不能为空！";
			return message;
		}
		
		message[0] = "0";
		message[1] = "成功";
		return message;
	}

	public void setQueryAvailableNumNewService(
			QueryAvailableNumNewService queryAvailableNumNewService) {
		this.queryAvailableNumNewService = queryAvailableNumNewService;
	}
}
