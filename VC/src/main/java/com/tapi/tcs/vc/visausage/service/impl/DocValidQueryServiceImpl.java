package com.tapi.tcs.vc.visausage.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.store.dao.VcAvailableDocDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.visausage.service.DocValidQueryService;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponse;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.ResultInfo;

public class DocValidQueryServiceImpl implements DocValidQueryService {

	private NormalVerificationDao normalVerificationDao;
	private VcAvailableDocDao vcAvailableDocDao;

	@Override
	public DocValidInquiryResponse docValidInquiry(DocValidInquiryRequestDTO body) throws BusinessException {

		DocValidInquiryResponse docValidInquiryResponse = new DocValidInquiryResponse();
		try{
			ResponseHeadDTO responseHead = new ResponseHeadDTO();
			DocValidInquiryResponseDTO responseBody = new DocValidInquiryResponseDTO();
	
			String docVerCode = body.getDocVerCode();
			String businessNo = body.getBusinessNo();
	
			// 根据输入的版本号和业务号来判断业务号是否已经核销
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("docVerCode", docVerCode);
			queryRule.addEqual("businessNo", businessNo);
	
			List<VcNormalVerification> vcNormalVerifications = normalVerificationDao.find(queryRule);
			int size = vcNormalVerifications.size();
			if (size > 0) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage("此业务号已经核销");
				List<ResultInfo> resultInfos = new ArrayList<ResultInfo>();
				// 业务号已核销
				for (VcNormalVerification vcNormalVerification : vcNormalVerifications) {
					String docNum = vcNormalVerification.getDocNum();
					ResultInfo resultInfo = new ResultInfo();
					resultInfo.setDocBeginSerialNo(docNum);
					resultInfo.setDocEndSerialNo(docNum);
					resultInfo.setBusinessNo(businessNo);
					resultInfos.add(resultInfo);
				}
				// 1：业务号已核销
				responseBody.setStatus("1");
				responseBody.setResultInfo(resultInfos);
				docValidInquiryResponse.setResponseHead(responseHead);
				docValidInquiryResponse.setResponseBody(responseBody);
				return docValidInquiryResponse;
	
			} else {
				
				//场景 0：单证打印核销  1：无线出单 2：单证正本打印销号 (1 2暂未知)
			
				// 判断流水号是否可用 流水号为纯数字 BigDecimal ?
				Long startNum = Long.valueOf(body.getDocBeginSerialNo());
				Long endNum = Long.valueOf(body.getDocEndSerialNo());
				List<ResultInfo> resultInfos = new ArrayList<ResultInfo>();
				
				for (long i = startNum; i < endNum + 1; i++) {
					queryRule = QueryRule.getInstance();
					queryRule.addEqual("docVerCode", docVerCode);
					queryRule.addEqual("docNum", i + "");
					queryRule.addEqual("docStatus", "A");
					queryRule.addEqual("takerCode", body.getOperator());
					queryRule.addEqual("orgCode", body.getComCode());
					VcAvailableDoc vcAvailableDoc = vcAvailableDocDao.findUnique(queryRule);
	
					if (vcAvailableDoc == null) {
						// 未找到可可用流水号
						ResultInfo resultInfo = new ResultInfo();
						resultInfo.setBusinessNo(businessNo);
						resultInfo.setDocBeginSerialNo(i + "");
						resultInfo.setDocEndSerialNo(i + "");
						resultInfos.add(resultInfo);
					}
				}
				if (resultInfos.size() > 0) {
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("流水号不可用");
	
					// 2：流水号不可用
					responseBody.setStatus("2");
					responseBody.setResultInfo(resultInfos);
	
					docValidInquiryResponse.setResponseHead(responseHead);
					docValidInquiryResponse.setResponseBody(responseBody);
					return docValidInquiryResponse;
				} else {
					responseHead.setResponseCode("000");
					responseHead.setResponseMessage("单证可用");
	
					// 0：成功
					responseBody.setStatus("0");
	
					docValidInquiryResponse.setResponseHead(responseHead);
					docValidInquiryResponse.setResponseBody(responseBody);
				}
			}
		}catch(Exception e){
			throw new BusinessException("查询数据失败！", e);
		}
		return docValidInquiryResponse;
	}

	public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}

	public void setVcAvailableDocDao(VcAvailableDocDao vcAvailableDocDao) {
		this.vcAvailableDocDao = vcAvailableDocDao;
	}
}
