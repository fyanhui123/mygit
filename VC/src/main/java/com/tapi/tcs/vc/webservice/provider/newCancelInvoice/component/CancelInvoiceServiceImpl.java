package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.component;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequest;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.service.VcCancelInvoiceService;
@WebService(targetNamespace="http://service.tapi.com/vc/cancelInvoiceService/intf", serviceName="CancelInvoiceService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.newCancelInvoice.component.CancelInvoiceService")
public class CancelInvoiceServiceImpl implements  CancelInvoiceService{
	 protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private	VcCancelInvoiceService  vcCancelInvoiceService;
	@Override
	public String cancelInvoice(String request)
			throws DaoException {
		logger.error("请求报文"+request);
		parserXml parser= new parserXml();
		CancelInvoiceRequest cancelStorage=null;
		StorageResponse response=new StorageResponse();//返回RESPONSE
		ResponseDTO responseHead=new ResponseDTO();//头
		StorageResponseDTO responsebody  =new StorageResponseDTO();
		String resultXml=null;
		try {
		try {
			//xml报文转成对象
			cancelStorage=(CancelInvoiceRequest) parser.guXmlToObject(request.trim(), CancelInvoiceRequest.class);
			responseHead.setfLOWINTIME(cancelStorage.getHeadDTO().getfLOWINTIME());
			responseHead.setrEQSERIALNO(cancelStorage.getHeadDTO().getrEQSERIALNO());
			responseHead.setrEQUESTTYPE(cancelStorage.getHeadDTO().getrEQUESTTYPE());
		} catch (Exception e) {
			throw new BusinessException("报文转换错误");
		}
			String[] message=ValidCheck.checkNumStatus(cancelStorage); 
			if(!"0".equals(message[0])){
				throw new BusinessException(message[1]);
			}
			response= vcCancelInvoiceService.saveVcCancelInvoice(cancelStorage);
			resultXml= parser.guObjectToXml(response, response.getClass());
		} catch (Exception e) {
			responsebody.setResultType("999");
			responsebody.setErrorInfo("验证错误"+e.getMessage());
			response.setResult(responsebody);
			response.setHead(responseHead);
			logger.error("报文转换失败返回自己定义的报文");
			resultXml=parser.getObjectToXml_T(response);
		}
		return resultXml;
	}
	public VcCancelInvoiceService getVcCancelInvoiceService() {
		return vcCancelInvoiceService;
	}
	public void setVcCancelInvoiceService(
			VcCancelInvoiceService vcCancelInvoiceService) {
		this.vcCancelInvoiceService = vcCancelInvoiceService;
	}
}
