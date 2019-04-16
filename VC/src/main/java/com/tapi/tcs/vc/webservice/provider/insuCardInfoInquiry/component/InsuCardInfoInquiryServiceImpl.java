package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.component;

import javax.jws.WebService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.service.VcInsuCardInquiryService;

@WebService(targetNamespace="http://service.tapi.com/vc/insuCardInfoInquiry/intf", 
		serviceName="InsuCardInfoInquiryService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.component.InsuCardInfoInquiryService")
public class InsuCardInfoInquiryServiceImpl implements InsuCardInfoInquiryService {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcInsuCardInquiryService vcInsuCardInquiryService;
	
	/**
	 * 激活卡信息查询接口入口程序
	 */
	@Override
	public InsuCardInfoInquiryResponse insuCardInfoInquiry(InsuCardInfoInquiryRequest request){
		InsuCardInfoInquiryResponse response = new InsuCardInfoInquiryResponse();
		try{
			//校验请求对象
			String message = this.check(request);
			if(StringUtils.isNotEmpty(message)){
				ResponseHeadDTO responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message);
				InsuCardInfoInquiryResponseDTO responseBody = new InsuCardInfoInquiryResponseDTO();
				responseBody.setStatus("4");
				responseBody.setResultMessage(message);
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
			InsuCardInfoInquiryRequestDTO requestBody = request.getRequestBody();
			response = vcInsuCardInquiryService.queryInsuCardInfo(requestBody);
			/*responseBody = vcInsuCardInquiryService.queryInsuCardInfo(requestBody);
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("查询成功！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);*/
		}catch(BusinessException e){
			e.printStackTrace();
			ResponseHeadDTO responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage());
			InsuCardInfoInquiryResponseDTO responseBody = new InsuCardInfoInquiryResponseDTO();
			responseBody.setStatus("5");
			responseBody.setResultMessage(e.getMessage());
			response.setResponseBody(responseBody);
			response.setResponseHead(responseHead);
			return response;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			ResponseHeadDTO responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			InsuCardInfoInquiryResponseDTO responseBody = new InsuCardInfoInquiryResponseDTO();
			responseBody.setStatus("5");
			responseBody.setResultMessage("内部错误！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		return response;
	}

	/**
	 * 校验请求对象
	 * @param request
	 * @return
	 */
	private String check(InsuCardInfoInquiryRequest request) {
		String msg = "";
		if(request.getRequestHead()==null){
			msg += "请求头不能为空！";
		}
		if(request.getRequestBody()==null){
			msg += "请求体不能为空！";
		}else{
			if(StringUtils.isBlank(request.getRequestBody().getCardNO())){
				msg += "卡号不能为空！";
			}
			//modify by zhxiao3 VC-131仅以保险卡卡号为查询条件查询保险卡信息 begin
			//if(StringUtils.isBlank(request.getRequestBody().getEncryptedPW())){
			//	msg += "密码不能为空！";
			//}
			//modify by zhxiao3 VC-131仅以保险卡卡号为查询条件查询保险卡信息 end
		}
		return msg;
	}
	public void setVcInsuCardInquiryService(
			VcInsuCardInquiryService vcInsuCardInquiryService) {
		this.vcInsuCardInquiryService = vcInsuCardInquiryService;
	}
}
