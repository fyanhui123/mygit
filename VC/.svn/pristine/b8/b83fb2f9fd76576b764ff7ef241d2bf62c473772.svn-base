package com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.component;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.bean.InvoiceUploadPlatInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.service.InvoiceIsToPlatQueryService;

@WebService(targetNamespace="http://service.tapi.com/vc/invoiceUploadPlatInquiry/intf", serviceName="InvoiceUploadPlatInquiryService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.invoiceUploadPlatInquiry.component.InvoiceUploadPlatInquiryService")
public class InvoiceUploadPlatInquiryServiceImpl implements
		InvoiceUploadPlatInquiryService {
	
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private InvoiceIsToPlatQueryService invoiceIsToPlatQueryService;
	
	@Override
	public InvoiceUploadPlatInquiryResponse queryInvoiceIsUploadPlat(InvoiceUploadPlatInquiryRequest request){
		InvoiceUploadPlatInquiryResponse response = new InvoiceUploadPlatInquiryResponse();
		ResponseHeadDTO responseHead = null;
		
		String errMsg = this.checkParam(request);
		if(StringUtils.isNotEmpty(errMsg)){
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(errMsg);
			response.setResponseHead(responseHead);
			return response;
		}
		
		try{
			response = invoiceIsToPlatQueryService.queryInvoiceStatus(request.getRequestBody());
		}catch(BusinessException e){
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统内部错误！");
			response.setResponseHead(responseHead);
		}
		return response;
	}
	
	private String checkParam(InvoiceUploadPlatInquiryRequest request){
		StringBuffer errMsg = new StringBuffer();
		if(request.getRequestHead()==null){
			errMsg.append("请求头不能为空；");
		}
		if(request.getRequestBody()==null){
			errMsg.append("请求体不能为空；");
		}else{
			InvoiceUploadPlatInquiryRequestDTO requestBody = request.getRequestBody();
			//if(requestBody.getDepartId()==null){
			//	errMsg.append("部门ID不能为空；");
			//}
			if(StringUtils.isBlank(requestBody.getDepartCode())){
				errMsg.append("部门代码不能为空；");
			}
			if(StringUtils.isBlank(requestBody.getInvoiceCode())){
				errMsg.append("发票代码不能为空；");
			}
			if(StringUtils.isBlank(requestBody.getInvoiceNo())){
				errMsg.append("发票号码不能为空；");
			}
		}
		return errMsg.toString();
	}

	public void setInvoiceIsToPlatQueryService(
			InvoiceIsToPlatQueryService invoiceIsToPlatQueryService) {
		this.invoiceIsToPlatQueryService = invoiceIsToPlatQueryService;
	}
}
