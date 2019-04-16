package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.component;
//作废
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.component.ValidCheck;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.service.VcNewInvoiceDocDoAnnuledService;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.NewInvoiceDocDoAnnuledRequest;
@WebService(targetNamespace="http://service.tapi.com/vc/newInvoiceDocDoAnnuledService/intf", serviceName="NewInvoiceDocDoAnnuledService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.component.NewInvoiceDocDoAnnuledService")
public class NewInvoiceDocDoAnnuledServiceImpl implements  NewInvoiceDocDoAnnuledService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcNewInvoiceDocDoAnnuledService doAnnuledService;
	@Override
	public String newInvoiceDocDoAnnuled(String request) {
		logger.error("请求报文"+request);
		parserXml parser= new parserXml();
		StorageResponse response=new StorageResponse();//返回RESPONSE
		ResponseDTO responseHead=new ResponseDTO();//头
		StorageResponseDTO responsebody  =new StorageResponseDTO();
		NewInvoiceDocDoAnnuledRequest parserRequest= null;
		String resultXml=null;
		try {
			parserRequest=(NewInvoiceDocDoAnnuledRequest) parser.guXmlToObject(request.trim(), NewInvoiceDocDoAnnuledRequest.class);
			responseHead.setfLOWINTIME(parserRequest.getHeadDTO().getfLOWINTIME());
			responseHead.setrEQSERIALNO(parserRequest.getHeadDTO().getrEQSERIALNO());
			responseHead.setrEQUESTTYPE(parserRequest.getHeadDTO().getrEQUESTTYPE());
		} catch (Exception e) {
			responsebody.setResultType("999");
			responsebody.setErrorInfo("newInvoiceDocDoAnnuled报文转对象转换错误"+e);
			response.setHead(responseHead);
			response.setResult(responsebody);
		}
		try {	
	      	String[] message=ValidCheck.checkNumStatus(parserRequest); 
			if(!"0".equals(message[0])){
				throw new BusinessException(message[1]);
			}
			response=doAnnuledService.executeNewDocDoAnnuled(parserRequest);
			resultXml= parser.guObjectToXml(response, response.getClass());
		} catch (Exception e) {
			responsebody.setResultType("999");
			responsebody.setErrorInfo(e.getMessage());
			response.setHead(responseHead);
			response.setResult(responsebody);
			logger.error("报文转换失败返回自己定义的报文");
			resultXml=parser.getObjectToXml_T(response);
		}
		return resultXml;
	}
	public VcNewInvoiceDocDoAnnuledService getDoAnnuledService() {
		return doAnnuledService;
	}
	public void setDoAnnuledService(VcNewInvoiceDocDoAnnuledService doAnnuledService) {
		this.doAnnuledService = doAnnuledService;
	}
}
