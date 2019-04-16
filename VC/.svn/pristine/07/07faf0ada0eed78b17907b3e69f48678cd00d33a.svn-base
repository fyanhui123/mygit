package com.tapi.tcs.vc.webservice.provider.cancelDoReversed.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedDocNumDTO;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedResponse;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.bean.CancelReversedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.dao.CancelReversedDao;
import com.tapi.tcs.vc.webservice.provider.cancelDoReversed.service.CancelReversedService;

public class CancelReversedServiceImpl implements CancelReversedService {
	private CancelReversedDao cancelReversedDao;


	@Override
	public CancelReversedResponse executeCancelReversed(CancelReversedRequestDTO requestBody) throws BusinessException {
		
		String msg = "";
		//单证类型代码
		String docVerCode = requestBody.getDocVerCode();
		//印刷批次
		String pressBatchNo = requestBody.getPressBatchNo();
		//操作员归属机构代码
		String orgCode = requestBody.getOrgCode();
		//中介机构代码
		//String agentCode = requestBody.getAgentCode();
		
		String errDocNum = "";
		
		//撤单单证流水号列表
	    List<CancelReversedDocNumDTO>  reverseList = requestBody.getCancelReversedDocNumDTOList();
		
		for (CancelReversedDocNumDTO cancelReversedDocNumDTO : reverseList) {
			//单证流水号
			String docNum = cancelReversedDocNumDTO.getDocNum();
	        //业务号
			String businessNo = cancelReversedDocNumDTO.getBussinessNo();
			//查询非正常核销记录
			List<VcAbnormalVerification> vcAbnormalVerificationList = cancelReversedDao.findVcAbnormalVerification(docVerCode, docNum, businessNo,pressBatchNo);
			if (vcAbnormalVerificationList == null) {
				
				msg += "单证号：" + docNum + "无非正常核销记录" ;
				if (StringUtils.isBlank(errDocNum)) {
					errDocNum = docNum;
				}
			}else{
				VcNormalVerification vcNormalVerification = new VcNormalVerification();
				VcAbnormalVerification vcAbnormalVerification = vcAbnormalVerificationList.get(0);
				BeanUtils.copyProperties(vcAbnormalVerification, vcNormalVerification);
				vcNormalVerification.setVerifiedOrgCode(orgCode);
				vcNormalVerification.setDocStatus("U1");
				vcNormalVerification.setDateCreated(new Date());
				vcNormalVerification.setDateUpdated(new Date());
				vcNormalVerification.setVerifiedReason("5");//5-撤单冲正恢复核销
				
				List<VcAbnormalVerifiedDet> vcAbnormalDetList = vcAbnormalVerification.getVcAbnormalVerifiedDetList();
				List<VcNormalVerifiedDet> vcNormalDetList = new ArrayList<VcNormalVerifiedDet>();
				
				for (VcAbnormalVerifiedDet vcAbnormalVerifiedDet : vcAbnormalDetList) {
					VcNormalVerifiedDet vcNormalDet = new VcNormalVerifiedDet();
					BeanUtils.copyProperties(vcAbnormalVerifiedDet, vcNormalDet);
					//vcNormalDet.setCounteractFlag("0");
					vcNormalDet.setDateCreated(new Date());
					vcNormalDet.setDateUpdated(new Date());
					vcNormalDetList.add(vcNormalDet);
				}
				vcNormalVerification.setVcNormalVerifiedDet(vcNormalDetList);
				for (VcNormalVerifiedDet vcNormalVerifiedDet : vcNormalDetList) {
					vcNormalVerifiedDet.setVcNormalVerification(vcNormalVerification);
				}
				
				
				cancelReversedDao.saveNormalVerification(vcNormalVerification);	
				cancelReversedDao.deleteAbnormalVerification(vcAbnormalVerification);

			}
		}
		
//		responseHead
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		CancelReversedResponseDTO responseBody = new CancelReversedResponseDTO();
		CancelReversedResponse response = new CancelReversedResponse();
		if (StringUtils.isBlank(msg)) {
			responseHead.setResponseCode("000");
			responseHead.setResponseMessage("撤单冲正成功");
			responseBody.setCancelReverseStatus("0");
			responseBody.setDocVerCode(docVerCode);
			responseBody.setDocNum(errDocNum);
		}else {
			responseHead.setResponseCode("999");
			responseHead.setResponseMessage("失败"+ msg) ;
		}
		
		response.setResponseBody(responseBody);
		response.setResponseHead(responseHead);
		

		return response;
	}

	
	public CancelReversedDao getCancelReversedDao() {
		return cancelReversedDao;
	}

	public void setCancelReversedDao(CancelReversedDao cancelReversedDao) {
		this.cancelReversedDao = cancelReversedDao;
	}

}
