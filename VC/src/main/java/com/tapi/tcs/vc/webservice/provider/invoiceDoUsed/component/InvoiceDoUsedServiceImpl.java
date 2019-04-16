package com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.component;

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
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.bean.InvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.service.VcInvoiceDoUsedService;


@WebService(targetNamespace="http://service.tapi.com/vc/invoiceDoUsed/intf", serviceName="InvoiceDoUsedService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.invoiceDoUsed.component.InvoiceDoUsedService")
public class InvoiceDoUsedServiceImpl implements InvoiceDoUsedService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    /**
	 * 记录报文service
	 */
	private VcReportService vcReportService;
    
	private VcInvoiceDoUsedService vcInvoiceDoUsedService;
	
	public InvoiceDoUsedResponse invoiceDoUsedQuery(
			InvoiceDoUsedRequest invoiceDoUsedRequest) throws DaoException{
		
		InvoiceDoUsedResponse response=new InvoiceDoUsedResponse();
		ResponseHeadDTO  responseHead=new ResponseHeadDTO();
		InvoiceDoUsedResponseDTO  responseBody=new InvoiceDoUsedResponseDTO();

		try { 
			//数据校验
			String[] message=ValidCheck.checkNumStatus(invoiceDoUsedRequest);   
			if(!"0".equals(message[0])){
				throw new BusinessException(message[1]);
			}
			response=vcInvoiceDoUsedService.executeInvoiceDoUsedHandle(invoiceDoUsedRequest);  
	    }catch(DaoException e) {
	    	throw new DaoException(e.getMessage());
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
    		responseBody = response.getResponseBody();
    		responseHead = response.getResponseHead();
    		//status:返回状态 判断发票核销是否成功
    		String status = responseBody.getInvoiceDoUsedResultList() == null ? "" : responseBody.getInvoiceDoUsedResultList().get(0).getStatus();
    		if ( StringUtils.isNotBlank(status) && !status.equals("0")) {
    			InvoiceDoUsedRequestDTO requestBody = invoiceDoUsedRequest.getRequestBody();
    			String requestXml = ReportUtils.DTOtoXML(requestBody, InvoiceDoUsedRequestDTO.class.getName());
    			String docNum = "";
    			String docVerCode="";
    			if (requestBody.getDocNumInfoDTOs() != null && requestBody.getDocNumInfoDTOs().size()  <= 1) {
    				docNum = requestBody.getDocNumInfoDTOs().get(0).getDocNum();
    				docVerCode = requestBody.getDocVerCode();
    			}
    			record = ReportUtils.generateReport( "1", "I1", ReportUtils.substring(requestXml, 3999),docNum,docVerCode);
    			String responseXml = ReportUtils.DTOtoXML(response, InvoiceDoUsedResponse.class.getName());
            	record.setErrorCode(responseHead.getResponseCode());
            	record.setReportResult(status.equals("0") ? "1" : "0" );				
            	record.setErrorDesc(responseHead.getResponseCode().equals("000") ? "" : responseHead.getResponseMessage());
            	record.setResponseXml(responseXml);
            	record.setResponseTime(new Date());
    			vcReportService.nestSaveReportNest(record);
    		}
		} catch (Exception e) {
			logger.info("发票核销报文新增失败：" + e.getMessage());
		}
		return response;
	}

    public void setVcInvoiceDoUsedService(VcInvoiceDoUsedService vcInvoiceDoUsedService) {
        this.vcInvoiceDoUsedService = vcInvoiceDoUsedService;
    }

	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}

}
