package com.tapi.tcs.vc.webservice.provider.docDoReversed.component;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedDocNumDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.service.DocReversedService;

@WebService(targetNamespace="http://service.tapi.com/vc/docDoReversed/intf", serviceName="DocDoReversedService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docDoReversed.component.DocDoReversedService")
public class DocDoReversedServiceImpl implements DocDoReversedService {
	
	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private DocReversedService docReversedService;
	
	@Override
	public DocDoReversedResponse docDoReversed(DocDoReversedRequest request) {
		DocDoReversedResponse response = new DocDoReversedResponse();
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
			response = docReversedService.executeDocDoReversed(request.getRequestBody());
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
	
	private String checkParam(DocDoReversedRequest request) {
		StringBuffer result = new StringBuffer();
		if(request.getRequestHead() == null){
			result.append("请求头不能为空；");
		}
		DocDoReversedRequestDTO body = request.getRequestBody();
		if(body == null){
			result.append("请求体不能为空；");
		}else{
			if(StringUtils.isBlank(body.getDocVerCode())){
				result.append("单证类型代码不能为空；");
			}
			if(StringUtils.isBlank(body.getOrgCode())){
				result.append("归属机构代码不能为空；");
			}
			List<DocDoReversedDocNumDTO> docNumList = body.getDocDoReversedDocNumDTOList();
			if(docNumList==null || docNumList.size()==0){
				result.append("单证流水号列表不能为空；");
			}else{
				String msg = "";
				for(DocDoReversedDocNumDTO docNumDto : docNumList){
					if(StringUtils.isBlank(docNumDto.getDocNum())){
						msg += "单证流水号不能为空；";
					}
					List<DocDoReversedBusinessNoDTO> businessDtoList = docNumDto.getDocDoReversedBusinessNoDTOList();
					if(businessDtoList==null || businessDtoList.size()==0){
						msg += "业务号列表不能为空；";
					}else{
						for(DocDoReversedBusinessNoDTO businessDto : businessDtoList){
							if(StringUtils.isBlank(businessDto.getBusinessNo())){
								msg += "业务号不能为空；";
								break;
							}
						}
					}
					if(!"".equals(msg)){
						result.append(msg);
						break;
					}
				}
			}
		}
		return result.toString();
	}
	
	public void setDocReversedService(DocReversedService docReversedService) {
		this.docReversedService = docReversedService;
	}
}
