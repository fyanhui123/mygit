package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.component;

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
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.service.DocDoAnnuledQueryService;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResponseDTO;

@WebService(targetNamespace="http://service.tapi.com/vc/docDoAnnuled/intf", serviceName="DocDoAnnuledService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docDoAnnuled.component.DocDoAnnuledService")
public class DocDoAnnuledServiceImpl implements DocDoAnnuledService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 记录报文service
	 */
	private VcReportService vcReportService;
	
	private  DocDoAnnuledQueryService docDoAnnuledQueryService;
	/**
	 *  单证作废接口       新核心系统
	 * @param request
	 * @return
	 */ 
	public DocDoAnnuledResponse saveDocDoAnnul(DocDoAnnuledRequest request) throws BusinessException{
		DocDoAnnuledResponse response = new DocDoAnnuledResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoAnnuledResponseDTO responseBody = new DocDoAnnuledResponseDTO();

		try {
			// 校验入参
			String[] messages = CheckDocDoAnnuledValid.checkParams(request); 
			if (!"0".equals(messages[0])) {  
				responseHead.setResponseCode("999"); 
				responseHead.setResponseMessage(messages[1]); 
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				throw new BusinessException(messages[1]);
			}
			response = docDoAnnuledQueryService.executeDocDoAnnuled(request.getRequestBody());
		} catch (BusinessException e) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage()); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		} 
		
		//----写入交互报文 xshibai----
        VcReportRecord record = null; 
    	try {
    		responseHead = response.getResponseHead();
    		if ( StringUtils.isNotBlank(responseHead.getResponseCode()) && !responseHead.getResponseCode().equals("000")) {
    			DocDoAnnuledRequestDTO requestBody = request.getRequestBody();
        		String requestXml = ReportUtils.DTOtoXML(requestBody, DocDoAnnuledRequestDTO.class.getName());
        		String docNum = "";
        		String docVerCode="";
        		if (requestBody.getDocNumInfoDTOs() != null && requestBody.getDocNumInfoDTOs().size()  <= 1) {
        			docNum = requestBody.getDocNumInfoDTOs().get(0).getDocNum();
        			docVerCode = requestBody.getDocVerCode();
    			}
        		record = ReportUtils.generateReport( "1", "D2", ReportUtils.substring(requestXml, 3999),docNum,docVerCode);
        		String responseXml = ReportUtils.DTOtoXML(response, DocDoAnnuledResponse.class.getName());
        		record.setId(record.getId());
            	record.setErrorCode(responseHead.getResponseCode());
            	record.setReportResult(responseHead.getResponseCode().equals("000") ? "1" : "0" );				
            	record.setErrorDesc(responseHead.getResponseCode().equals("000") ? "" : responseHead.getResponseMessage());
            	record.setResponseXml(responseXml);
            	record.setResponseTime(new Date());
    			vcReportService.nestSaveReportNest(record);
    		}
		} catch (Exception e) {
			logger.info("单证作废报文新增失败：" + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

	public void setDocDoAnnuledQueryService(
			DocDoAnnuledQueryService docDoAnnuledQueryService) {
		this.docDoAnnuledQueryService = docDoAnnuledQueryService;
	}

	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}

	
}
