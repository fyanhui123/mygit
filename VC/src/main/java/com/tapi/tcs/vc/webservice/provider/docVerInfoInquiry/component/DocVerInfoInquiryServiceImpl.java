package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.component;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;









import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.service.DocVerInfoInquiryQueryService;

@WebService(targetNamespace="http://service.tapi.com/vc/docVerInfoInquiry/intf", serviceName="DocVerInfoInquiryService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.component.DocVerInfoInquiryService")
public class DocVerInfoInquiryServiceImpl implements DocVerInfoInquiryService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private  DocVerInfoInquiryQueryService docVerInfoInquiryQueryService;  
	@Override
	public  DocVerInfoInquiryResponse   docVerInfoQuery(DocVerInfoInquiryRequest request){
		DocVerInfoInquiryResponse response=new DocVerInfoInquiryResponse();
		ResponseHeadDTO  responseHead=null;
		DocVerInfoInquiryResponseDTO   responseBody=new DocVerInfoInquiryResponseDTO();
		
		// 校验输入的内容不能为空
		String[] message=this.checkVer(request);
		if(!"0".equals(message[0])){
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(message[1]); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return  response; 
		}
		try {
		   response=docVerInfoInquiryQueryService.handleQuery(request);
	    }
	    catch (BusinessException e) {
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage()); 
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
			return  response;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseHead = new ResponseHeadDTO();
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
	public String[] checkVer(DocVerInfoInquiryRequest request){
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
		 DocVerInfoInquiryRequestDTO  body=request.getRequestBody(); 
		 StringBuilder sb=new StringBuilder("");
		
		 if(StringUtils.isEmpty(body.getDocTypeCode())){
		     sb.append("单证种类代码不能为空！");
		 }
		 int pageNo = body.getPageNo();
		 int pageSize = body.getPageSize();
		 
		 if(pageNo <= 0){
		     sb.append("当前页数不能为空！");
		 }
		 if(pageSize <= 0){
		     sb.append("每页大小不能为空！");
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}

	public void setDocVerInfoInquiryQueryService(
			DocVerInfoInquiryQueryService docVerInfoInquiryQueryService) {
		this.docVerInfoInquiryQueryService = docVerInfoInquiryQueryService;
	}
	
}
