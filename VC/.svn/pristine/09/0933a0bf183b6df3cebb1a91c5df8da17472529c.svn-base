package com.tapi.tcs.vc.webservice.provider.outStorage.component;

import javax.jws.WebService;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.webservice.provider.outStorage.bean.OutStorageRequest;
import com.tapi.tcs.vc.webservice.provider.outStorage.service.VcOutStorageService;
@WebService(targetNamespace="http://service.tapi.com/vc/outStorageService/intf", serviceName="OutStorageService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.outStorage.component.OutStorageService")
public class OutStorageServiceImpl implements OutStorageService{
	 protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcOutStorageService vcOutStorageService;
	@SuppressWarnings("static-access")
	@Override
	public String outStorage(String request) throws DaoException {
		logger.error("请求报文"+request);
		parserXml parser= new parserXml();
		OutStorageRequest outStorage =new OutStorageRequest();
		StorageResponse response=new StorageResponse();
		ResponseDTO responseHead=new ResponseDTO();
		String resultXml;
		StorageResponseDTO responsebody=new StorageResponseDTO();
		try {
			//将报文转换成对象
			outStorage=(OutStorageRequest) parser.guXmlToObject(request.trim(), OutStorageRequest.class);
		} catch (Exception e) {
			responsebody.setResultType("999");
			responsebody.setErrorInfo("请求报文转换对象失败");
			responseHead.setfLOWINTIME(responseHead.getfLOWINTIME());
			responseHead.setrEQSERIALNO(responseHead.getrEQSERIALNO());
			responseHead.setrEQUESTTYPE(responseHead.getrEQUESTTYPE());
			response.setHead(responseHead);
			response.setResult(responsebody);
			logger.error(request+"请求报文转换失败",e);
		}
		try {
			String[] message=ValidCheck.checkNumStatus(outStorage); 
			if(!"0".equals(message[0])){
				logger.error("验证失败",message[1]);
				throw new BusinessException(message[1]);
			}
			response =vcOutStorageService.updateSplitStorageHandle(outStorage);
		}catch(DaoException b){
			logger.error("验证失败",b);
			throw new DaoException(b.getMessage());
		}
		catch (Exception e) {
			responsebody.setResultType("999");
			responsebody.setErrorInfo("自动分发失败"+e.getMessage());
			responseHead.setfLOWINTIME(responseHead.getfLOWINTIME());
			responseHead.setrEQSERIALNO(responseHead.getrEQSERIALNO());
			responseHead.setrEQUESTTYPE(responseHead.getrEQUESTTYPE());
			response.setHead(responseHead);
			response.setResult(responsebody);
			logger.error("验证失败",e);
		}
		try {
			resultXml= parser.guObjectToXml(response, response.getClass());
			logger.error("转换成功resultXml-->"+resultXml);
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		//转换失败，自己定义返回报文
		resultXml=parser.getObjectToXml_T(response);
		logger.error("转换失败返回自己定义的报文-->"+resultXml);
	}
		return resultXml;
	}
	public void setVcOutStorageService(VcOutStorageService vcOutStorageService) {
		this.vcOutStorageService = vcOutStorageService;
	}
	
}
