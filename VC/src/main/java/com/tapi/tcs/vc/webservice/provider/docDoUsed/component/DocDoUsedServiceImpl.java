package com.tapi.tcs.vc.webservice.provider.docDoUsed.component;

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
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.service.DocDoUsedQueryService;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoPaid.bean.InsuranceCardDoPaidResponseDTO;
@WebService(targetNamespace="http://service.tapi.com/vc/docDoUsed/intf", serviceName="DocDoUsedService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docDoUsed.component.DocDoUsedService")
public class DocDoUsedServiceImpl implements DocDoUsedService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 记录报文service
	 */
	private VcReportService vcReportService;


    public VcReportService getVcReportService() {
		return vcReportService;
	}

	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}
	
	
    private    DocDoUsedQueryService  docDoUsedQueryService;
   
	public void setDocDoUsedQueryService(DocDoUsedQueryService docDoUsedQueryService) {
		this.docDoUsedQueryService = docDoUsedQueryService;
	}

	@Override
	public DocDoUsedResponse docDoUsedQuery(DocDoUsedRequest request){
		DocDoUsedResponse response=new DocDoUsedResponse();
		ResponseHeadDTO  responseHead=new ResponseHeadDTO();
		DocDoUsedResponseDTO   responseBody=new DocDoUsedResponseDTO();

		try { 
			// 校验输入的内容不能为空
			String[] message=ValidCheck.checkNumStatus(request);   
			if(!"0".equals(message[0])){
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message[1]); 
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				throw new BusinessException(message[1]);
			}
			response=docDoUsedQueryService.executeDocDoUsedHandle(request);  
	    }
		catch (BusinessException e) {
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage()); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			//return  response;
		}
	    catch (Exception e) {  
	    	logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统内部错误，核销失败！");
			response.setResponseHead(responseHead);
			responseBody.setStatus("9");
			response.setResponseBody(responseBody);
			//return response;
		}

	    //---xshibai 单证核销写入请求报文
		VcReportRecord record = new VcReportRecord();
		try {
			responseHead = response.getResponseHead();
			if ( StringUtils.isNotBlank(responseHead.getResponseCode()) && !responseHead.getResponseCode().equals("000")) {
				DocDoUsedRequestDTO requestBody = request.getRequestBody();
	    		String requestXml = ReportUtils.DTOtoXML(requestBody, DocDoUsedRequestDTO.class.getName());
	    		String docNum = "";
	    		String docVerCode="";
	    		if (requestBody.getDocNumInfoDTOs() != null && requestBody.getDocNumInfoDTOs().size()  <= 1) {
	    			docNum = requestBody.getDocNumInfoDTOs().get(0).getDocNum();
	    			docVerCode = requestBody.getDocVerCode();
				}
	    		record = ReportUtils.generateReport( "1", "D1", ReportUtils.substring(requestXml, 3999),docNum,docVerCode);
				String responseXml = ReportUtils.DTOtoXML(response, DocDoUsedResponse.class.getName());
	        	record.setErrorCode(responseHead.getResponseCode());
	        	record.setReportResult(responseHead.getResponseCode().equals("000") ? "1" : "0" );				
	        	record.setErrorDesc(responseHead.getResponseCode().equals("000") ? "" : responseHead.getResponseMessage());
	        	record.setResponseXml(responseXml);
	        	record.setResponseTime(new Date());
	        	vcReportService.nestSaveReportNest(record);
			}
		} catch (Exception e) {
			logger.info("单证核销报文新增失败：" + e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
}
