package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.component;


import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service.VcDocVerInfoService;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service.impl.DocVerInquiryCheck;

@WebService(targetNamespace="http://service.tapi.com/vc/docVerInquiryOld/intf", serviceName="DocVerInquiryOldService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.component.DocVerInquiryOldService")
public class DocVerInquiryOldServiceImpl implements DocVerInquiryOldService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcDocVerInfoService vcDocVerInfoService;
	
	/**
	 * 单证类型查询接口-入口程序 
	 */
	@Override
	public  DocVerInquiryOldResponse docVerInquiry(
			DocVerInquiryOldRequest request) {
		//响应对象
		DocVerInquiryOldResponse response=new DocVerInquiryOldResponse();
		//响应头
		ResponseHeadDTO responseHead = null;
		try {
			DocVerInquiryCheck docVerInquiryCheck=new DocVerInquiryCheck();
			String[] message = docVerInquiryCheck.checkDocInquiry(request);
			if(!"000".equals(message[0])){
				responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message[1]);
				response.setResponseHead(responseHead);
				return response;
			}
			//查找单证类型，组织响应体
			DocVerInquiryOldResponseDTO responseBody = vcDocVerInfoService.docVerInquiry(request.getRequestBody());
			if(responseBody==null){
				throw new BusinessException("没有符合条件的数据！");
			}
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		} catch(BusinessException be){
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
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			response.setResponseHead(responseHead);
			return response;
		}
		return response;
	}
	
	public void setVcDocVerInfoService(VcDocVerInfoService vcDocVerInfoService) {
		this.vcDocVerInfoService = vcDocVerInfoService;
	}
	
}
