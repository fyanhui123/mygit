package com.tapi.tcs.vc.webservice.provider.docDoUsedOld.component;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldResponse;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocDoUsedOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.bean.DocSerialNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.service.DocExecuteUsedService;

@WebService(targetNamespace="http://service.tapi.com/vc/docDoUsedOld/intf", serviceName="DocDoUsedOldService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docDoUsedOld.component.DocDoUsedOldService")
public class DocDoUsedOldServiceImpl implements DocDoUsedOldService {

	/**日志管理*/
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private DocExecuteUsedService docExecuteUsedService;

	/**
	 * 单证核销
	 * @param request
	 * @return
	 */
	@Override
	public DocDoUsedOldResponse docDoUsed(DocDoUsedOldRequest request) {
		
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoUsedOldResponse response = new DocDoUsedOldResponse();
		DocDoUsedOldResponseDTO responseBody = new DocDoUsedOldResponseDTO();

		String[] message = checkParams(request);
		if (!"0".equals(message[0])) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(message[1]);
			responseBody.setStatus("3");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		try {
			response = docExecuteUsedService.saveDocDoUsed(request);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			responseBody.setStatus("9");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			responseBody.setStatus("9");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		
		return response;
	}

	private String[] checkParams(DocDoUsedOldRequest request) {
		String[] message = new String[2];

		if (request == null) {
			message[0] = "10";
			message[1] = "服务请求不能为空！";
			return message;
		}
		if (request.getRequestHead() == null) {
			message[0] = "10";
			message[1] = "请求头不能为空！";
			return message;
		}
		if (request.getRequestBody() == null) {
			message[0] = "10";
			message[1] = "请求体不能为空！";
			return message;
		}
		StringBuffer errors = new StringBuffer();
		DocDoUsedOldRequestDTO requestBody = request.getRequestBody();
		if (requestBody.getDocVersionID()==null || requestBody.getDocVersionID()<=0) {
			errors.append("单证类型ID不能为空,");
		}
		/*if (StringUtils.isEmpty(requestBody.getCompanyId())) {
			errors.append("机构ID不能为空,");
		}*/
		if(requestBody.getDepartId()==null || requestBody.getDepartId()<=0){
			errors.append("部门ID不能为空！");
		}
		if(StringUtils.isBlank(requestBody.getDepartCode())){
			errors.append("部门代码不能为空！");
		}
		if (requestBody.getUserId()==null || requestBody.getUserId()<=0) {
			errors.append("操作员不能为空,");
		}
		List<DocSerialNoDTO> docSerialNos = requestBody.getDocSerialNos();
		if (docSerialNos == null || docSerialNos.size() < 1) {
			errors.append("流水号区间段不能为空,");
			message[0] = "10";
			message[1] = errors.substring(0, errors.length() - 1);
			return message;
		}

		for (DocSerialNoDTO docSerialNoDTO : docSerialNos) {
			if (StringUtils.isEmpty(docSerialNoDTO.getDocNum())) {
				errors.append("单证流水号不能为空,");
				break;
			}
			//modify by chy 20130821 begin
			/*if (StringUtils.isEmpty(docSerialNoDTO.getBusinessNo())) {
				errors.append("业务号不能为空,");
				break;
			}*/
			List<BusinessNoDTO> businessNoDtos = docSerialNoDTO.getBusinessNoDTOs();
			if(businessNoDtos==null || businessNoDtos.size()<1){
				errors.append("业务号列表不能为空,");
				break;
			}
			for(BusinessNoDTO businessNoDTO : businessNoDtos){
				if(StringUtils.isEmpty(businessNoDTO.getBusinessNo())){
					errors.append("业务号不能为空,");
					break;
				}
			}
			//modify by chy 20130821 end
		}
		if (errors.length() > 0) {
			message[0] = "10";
			message[1] = errors.substring(0, errors.length() - 1);
			return message;
		}
		message[0] = "0";
		return message;
	}

	public void setDocExecuteUsedService(DocExecuteUsedService docExecuteUsedService) {
		this.docExecuteUsedService = docExecuteUsedService;
	}
}
