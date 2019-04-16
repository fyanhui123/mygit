package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.component;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.service.DocNumStatusInquiryQueryService;
@WebService(targetNamespace="http://service.tapi.com/vc/docNumStatusInquiry/intf", serviceName="DocNumStatusInquiryService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.component.DocNumStatusInquiryService")
public class DocNumStatusInquiryServiceImpl implements DocNumStatusInquiryService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
    private    DocNumStatusInquiryQueryService  docNumStatusInquiryQueryService;
   public void setDocNumStatusInquiryQueryService(
			DocNumStatusInquiryQueryService docNumStatusInquiryQueryService) {
		this.docNumStatusInquiryQueryService = docNumStatusInquiryQueryService;
	}
	@Override
	public DocNumStatusInquiryResponse statusQuery(DocNumStatusInquiryRequest request){
		DocNumStatusInquiryResponse response=new DocNumStatusInquiryResponse();
		ResponseHeadDTO  responseHead=new ResponseHeadDTO();
		DocNumStatusInquiryResponseDTO   responseBody=new DocNumStatusInquiryResponseDTO();
		
		// 校验输入的内容不能为空
		String[] message=this.checkNumStatus(request);
		if(!"0".equals(message[0])){
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(message[1]); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return  response; 
		}
		
		  // 把查询条件放到requestBody
		try {
		   response=docNumStatusInquiryQueryService.statusHandleQueryNew(request.getRequestBody()); 
	    }
		catch (BusinessException e) {  
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage()); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return  response;
		}
	    catch (Exception e) {  
	    	logger.error(e.getMessage(), e);
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("系统异常，请与管理员联系！");
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return response;
		}
		return response;
	}
	
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public String[] checkNumStatus(DocNumStatusInquiryRequest request){
		 String[] message=new String[2];
		 if(request==null){
			 message[0]="10";
			 message[1]="服务请求不能为空!";
			 return  message;
		 }
		 if(request.getRequestHead()==null){
			 message[0]="10";
			 message[1]="请求头不能为空!";
			 return  message;
		 }
		 if(StringUtils.isEmpty(request.getRequestHead().getSystemCode())){
			 message[0]="10";
			 message[1]="系统代码不能为空!";
			 return  message;
		 }
		 if(request.getRequestBody()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 
		 RequestHeadDTO requestHead = request.getRequestHead();
		 DocNumStatusInquiryRequestDTO  body=request.getRequestBody(); 
		 StringBuilder sb=new StringBuilder("");
		 
		 if(StringUtils.isEmpty(body.getDocVerCode())){
		     sb.append("单证类型代码不能为空！");
		 }
		 if (SysConst.EXTERNAL_SYSTEM_CODE.indexOf(requestHead.getSystemCode())<0){
			 //非外部接口调用，操作员代码校验非空
			 if(StringUtils.isEmpty(body.getOperatorCode())){
				 sb.append("操作员代码不能为空！");
			 }
		 }
		 if(StringUtils.isEmpty(body.getOrgCode())){
		     sb.append("操作员归属机构代码不能为空！");
		 }
		 if(StringUtils.isEmpty(body.getStartNum())){
		     sb.append("单证起始流水号不能为空！");
		 }
		 if(StringUtils.isEmpty(body.getEndNum())){
		     sb.append("单证终止流水号不能为空！");
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}
	
	
}
