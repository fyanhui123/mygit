package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.component;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeRequest;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeResponse;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocProvideNumDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.service.QueryProvideNumService;


@WebService(targetNamespace = "http://service.tapi.com/vc/docNumSynchronize/intf", serviceName = "DocNumSynchronizeService", endpointInterface = "com.tapi.tcs.vc.webservice.provider.docNumSynchronize.component.DocNumSynchronizeService")

public class DocNumSynchronizeServiceImpl implements DocNumSynchronizeService {
	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private QueryProvideNumService queryProvideNumService;
	
	public QueryProvideNumService getQueryProvideNumService() {
		return queryProvideNumService;
	}
	public void setQueryProvideNumService(
			QueryProvideNumService queryProvideNumService) {
		this.queryProvideNumService = queryProvideNumService;
	}

	@Override
	public DocNumSynchronizeResponse docNumSynchronize(DocNumSynchronizeRequest request) {
		// TODO Auto-generated method stub
		DocNumSynchronizeResponse response=new DocNumSynchronizeResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		
			// 校验输入的内容不能为空
			String errMsg = this.checkParam(request);
			if (StringUtils.isNotEmpty(errMsg)) {
				responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(errMsg);
				response.setResponseHead(responseHead);
				return response;
			}
	       try{
			response =queryProvideNumService.executeProvideNumInquiry(request.getRequestBody());		
		}catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统内部错误！");
			response.setResponseHead(responseHead);
		}
		return response;
	}
	
		
	private String checkParam(DocNumSynchronizeRequest request) {
		StringBuffer result = new StringBuffer();
		if (request.getRequestHead() == null) {
			result.append("请求头不能为空!");
		}
		DocNumSynchronizeRequestDTO body=request.getRequestBody();
		if(body==null){
			result.append("请求体不能为空!");
		}else{
			if (StringUtils.isBlank(body.getDocVerCode())){
				result.append("单证类型代码不能为空!");				
			}
			if(StringUtils.isBlank(body.getOpAndProxyId()))	{
				result.append("领用人代码不能为空!");	
			}
			}
		return result.toString();
		}

		
	}
