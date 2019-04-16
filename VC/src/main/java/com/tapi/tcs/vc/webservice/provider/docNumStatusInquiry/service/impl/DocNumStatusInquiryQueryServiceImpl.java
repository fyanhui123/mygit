package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.DocNumStatusInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.bean.ResultInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.dao.DocNumStatusInquiryDao;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiry.service.DocNumStatusInquiryQueryService;

public class DocNumStatusInquiryQueryServiceImpl implements DocNumStatusInquiryQueryService {

	private  DocNumStatusInquiryDao  newDocNumStatusInquiryDao;
	 /***
	  *   新核心系统   单证流水号状态查询接口
	  */
	@Override
	public DocNumStatusInquiryResponse statusHandleQuery(DocNumStatusInquiryRequestDTO requestBody) throws BusinessException {
		
	       DocNumStatusInquiryResponse response=new DocNumStatusInquiryResponse();
		//  response=newDocNumStatusInquiryDao.docNumStatusInquiry(body);
	        VcAvailableDoc vcAvailableDoc=new  VcAvailableDoc(); 
			ResultInfoDTO resultInfoDTO=new ResultInfoDTO();
	        long   startNum=Long.valueOf(requestBody.getStartNum()); 
	        long   endNum=Long.valueOf(requestBody.getEndNum());
	        String docVerCode=requestBody.getDocVerCode();
	        String operatorCode=requestBody.getOperatorCode();
	        String orgCode=requestBody.getOrgCode();
	        
	        String agentCode = requestBody.getAgentCode();
	        //add by whj 20130917
	        agentCode=null;
	        
	        //add by chy 20131017 增加印刷批次 begin
	        String pressBatchNo = requestBody.getPressBatchNo();
	        if(StringUtils.isBlank(pressBatchNo)){
	        	pressBatchNo = "000";
	        }
	        //add by chy 20131017 增加印刷批次 end
	        
	        ResponseHeadDTO  responseHead=new ResponseHeadDTO();
			DocNumStatusInquiryResponseDTO responseBody=new  DocNumStatusInquiryResponseDTO();
			ResultInfoDTO result=null;
			List<ResultInfoDTO>  resultInfoDTOs=new ArrayList<ResultInfoDTO>();
			DecimalFormat format = new DecimalFormat("0");
			format.setMinimumIntegerDigits(requestBody.getStartNum().length());
			
	        try{
	        	
		        for (long j = startNum; j< endNum + 1; j++) { 
		        	String  docNum=format.format(j);
		        	vcAvailableDoc=newDocNumStatusInquiryDao.
		        		docNumStatusInquiry(docVerCode, docNum, operatorCode, orgCode, agentCode, pressBatchNo);
					if(vcAvailableDoc!=null){
					    //增加单证有效期校验
					      boolean  isAvailable = true; 
					      if( DateUtils.compare(DateUtils.reset(vcAvailableDoc.getDeadline()), DateUtils.reset(new Date())) < 0){
					          isAvailable=false; 
					      }
						   //0：成功
							 if(vcAvailableDoc.getDocStatus().equals("A") && isAvailable){ 
								    responseHead.setResponseCode("000");
									responseHead.setResponseMessage("调用成功!");
									responseBody.setResultInfoDTOs(null);
									responseBody.setStatus("0");
									response.setResponseHead(responseHead);
									response.setResponseBody(responseBody);
							   }
						   //3:流水号不可用
							 else if(!vcAvailableDoc.getDocStatus().equals("A") || !isAvailable){
								 
								   responseHead.setResponseCode("999");  
								   if(!isAvailable){
								      responseHead.setResponseMessage("流水号已过使用截止期!"); 
								   }else{
								      responseHead.setResponseMessage("流水号不可用!");
								   }
								   result=new ResultInfoDTO();
								   result.setDocNum(String.valueOf(vcAvailableDoc.getDocNum()));
								   resultInfoDTOs.add(result);
								   responseBody.setResultInfoDTOs(resultInfoDTOs);
								   responseBody.setStatus("3");
								   response.setResponseHead(responseHead);
								   response.setResponseBody(responseBody);
								   return  response;
						   }
						}
						// 3:流水号不存在(满足某一个条件直接return、不同条件的不再组合在一起)
						else{
							
							responseHead.setResponseCode("999");
							responseHead.setResponseMessage("流水号不存在!");
							result=new ResultInfoDTO();
							result.setDocNum(String.valueOf(j));  
							resultInfoDTOs.add(result);
							responseBody.setResultInfoDTOs(resultInfoDTOs);
							responseBody.setStatus("3");
							response.setResponseHead(responseHead);
							response.setResponseBody(responseBody);
							return response;
						}
		        }
	        }catch(Exception  e){
	        	e.printStackTrace();
	        	throw new BusinessException("查询数据库异常！");
	        }
		 return  response;
	    
	}
	
	 /***
	  *   新核心系统   单证流水号状态查询接口
	  *   错误号段返回前50个
	  */
	@Override
	public DocNumStatusInquiryResponse statusHandleQueryNew(
			DocNumStatusInquiryRequestDTO requestBody) throws BusinessException {

		DocNumStatusInquiryResponse response = new DocNumStatusInquiryResponse();
		VcAvailableDoc vcAvailableDoc = new VcAvailableDoc();
		ResultInfoDTO resultInfoDTO = new ResultInfoDTO();
		long startNum = Long.valueOf(requestBody.getStartNum());
		long endNum = Long.valueOf(requestBody.getEndNum());
		StringBuilder sb = new StringBuilder("");
		String docVerCode = requestBody.getDocVerCode();
		String operatorCode = requestBody.getOperatorCode();
		String orgCode = requestBody.getOrgCode();
		String agentCode = requestBody.getAgentCode();
		agentCode = null;
		String pressBatchNo = requestBody.getPressBatchNo();
		if (StringUtils.isBlank(pressBatchNo)) {
			pressBatchNo = "000";
		}
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocNumStatusInquiryResponseDTO responseBody = new DocNumStatusInquiryResponseDTO();
		ResultInfoDTO result = null;
		List<ResultInfoDTO> resultInfoDTOs = new ArrayList<ResultInfoDTO>();
		DecimalFormat format = new DecimalFormat("0");
		format.setMinimumIntegerDigits(requestBody.getStartNum().length());
		try {
			int count = 0;
			for (long j = startNum; j < endNum + 1; j++) {
				String docNum = format.format(j);
				vcAvailableDoc = newDocNumStatusInquiryDao.docNumStatusInquiry(
						docVerCode, docNum, operatorCode, orgCode, agentCode, pressBatchNo);
				// 3:流水号不存在
				if (vcAvailableDoc == null) {
					result = new ResultInfoDTO();
					sb.append("流水号" + docNum + "不存在、");
					result.setDocNum(docNum);
					resultInfoDTOs.add(result);
					count++;
				} else {
					// 单证有效期校验
					boolean isAvailable = true;
					if (DateUtils.compare(DateUtils.reset(vcAvailableDoc.getDeadline()), DateUtils.reset(new Date())) < 0){
						isAvailable = false;
					}
					// 3:流水号不可用
					if (!vcAvailableDoc.getDocStatus().equals("A") || !isAvailable) {
						if (!isAvailable) {
							sb.append("流水号" + docNum + "已过使用截止期、");
						} else {
							sb.append("流水号" + docNum + "不可用、");
						}
						result = new ResultInfoDTO();
						result.setDocNum(String.valueOf(vcAvailableDoc.getDocNum()));
						resultInfoDTOs.add(result);
						count++;
					}
				}
				// 只返回50个错误流水号
				if (count == 50) {
					break;
				}
			}
			String responseMessage = "";
			if (sb.length() > 0) {
				responseMessage = sb.substring(0, sb.length() - 1);
			}
			if (resultInfoDTOs.size() > 0) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage(responseMessage);
				responseBody.setResultInfoDTOs(resultInfoDTOs);
				responseBody.setStatus("3");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			} else {
				// 0：成功
				responseHead.setResponseCode("000");
				responseHead.setResponseMessage("调用成功!");
				responseBody.setResultInfoDTOs(null);
				responseBody.setStatus("0");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询数据库异常！");
		}
	}
	public void setNewDocNumStatusInquiryDao(
			DocNumStatusInquiryDao newDocNumStatusInquiryDao) {
		this.newDocNumStatusInquiryDao = newDocNumStatusInquiryDao;
	}
}
