package com.tapi.tcs.vc.webservice.provider.docNumSynchronize.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocRelInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocTakerIo;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedResponse;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiryNew.bean.SingleLibEntity;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeResponse;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocNumSynchronizeResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.bean.DocProvideNumDTO;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.dao.QueryProvideNumDao;
import com.tapi.tcs.vc.webservice.provider.docNumSynchronize.service.QueryProvideNumService;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVersionInfoDTO;

public class QueryProvideNumServiceImpl implements QueryProvideNumService {
	private QueryProvideNumDao queryProvideNumDao;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public QueryProvideNumDao getQueryProvideNumDao() {
		return queryProvideNumDao;
	}

	public void setQueryProvideNumDao(QueryProvideNumDao queryProvideNumDao) {
		this.queryProvideNumDao = queryProvideNumDao;
	}

	@Override
	public DocNumSynchronizeResponse executeProvideNumInquiry(
			DocNumSynchronizeRequestDTO requestBody) throws BusinessException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();


		String msg = "";
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocNumSynchronizeResponse response = new DocNumSynchronizeResponse();
		DocNumSynchronizeResponseDTO responseBody=new DocNumSynchronizeResponseDTO();
		List<DocProvideNumDTO> DocProvideNumDTOs = new ArrayList<DocProvideNumDTO>();

		//单证类型代码
		String docVerCode = requestBody.getDocVerCode();
		//领用人代码
		String  takerCode=requestBody.getOpAndProxyId();	
		Date startDate=requestBody.getStartDate();
		//起止时间
		Date endDate=requestBody.getEndDate();
		if(startDate==null||startDate.equals("")||endDate==null ||endDate.equals("")){
			startDate=new Date();
			endDate=new Date();
		}
		java.sql.Date startDateFormat=new java.sql.Date(startDate.getTime());
		java.sql.Date endDateFormat=new java.sql.Date(endDate.getTime());
		List<VcDocTakerIo> vcDocTakerIoList =queryProvideNumDao.queryVcDocTakerIo(docVerCode, takerCode,startDateFormat,endDateFormat);
		long endTime = System.currentTimeMillis();
		logger.error("查询轨迹表的时间"+(endTime-startTime));
//		if (vcDocTakerIoList==null) {
//			msg+="此单证领用人："+takerCode+"三个月内无此单证类型："+docVerCode+"的发放记录";
//		}
		try {	
		if (vcDocTakerIoList.size()>0 && vcDocTakerIoList!=null) {
			long startTime5 = System.currentTimeMillis();
			//根据单证类型查询单证ID
			List<VcDocVersionInfo> vcDocVersionInfoList = queryProvideNumDao.queryVcDocVersionId(requestBody.getDocVerCode());
			long endTime2 = System.currentTimeMillis();
			for (VcDocTakerIo obj:vcDocTakerIoList) {
				long startTime1 = System.currentTimeMillis();
				// 单证发放轨迹表
				//VcDocTakerIo vcDocTakerIo = new VcDocTakerIo();
				DocProvideNumDTO docProvideNumDTO = new DocProvideNumDTO();				
				//单证类型代码
				docProvideNumDTO.setDocVerCode(obj.getDocVerCode());
				docProvideNumDTO.setSingleStartNum(obj.getStartNum());
				docProvideNumDTO.setSingleEndNum(obj.getEndNum());
				docProvideNumDTO.setProvideTime(obj.getOprTime());
				docProvideNumDTO.setOpAndProxyId(obj.getTakerCode());
				//VcDocVersionInfo docVersionInfo=new VcDocVersionInfo();
				docProvideNumDTO.setSingleTypeId(vcDocVersionInfoList.get(0).getIdVcDocVersionInfo());
				//根据单证类型代码和起止流水号查询可使用表内单证有效期
				String startNum = obj.getStartNum();
				String endNum = obj.getEndNum();
				long startTime3 = System.currentTimeMillis();
				List<VcAvailableDoc> vcAvailableDocList = queryProvideNumDao.queryVcDocDeadline(docVerCode,startNum,endNum);
				long endTime3 = System.currentTimeMillis();
				logger.error("查询可使用表的时间"+(endTime3-startTime3));
				VcAvailableDoc vcAvailableDoc = (vcAvailableDocList != null && vcAvailableDocList.size() >0) ? vcAvailableDocList.get(0) : null;
				docProvideNumDTO.setDeadLine( vcAvailableDoc == null ? null :   vcAvailableDoc.getDeadline());				
				DocProvideNumDTOs.add(docProvideNumDTO);	
				long endTime1 = System.currentTimeMillis();
				System.out.println("循环一次的时间"+(endTime1-startTime1));
				logger.error("循环一次的时间"+(endTime1-startTime1));
				}
			long startTime4 = System.currentTimeMillis();
			logger.error("循环全部的时间"+(startTime4-startTime5));
			int j=0;
			int size=DocProvideNumDTOs.size();
			
			while(j<size){
				responseHead.setResponseCode("000");
				responseHead.setResponseMessage("单证同步成功！");
				responseBody.setDocProvideNums(DocProvideNumDTOs);				
				response.setResponsebody(responseBody);
				response.setResponseHead(responseHead);	
				long endTime1 = System.currentTimeMillis();
				logger.error("查询放入DTO的时间"+(endTime1-endTime));
				logger.error("单证同步的时间"+(endTime1-startTime));
				return response;				
			}							
			long endTime4 = System.currentTimeMillis();
			logger.error("比较的时间"+(endTime4-startTime4));
			System.out.println("比较的时间"+(endTime4-startTime4));
		}
	}catch (Exception e) {
		e.printStackTrace();
		throw new BusinessException(e.getMessage());				
	}
	responseHead.setResponseCode("999");
	responseHead.setResponseMessage("查询失败、未查询到相关联的数据！");
	responseBody.setDocProvideNums(DocProvideNumDTOs);
	response.setResponsebody(responseBody);
	response.setResponseHead(responseHead);	
	long endTime1 = System.currentTimeMillis();
	logger.error("单证同步的时间"+(endTime1-startTime));
	return response;
	}
}

	


	
	

