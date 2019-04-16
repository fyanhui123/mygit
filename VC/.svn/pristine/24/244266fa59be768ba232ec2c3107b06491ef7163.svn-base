package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.component;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.NewInvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.service.VcNewInvoiceDoUsedService;
@WebService(targetNamespace="http://service.tapi.com/vc/newInvoiceDoUsedService/intf", serviceName="NewInvoiceDoUsedService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.component.NewInvoiceDoUsedService")
public class NewInvoiceDoUsedServiceImpl implements NewInvoiceDoUsedService{
	 protected final Logger logger = LoggerFactory.getLogger(getClass());
	 private VcNewInvoiceDoUsedService newVcInvoiceDoUsedService;
	@Override
	public String NewInvoiceDoUsed(String request) throws DaoException {
		logger.error("请求报文"+request);
		parserXml parser= new parserXml();
		String resultXml =null;
		StorageResponse response=new StorageResponse();//返回RESPONSE
		ResponseDTO responseHead=new ResponseDTO();//头
		StorageResponseDTO responsebody  =new StorageResponseDTO();
		NewInvoiceDoUsedRequest getRequest=new NewInvoiceDoUsedRequest();
			try {
				getRequest=(NewInvoiceDoUsedRequest)parser.guXmlToObject(request.trim(),NewInvoiceDoUsedRequest.class);
				responseHead.setfLOWINTIME(getRequest.getHeadDTO().getfLOWINTIME());
				responseHead.setrEQSERIALNO(getRequest.getHeadDTO().getrEQSERIALNO());
				responseHead.setrEQUESTTYPE(getRequest.getHeadDTO().getrEQUESTTYPE());
				logger.error("得到的转换后的"+getRequest);
				String[] message=ValidCheck.checkNumStatus(getRequest);
				if(!"0".equals(message[0])){
					throw new BusinessException(message[1]);
				}
				response=newVcInvoiceDoUsedService.executeInvoiceDoUsedService(getRequest);
				resultXml= parser.guObjectToXml(response, response.getClass());
			}
			catch (Exception e) {
				logger.error("出现异常",e);
				responsebody.setErrorInfo(e.getMessage());
				responsebody.setResultType("999");
				response.setHead(responseHead);
				response.setResult(responsebody);
				logger.error("报文转换失败返回自己定义的报文");
				resultXml=parser.getObjectToXml_T(response);
			}
		return resultXml;
	}
	public void setNewVcInvoiceDoUsedService(
			VcNewInvoiceDoUsedService newVcInvoiceDoUsedService) {
		this.newVcInvoiceDoUsedService = newVcInvoiceDoUsedService;
	}
}
