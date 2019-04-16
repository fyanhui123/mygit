package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.component;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service.CheckDocNumStatus;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service.DocNumStatusQueryService;

@WebService(targetNamespace="http://service.tapi.com/vc/docNumStatusInquiryOld/intf", serviceName="DocNumStatusInquiryOldService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.component.DocNumStatusInquiryOldService")
public class DocNumStatusInquiryOldServiceImpl implements DocNumStatusInquiryOldService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
    private   DocNumStatusQueryService  docNumStatusQueryService;

	/**
	 * 接口 单证流水号状态查询
	 * 
	 * @param request
	 * @return response
	 */
	@Override
	public DocNumStatusInquiryOldResponse queryDocNumStatus(
			DocNumStatusInquiryOldRequest request) {

		List<DocNumStatusInquiryOldResponseDTO> responseBody = new ArrayList<DocNumStatusInquiryOldResponseDTO>();
		// 响应对象
		DocNumStatusInquiryOldResponse response = new DocNumStatusInquiryOldResponse();
		// 响应头
		ResponseHeadDTO responseHead = null;
		try {
			String[] message = CheckDocNumStatus.check(request);
			if (!"0".equals(message[0])) {
				responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message[1]);
				response.setResponseHead(responseHead);
				response.setStatus(message[0]);
				return response;
			}
			// 把查询条件放到requestBody、多表关联查询
			response = docNumStatusQueryService.queryDocNumStatus(request.getRequestBody());
		} catch (BusinessException be) {
			logger.error(be.getMessage(), be);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(be.getMessage());
			response.setResponseHead(responseHead);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
			return response;
		}
		return response;
	}
	
	public void setDocNumStatusQueryService(DocNumStatusQueryService docNumStatusQueryService) {
		this.docNumStatusQueryService = docNumStatusQueryService;
	}

}
