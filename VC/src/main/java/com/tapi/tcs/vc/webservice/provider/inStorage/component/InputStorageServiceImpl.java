package com.tapi.tcs.vc.webservice.provider.inStorage.component;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.parserXml;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcReportRecord;
import com.tapi.tcs.vc.webservice.provider.common.service.VcReportService;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.GroupDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequest;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageRequestDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponse;
import com.tapi.tcs.vc.webservice.provider.inStorage.bean.InputStorageResponseDTO;
import com.tapi.tcs.vc.webservice.provider.inStorage.service.VcInputStorageService;

@WebService(targetNamespace="http://service.tapi.com/vc/inputStorageService/intf", serviceName="InputStorageService", 
endpointInterface="com.tapi.tcs.vc.webservice.provider.inStorage.component.InputStorageService")
public class InputStorageServiceImpl implements InputStorageService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcInputStorageService  vcInputStorageService;
	private VcReportService vcReportService;
	public String saveStorage(String requests) throws DaoException{
		logger.error("请求报文"+requests);
		parserXml parser= new parserXml();
		InputStorageResponse response=new InputStorageResponse();
		ResponseDTO  responseHead=new ResponseDTO();
		InputStorageResponseDTO  responsebody = new InputStorageResponseDTO();
		InputStorageRequest request =null;
		String  resultXml="";
		try {
			request = (InputStorageRequest) parser.guXmlToObject(requests.trim(), InputStorageRequest.class);
			logger.error("转换后的报文是-->"+request);
			String[] message=ValidCheck.checkNumStatus(request); 
			if(!"0".equals(message[0])){
				throw new BusinessException(message[1]);
			}
			response=vcInputStorageService.saveStorageHandle(request);
			logger.error("返回结果报文-->"+response);
			resultXml= parser.guObjectToXml(response, response.getClass());
			logger.error("转换成功resultXml-->"+resultXml);
			     String status=response.getrESULT() == null ? "" : response.getrESULT().getrESULTTYPE();
				 InputStorageRequestDTO requestDTO=request.getInputStorageRequestDTO();
	    	     Date nowDate = new Date();
	    	     if("999".equals(response.getrESULT().getrESULTTYPE())){
	    	    	 String	 docVerCode=  vcInputStorageService.getdocVerCode(requestDTO.getfPLXDM()); //转换单证类型
	    	    	 List<GroupDTO> groupDTO=requestDTO.getrESULTLIST();
	    	    	 for(int i=0;i<groupDTO.size();i++){
	    	    		 VcReportRecord record = new VcReportRecord();
	    	    		     String  docNum=groupDTO.get(i).getqSHM();
	    	    		     record.setDocNum(docNum!=null?docNum:"");
	    	    		     record.setEndNum(groupDTO.get(i).getzZHM());
	    	    		     record.setDocVerCode(docVerCode!=null?docVerCode:"");
	    	    		     record.setReportTag("1");
	    	    		     record.setReportType("I0");  //状态为I0的时候代表发票入库
	    	    		     record.setReportResult(responsebody.getrESULTTYPE());
	    	    		     record.setRequestXml(requests);
	    	    		     record.setRequestTime(nowDate);
	    	    		     record.setResponseXml(resultXml);
	    	    		     record.setResponseTime(new Date());
	    	    		     record.setErrorCode(responsebody.getrESULTTYPE());
	    		    	     record.setErrorDesc(responsebody.geteRRORINFO());
	    		    	     record.setCreatedBy(groupDTO.get(i).getlGRYCODE());
	    		    	     record.setUpdatedBy(groupDTO.get(i).getlGRYCODE());
	    		    	     vcReportService.nestSaveReportNest(record);
	    	    	 }
	    	     }
		}catch (DaoException e) {
			throw new DaoException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			responsebody.setrESULTTYPE("999");
			responsebody.seteRRORINFO(e.getMessage());
			responseHead.setfLOWINTIME(request.getHeadDTO().getfLOWINTIME());
			responseHead.setrEQSERIALNO(request.getHeadDTO().getrEQSERIALNO());
			responseHead.setrEQUESTTYPE(request.getHeadDTO().getrEQUESTTYPE());
			response.sethEAD(responseHead);
			response.setrESULT(responsebody);
			logger.error(e.getMessage(), e);
			//转换失败，自己定义返回报文
			resultXml=parser.guObjectToXml_T(response);
			logger.error("转换失败返回自己定义的报文-->"+resultXml);
		}
		return resultXml;
	}
	public void setVcReportService(VcReportService vcReportService) {
		this.vcReportService = vcReportService;
	}
	public void setVcInputStorageService(VcInputStorageService vcInputStorageService) {
		this.vcInputStorageService = vcInputStorageService;
	}

}
