package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.component;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocSerialNoScope;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.service.DocAnnuledService;

@WebService(targetNamespace="http://service.tapi.com/vc/docDoAnnuledOld/intf", serviceName="DocDoAnnuledService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.component.DocDoAnnuledOldService")
public class DocDoAnnuledOldServiceImpl implements DocDoAnnuledOldService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private DocAnnuledService docAnnuledService;
	/**
	 * 单证作废
	 * 
	 * @param request
	 * @return
	 */
	public DocDoAnnuledOldResponse saveDocDoAnnul(DocDoAnnuledOldRequest request) {
		DocDoAnnuledOldResponse response = new DocDoAnnuledOldResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoAnnuledOldResponseDTO responseBody = new DocDoAnnuledOldResponseDTO();

		// 校验入参
		String[] messages = checkcheckParams(request);
		if (!"0".equals(messages[0])) {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(messages[1]);

			responseBody.setDoAnnuledStatus("2");

			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}

		try {
			response = docAnnuledService.saveDocAnnul(request);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统内部错误!");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		} 
		return response;
	}

	/**
	 * 检查入参
	 * 
	 * @param request
	 * @return
	 */
	private String[] checkcheckParams(DocDoAnnuledOldRequest request) {
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

		DocDoAnnuledOldRequestDTO requsetBody = request.getRequestBody();
		List<DocSerialNoScope> docSerialNoScopes = requsetBody.getDocSerialNoScope();
		StringBuffer errors = new StringBuffer();
		
		 Long  docVersionID=requsetBody.getDocVersionID();
		 Long  userId=requsetBody.getUserId();
		 //  long型默认值为零
		 if(docVersionID==null || docVersionID==0l){
			 errors.append("单证类型代码不能为空,");
		 }
		 //发票代码不能为空
		 /*if(StringUtils.isBlank(requsetBody.getPressBatchNo())){
			 errors.append("发票代码不能为空！");
		 }*/
		 if(userId==null || userId==0l){
			 errors.append("操作员不能为空,");
		 }
		/* if (StringUtils.isEmpty(requsetBody.getCompanyId())) {
			errors.append("归属机构不能为空,");
		 }*/
		 if(requsetBody.getDepartId()==null || requsetBody.getDepartId()<=0){
			 errors.append("部门ID不能为空,");
		 }
		 if(StringUtils.isBlank(requsetBody.getDepartCode())){
			 errors.append("部门代码不能为空,");
		 }
		 if (docSerialNoScopes == null || docSerialNoScopes.size() == 0) {
				errors.append("流水号区间段不能为空,");
		 }

		for (DocSerialNoScope docSerialNoScope : docSerialNoScopes) {
			//modify by chy 20130821 begin
			/*if (StringUtils.isEmpty(docSerialNoScope.getDocBeginSerialNo())) {
				errors.append("单证起始流水号不能为空,");
				break;
			}

			if (StringUtils.isEmpty(docSerialNoScope.getDocEndSerialNo())) {
				errors.append("单证终止流水号不能为空,");
				break;
			}
			
			if(StringUtils.isEmpty(docSerialNoScope.getPolicyNo())){
				errors.append("保单号不能为空,");
				break;
			}*/
			
			if (StringUtils.isEmpty(docSerialNoScope.getDocNum())) {
				errors.append("单证流水号不能为空,");
				break;
			}
			/*List<BusinessNoDTO> businessNoDtos = docSerialNoScope.getBusinessNoDTOs();
			if(businessNoDtos==null || businessNoDtos.size()<1){
				errors.append("业务号列表不能为空,");
				break;
			}
			for(BusinessNoDTO businessNoDTO:businessNoDtos){
				if(StringUtils.isEmpty(businessNoDTO.getBusinessNo())){
					errors.append("业务号不能为空,");
					break;
				}
			}*/
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

	public void setDocAnnuledService(DocAnnuledService docAnnuledService) {
		this.docAnnuledService = docAnnuledService;
	}
}
