package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.component;

import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedDocNumDTO;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedRequest;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedResponse;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.service.CancelReversedService;


@WebService(targetNamespace = "http://service.tapi.com/vc/cancelDoReversed/intf", serviceName = "CancelDoReversedService", endpointInterface = "com.tapi.tcs.vc.webservice.provider.cancelDoReversed.component.CancelDoReversedService")
public class CancelDoReversedServiceImpl implements CancelDoReversedService {
	/** 日志管理 */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private CancelReversedService cancelReversedService;

	public CancelReversedService getCancelReversedService() {
		return cancelReversedService;
	}

	public void setCancelReversedService(CancelReversedService cancelReversedService) {
		this.cancelReversedService = cancelReversedService;
	}

	@Override
	public CancelReversedResponse cancelDoReversed(CancelReversedRequest request) {
		CancelReversedResponse response = new CancelReversedResponse();
		ResponseHeadDTO responseHead = null;

		String errMsg = this.checkParam(request);
		if (StringUtils.isNotEmpty(errMsg)) {
			responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(errMsg);
			response.setResponseHead(responseHead);
			return response;
		}
		try {
			response = cancelReversedService.executeCancelReversed(request.getRequestBody());
		} catch (BusinessException e) {
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

	private String checkParam(CancelReversedRequest request) {
		StringBuffer result = new StringBuffer();
		if (request.getRequestHead() == null) {
			result.append("请求头不能为空；");
		}
		CancelReversedRequestDTO body = request.getRequestBody();
		if (body == null) {
			result.append("请求体不能为空；");
		} else {
			if (StringUtils.isBlank(body.getDocVerCode())) {
				result.append("单证类型代码不能为空；");
			}
			if (StringUtils.isBlank(body.getOrgCode())) {
				result.append("归属机构代码不能为空；");
			}
			List<CancelReversedDocNumDTO> docNumList = body
					.getCancelReversedDocNumDTOList();
			if (docNumList == null || docNumList.size() == 0) {
				result.append("单证流水号列表不能为空；");
			} else {
				String msg = "";
				for (CancelReversedDocNumDTO docNumDto : docNumList) {
					if (StringUtils.isBlank(docNumDto.getDocNum())) {
						msg += "单证流水号不能为空；";
					} else if (StringUtils.isBlank(docNumDto.getBussinessNo())) {
						msg += "业务号不能为空；";
					}

					if (!"".equals(msg)) {
						result.append(msg);
						break;

					}
				}
			}
		}
		return result.toString();

	}
}
