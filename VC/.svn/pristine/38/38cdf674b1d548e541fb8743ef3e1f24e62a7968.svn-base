package com.tapi.tcs.vc.webservice.provider.docDoReversed.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcTaker;
import com.tapi.tcs.vc.store.service.ProvideService;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedDocNumDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.bean.DocDoReversedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.dao.DocReversedDao;
import com.tapi.tcs.vc.webservice.provider.docDoReversed.service.DocReversedService;

public class DocReversedServiceImpl implements DocReversedService {

	private DocReversedDao docReversedDao;
	private ProvideService provideService;
	
	@Override
	public DocDoReversedResponse executeDocDoReversed(DocDoReversedRequestDTO requestBody) throws BusinessException{
		DocDoReversedResponse response = new DocDoReversedResponse();
		Date nowdate = new Date();
		try{
			//单证类型代码
			String docVerCode = requestBody.getDocVerCode();
			//操作员归属机构代码
			String orgCode = requestBody.getOrgCode();
			//中介机构代码
			String agentCode = requestBody.getAgentCode();
			//印刷批次
			String pressBatchNo = requestBody.getPressBatchNo();
			if(StringUtils.isEmpty(pressBatchNo)){
				pressBatchNo = "000";
			}
			//单证流水号列表
			List<DocDoReversedDocNumDTO> docNumList = requestBody.getDocDoReversedDocNumDTOList();
			//正常核销记录列表
			List<VcNormalVerification> vcNormalVerificationList = new ArrayList<VcNormalVerification>();
			//可使用列表
			List<VcAvailableDoc> vcAvailableDocList = new ArrayList<VcAvailableDoc>();
            
            //查询使用人
            String takerCode = "";
            List<VcTaker> takerList = docReversedDao.findVcTakerListByOrgCode(orgCode);
            if(takerList==null || takerList.size()==0){
            	ResponseHeadDTO responseHead = new ResponseHeadDTO();
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage("该机构下没有查询到使用人！");
				DocDoReversedResponseDTO responseBody = new DocDoReversedResponseDTO();
				responseBody.setDocDoReverseStatus("1");
				responseBody.setDocVerCode(docVerCode);
				responseBody.setDocNum(docNumList.get(0).getDocNum());
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
            }else{
            	takerCode = takerList.get(0).getTakerCode();
            }
            
          //查询最长库存时间
            int maxStoreTime = provideService.getMaxStoreTime(docVerCode, "1", takerCode); // 查询数据库得到最长库存时间
            Date calcDeadline = DateUtils.addDay(nowdate, maxStoreTime);
            
			for(DocDoReversedDocNumDTO docNumDto : docNumList){
				//正常核销记录
				VcNormalVerification vcNormalVerification = null;
				//流水号
				String docNum = docNumDto.getDocNum();
				//业务号列表
				List<DocDoReversedBusinessNoDTO> businessNoDtoList = docNumDto.getDocDoReversedBusinessNoDTOList();
				for(DocDoReversedBusinessNoDTO businessNoDto : businessNoDtoList){
					//业务号
					String businessNo = businessNoDto.getBusinessNo();
					//查询正常核销记录
					vcNormalVerification = docReversedDao
							.findVcNormalVerification(docVerCode, pressBatchNo, docNum, orgCode, businessNo);
					if(vcNormalVerification==null){
						ResponseHeadDTO responseHead = new ResponseHeadDTO();
						responseHead.setResponseCode("999");
						responseHead.setResponseMessage("未找到正常核销记录！");
						DocDoReversedResponseDTO resonseBody = new DocDoReversedResponseDTO();
						resonseBody.setDocDoReverseStatus("1");
						resonseBody.setDocVerCode(docVerCode);
						resonseBody.setDocNum(docNum);
						response.setResponseHead(responseHead);
						response.setResponseBody(resonseBody);
						return response;
					}
				}
				//添加需删除的正常核销记录
				vcNormalVerificationList.add(vcNormalVerification);
				//组织需新增的可使用单证
				VcAvailableDoc vcAvailableDoc = new VcAvailableDoc();
				vcAvailableDoc.setDocVerCode(docVerCode);
				vcAvailableDoc.setDocNum(docNum);
				vcAvailableDoc.setPressBatchNo(pressBatchNo);
				vcAvailableDoc.setDocStatus("A");
				vcAvailableDoc.setTakerCode(takerCode);
				vcAvailableDoc.setOrgCode(orgCode);
				vcAvailableDoc.setProvideTime(nowdate);
				vcAvailableDoc.setDeadline(calcDeadline);
				vcAvailableDoc.setCreatedBy(orgCode);
				vcAvailableDoc.setDateCreated(nowdate);
				vcAvailableDoc.setUpdatedBy(orgCode);
				vcAvailableDoc.setDateUpdated(nowdate);
				if(StringUtils.isNotEmpty(agentCode)){
					vcAvailableDoc.setAgentCode(agentCode);
				}
				vcAvailableDocList.add(vcAvailableDoc);
			}
			//删除正常核销记录
			docReversedDao.deleteAll(vcNormalVerificationList);
			//保存可使用表
			docReversedDao.saveAll(vcAvailableDocList);
			
			ResponseHeadDTO responseHead = new ResponseHeadDTO();
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("冲正成功！");
			DocDoReversedResponseDTO responseBody = new DocDoReversedResponseDTO();
			responseBody.setDocDoReverseStatus("0");
			responseBody.setDocVerCode(docVerCode);
			response.setResponseHead(responseHead);
			response.setResponseBody(responseBody);
		}catch(BusinessException e){
			throw e;
		}catch(Exception e){
			throw new BusinessException(e.getMessage(), e);
		}
		return response;
	}

	public void setDocReversedDao(DocReversedDao docReversedDao) {
		this.docReversedDao = docReversedDao;
	}

	public void setProvideService(ProvideService provideService) {
		this.provideService = provideService;
	}
}
