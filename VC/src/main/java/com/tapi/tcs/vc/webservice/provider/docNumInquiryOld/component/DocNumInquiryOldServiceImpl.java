package com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.component;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.bean.DocNumInquiryOldResponse;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.service.DocNumQueryService;
 
@WebService(targetNamespace="http://service.tapi.com/vc/docNumInquiryOld/intf", serviceName="DocNumInquiryOldService", 
		endpointInterface="com.tapi.tcs.vc.webservice.provider.docNumInquiryOld.component.DocNumInquiryOldService")
public class DocNumInquiryOldServiceImpl implements DocNumInquiryOldService{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private  DocNumQueryService docNumQueryService;  
	@Override
	public DocNumInquiryOldResponse docNumInquiryQuery(DocNumInquiryOldRequest request){
		DocNumInquiryOldResponse response=new DocNumInquiryOldResponse();
		ResponseHeadDTO  responseHead = null;
		
		try {
			// 校验输入的内容不能为空
			String[] message = this.checkDocNumInquiry(request);
			if (!"0".equals(message[0])) {
				responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(message[1]);
				response.setResponseHead(responseHead);
				return response;
			}
			// 根据查询条件、查询数据库、封装成一个实体、查询出list集合
			// 把查询条件放到requestBody
			response = docNumQueryService.vcAvailableQuery(request
					.getRequestBody());
		}
		catch (BusinessException be) { 
			logger.error(be.getMessage(), be);
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(be.getMessage()); 
			response.setResponseHead(responseHead);
			return  response;
		} 
	    catch (Exception e) {
	    	logger.error(e.getMessage(), e);
			responseHead=new ResponseHeadDTO();
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage(e.getMessage()); 
			response.setResponseHead(responseHead);
			return  response;
		}
		return response;
	}
 
	 /***
	  *    校验请求头、请求体内容
	  * **/
	public String[] checkDocNumInquiry(DocNumInquiryOldRequest request){
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
		 if(request.getRequestBody()==null){
			 message[0]="10";
			 message[1]="请求体不能为空!";
			 return  message;
		 }
		 DocNumInquiryOldRequestDTO  body=request.getRequestBody();
		 StringBuilder sb=new StringBuilder("");
		 Long  docVerID=body.getDocVerID();
		 Long  operatorID=body.getOperatorID();
		 //  long型默认值为零
		 if(docVerID==null || docVerID<= 0){
			 sb.append("单证类型ID不能为空！");
		 }
		 if(operatorID==null || operatorID<= 0){ 
		     sb.append("操作员ID不能为空！"); 
		 }
		 /*if(StringUtils.isEmpty(body.getOrgID())){
		     sb.append("操作员归属机构ID不能为空！");
		 }*/
		 if(body.getDepID()==null || body.getDepID()<=0){
			 sb.append("部门id不能为空！");
		 }
		 if(StringUtils.isBlank(body.getDepCode())){
			 sb.append("部门代码不能为空！");
		 }
		 if(StringUtils.isNotEmpty(sb.toString())){
				message[0] = "10";
				message[1] = sb.toString();
				return message;
		 }
		 message[0]="0";
		 return message;
	}

	public void setDocNumQueryService(DocNumQueryService docNumQueryService) {
		this.docNumQueryService = docNumQueryService;
	}
	
}
