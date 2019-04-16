package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.component;

import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.ReportUtils;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.common.service.VcReportService;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequest;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardDoActivatedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.service.ActivatedCheckValid;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.service.InsuranceActivatedService;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponseDTO;

@WebService(targetNamespace="http://service.tapi.com/vc/insuranceCardDoActivated/intf", serviceName="InsuranceCardDoActivatedService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.component.InsuranceCardDoActivatedService")
public class InsuranceCardDoActivatedServiceImpl implements
		InsuranceCardDoActivatedService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 记录报文service
	 */
	private VcReportService vcReportService;
	
	private  InsuranceActivatedService insuranceActivatedService;
	@Override
	public InsuranceCardDoActivatedResponse insuranceCardDoActivatedService(
			InsuranceCardDoActivatedRequest request){

		InsuranceCardDoActivatedResponse response = new  InsuranceCardDoActivatedResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		InsuranceCardDoActivatedResponseDTO responseBody = new InsuranceCardDoActivatedResponseDTO();

		try {
			// 校验入参
			String[] messages = ActivatedCheckValid.validParams(request);
			if (!"0".equals(messages[0])) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(messages[1]);
				responseBody.setStatus("0");
				response.setResponseHead(responseHead); 
				response.setResponseBody(responseBody);
				throw new BusinessException(messages[1]);
			}
			response =insuranceActivatedService.executeInsuranceActivatedService(request.getRequestBody());
		} catch (BusinessException e) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}catch (Exception e) {
			e.printStackTrace();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统内部错误!");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		} 
		
		//----写入交互报文 xshibai----
        VcReportRecord record = null;       
        responseHead = response.getResponseHead();
    	try {
    		if ( StringUtils.isNotBlank(responseHead.getResponseCode()) && !responseHead.getResponseCode().equals("000")) {
    			InsuranceCardDoActivatedRequestDTO requestBody = request.getRequestBody();
    			String requestXml = ReportUtils.DTOtoXML(requestBody, InsuranceCardDoActivatedRequestDTO.class.getName());
    			String docNum = "";
    			String docVerCode="";
    			if (requestBody != null) {
    				docNum = requestBody.getCardNO();
    				docVerCode = requestBody.getDocVerCode();
    			}
    			record = ReportUtils.generateReport( "1", "C2", ReportUtils.substring(requestXml, 3999),docNum,docVerCode);
    			String responseXml = ReportUtils.DTOtoXML(response, InsuranceCardDoActivatedResponse.class.getName());
            	record.setErrorCode(responseHead.getResponseCode());
            	record.setReportResult(responseHead.getResponseCode().equals("000") ? "1" : "0" );				
            	record.setErrorDesc(responseHead.getResponseCode().equals("000") ? "" : responseHead.getResponseMessage());
            	record.setResponseXml(responseXml);
            	record.setResponseTime(new Date());
    			vcReportService.nestSaveReportNest(record);
    		}
		} catch (Exception e) {
			logger.info("激活卡激活报文新增失败：" + e.getMessage());
			e.printStackTrace();
		}
		return response;

	}
	public void setInsuranceActivatedService(
			InsuranceActivatedService insuranceActivatedService) {
		this.insuranceActivatedService = insuranceActivatedService;
	}
	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}
}
