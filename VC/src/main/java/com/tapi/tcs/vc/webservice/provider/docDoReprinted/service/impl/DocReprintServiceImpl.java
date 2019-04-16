package com.tapi.tcs.vc.webservice.provider.docDoReprinted.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerificationHis;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDetHis;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.bean.DocDoReprintedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.dao.DocReprintDao;
import com.tapi.tcs.vc.webservice.provider.docDoReprinted.service.DocReprintService;

public class DocReprintServiceImpl implements DocReprintService {
	private DocReprintDao docReprintDao; //单证重打接口DAO
	private VcDocStatusDao vcDocStatusDao; //单证状态查询公用方法DAO
	
	public DocReprintDao getDocReprintDao() {
		return docReprintDao;
	}

	public void setDocReprintDao(DocReprintDao docReprintDao) {
		this.docReprintDao = docReprintDao;
	}

	public VcDocStatusDao getVcDocStatusDao() {
		return vcDocStatusDao;
	}

	public void setVcDocStatusDao(VcDocStatusDao vcDocStatusDao) {
		this.vcDocStatusDao = vcDocStatusDao;
	}

	@Override
	public DocDoReprintedResponse executeDocRepirnt(DocDoReprintedRequest request) throws BusinessException{
		DocDoReprintedResponse response = new DocDoReprintedResponse();
		DocDoReprintedResponseDTO responseBody = new DocDoReprintedResponseDTO();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		
		String newDocVerCode = request.getRequestBody().getNewDocVerCode(); //新单证类型代码
		String newPressBatchNo = request.getRequestBody().getNewPressBatchNo();//新印刷批次
		if(StringUtils.isEmpty(newPressBatchNo)){
			newPressBatchNo = "000";
		}
		String newDocNum = request.getRequestBody().getNewDocNum(); //新单证流水号
		String operatorCode = request.getRequestBody().getOperatorCode(); //业务员代码
		String orgCode = request.getRequestBody().getOrgCode(); //业务归属部门代码
		String agentCode = request.getRequestBody().getAgentCode(); //中介机构代码
		//add by whj 20130917
        agentCode=null;
        
        //modify by zhxiao3 20140716:VC-120  begin
        VcAvailableDoc vcAvailableDoc = null;
        //modify by zhxiao3 20140716:VC-120  end
		try {
			// 判断流水号(单证)是否可用
			List<VcAvailableDoc> vcAvailableDocList = vcDocStatusDao.checkDocNumIsValid(newDocNum, newDocVerCode, operatorCode, orgCode, newPressBatchNo,agentCode);
			if(vcAvailableDocList!=null && vcAvailableDocList.size()<1) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage("重打失败，单证流水号不可用！");
				responseBody.setResultCode("1");
				responseBody.setResultMessage("重打失败，单证流水号不可用！");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			}
			vcAvailableDoc = vcAvailableDocList.get(0);
		} catch (DaoException de) {
			throw new BusinessException(de.getMessage(), de);
		}
		
		List<VcNormalVerification> list = null;
		
		try {
			list = docReprintDao.checkIsVerificated(request);
			Date nowTime = new Date();
			if(list == null || list.size()<1) {
				responseHead.setResponseCode("999");
				responseHead.setResponseMessage("重打失败，保单的历史核销记录不存在！");
				responseBody.setResultCode("1");
				responseBody.setResultMessage("重打失败，保单的历史核销记录不存在！");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
				return response;
			} else {

				VcNormalVerification vcNormalVerification = list.get(0);
				// 作废旧单证流水号
				VcAbnormalVerification vcAbnormalVerification = this.generateAbnormalVerification(request, vcNormalVerification);
				
				//转储旧单证核销记录
				VcNormalVerificationHis vcNormalVerificationHis = this.generateVcNormalVerificationHis(request, vcNormalVerification);
				
				// 记录新单证核销信息
				VcNormalVerification vcNormalVerificationNew = this.generateNormalVerifiedInfo(request);
				
				//docReprintDao.saveNormalVerification(vcNormalVerification);
				//删除旧单号的核销记录
				docReprintDao.delete(vcNormalVerification);
				//保存历史表
				docReprintDao.save(vcNormalVerificationHis);
				//保存旧单号的作废记录
				docReprintDao.save(vcAbnormalVerification);
				//保存新的核销记录
				docReprintDao.save(vcNormalVerificationNew);
				//modify by zhxiao3 20140716:VC-120  begin
				//删除可使用单证
				docReprintDao.delete(vcAvailableDoc);
				//modify by zhxiao3 20140716:VC-120  end
				responseHead.setResponseCode("000");
				responseHead.setResponseMessage("重打成功！");
				responseBody.setResultCode("0");
				responseBody.setResultMessage("重打成功！");
				response.setResponseHead(responseHead);
				response.setResponseBody(responseBody);
			}
		} catch (DaoException de) {
			throw new BusinessException(de.getMessage(), de);
		}
		return response;
	}
	
	/**
	 * 作废旧单证流水号
	 * @param request
	 * @param vcNormalVerificationHistory
	 * @return
	 * @throws BusinessException
	 */
	public VcAbnormalVerification generateAbnormalVerification(DocDoReprintedRequest request,VcNormalVerification vcNormalVerificationHistory) throws BusinessException {
		VcAbnormalVerification vcAbnormalVerification = new VcAbnormalVerification();
		vcAbnormalVerification.setDocVerCode(request.getRequestBody().getOldDocVerCode());
		String oldPressBatchNo = request.getRequestBody().getOldPressBatchNo();
		if(StringUtils.isEmpty(oldPressBatchNo)){
			oldPressBatchNo = "000";
		}
		vcAbnormalVerification.setPressBatchNo(oldPressBatchNo);
		vcAbnormalVerification.setDocNum(request.getRequestBody().getOldDocNum());
		vcAbnormalVerification.setBusinessNo(request.getRequestBody().getBusinessNo());
		// 如果正常核销日期跟非正常核销日期在同一个月份，为C2，如不在同一个月份为C3
		Date nowTime = new Date();
		Date nVerificationDate = vcNormalVerificationHistory.getVerifiedTime();
		int months = DateUtils.getMonthDifference(nVerificationDate, nowTime);
		String docStatus = "C3";
		if (months == 0) {
			docStatus = "C2";
		}
		vcAbnormalVerification.setDocStatus(docStatus);
		String operatorCode = "";
		if(StringUtils.isNotBlank(request.getRequestBody().getOperatorCode())) {
			operatorCode = request.getRequestBody().getOperatorCode();
		} else {
			//作废时，如果未传操作员代码，作废人填写操作员所属部门代码
			operatorCode = request.getRequestBody().getOrgCode();
		}
		vcAbnormalVerification.setVerifiedOprCode(operatorCode);
		vcAbnormalVerification.setVerifiedOrgCode(request.getRequestBody().getOrgCode());
		vcAbnormalVerification.setVerifiedTime(nowTime);
		vcAbnormalVerification.setType("C");
		vcAbnormalVerification.setCreatedBy(operatorCode);
		vcAbnormalVerification.setDateCreated(nowTime);
		vcAbnormalVerification.setUpdatedBy(operatorCode);
		vcAbnormalVerification.setDateUpdated(nowTime);	
		
		//add by chy 20130815 增加明细表的保存 begin
		VcAbnormalVerifiedDet vcAbnormalVerifiedDet = new VcAbnormalVerifiedDet();
		vcAbnormalVerifiedDet.setBusinessNo(request.getRequestBody().getBusinessNo());
		vcAbnormalVerifiedDet.setRelBusinessNo("");
		vcAbnormalVerifiedDet.setPayNo("1");
		vcAbnormalVerifiedDet.setCreatedBy(operatorCode);
		vcAbnormalVerifiedDet.setDateCreated(nowTime);
		vcAbnormalVerifiedDet.setUpdatedBy(operatorCode);
		vcAbnormalVerifiedDet.setDateUpdated(nowTime);	
		vcAbnormalVerifiedDet.setVcAbnormalVerification(vcAbnormalVerification);
		List<VcAbnormalVerifiedDet> list = new ArrayList<VcAbnormalVerifiedDet>();
		list.add(vcAbnormalVerifiedDet);
		vcAbnormalVerification.setVcAbnormalVerifiedDetList(list);
		//add by chy 20130815 增加明细表的保存 end
		
		return vcAbnormalVerification;
	}
	

	
	
	private VcNormalVerification generateNormalVerifiedInfo(DocDoReprintedRequest request) {
		//单证正常核销
		VcNormalVerification vcNormalVerification = generateVcNormalVerification(request);
		//单证正常核销明细
		List<VcNormalVerifiedDet> vcNormalVerifiedDetList = generateVcNormalVerifiedDet(request);
		for(VcNormalVerifiedDet normalVerifiedDetList : vcNormalVerifiedDetList ) {
			normalVerifiedDetList.setVcNormalVerification(vcNormalVerification);
		}
		vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
		
		return vcNormalVerification;
	}
	
	/**
	 * 核销新单证流水号
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	public VcNormalVerification generateVcNormalVerification(DocDoReprintedRequest request) {
		VcNormalVerification vcNormalVerification = new VcNormalVerification();
		vcNormalVerification.setDocVerCode(request.getRequestBody().getNewDocVerCode());
		vcNormalVerification.setDocNum(request.getRequestBody().getNewDocNum());
		String newPressBatchNo = request.getRequestBody().getNewPressBatchNo();
		if(StringUtils.isEmpty(newPressBatchNo)){
			newPressBatchNo = "000";
		}
		vcNormalVerification.setPressBatchNo(newPressBatchNo);
		vcNormalVerification.setBusinessNo(request.getRequestBody().getBusinessNo());
		vcNormalVerification.setDocStatus("U1");
		String operatorCode = "";
		if(StringUtils.isNotBlank(request.getRequestBody().getOperatorCode())) {
			operatorCode = request.getRequestBody().getOperatorCode();
		} else {
			//作废时，如果未传操作员代码，作废人填写操作员所属部门代码
		    //modify by whj 20130917
			//operatorCode = request.getRequestBody().getAgentCode();
			operatorCode = request.getRequestBody().getOrgCode();
		}
		vcNormalVerification.setVerifiedOprCode(operatorCode);
		vcNormalVerification.setVerifiedOrgCode(request.getRequestBody().getOrgCode());
		Date nowTime = new Date();
		vcNormalVerification.setVerifiedReason("4");//4-重打核销
		vcNormalVerification.setVerifiedTime(nowTime);
		vcNormalVerification.setCreatedBy(operatorCode);
		vcNormalVerification.setDateCreated(nowTime);
		vcNormalVerification.setUpdatedBy(operatorCode);
		vcNormalVerification.setDateUpdated(nowTime);
		
		return vcNormalVerification;
	}
	
	/**
	 * 核销新单证流水号
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	public List<VcNormalVerifiedDet> generateVcNormalVerifiedDet(DocDoReprintedRequest request) {
		List<VcNormalVerifiedDet> list = new ArrayList<VcNormalVerifiedDet>();
		VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet();
		
		vcNormalVerifiedDet.setBusinessNo(request.getRequestBody().getBusinessNo());
		vcNormalVerifiedDet.setRelBusinessNo("");
		vcNormalVerifiedDet.setPayNo("1");
		String operatorCode = "";
		if(StringUtils.isNotBlank(request.getRequestBody().getOperatorCode())) {
			operatorCode = request.getRequestBody().getOperatorCode();
		} else {
			//作废时，如果未传操作员代码，作废人填写操作员所属部门代码
		    //modify by whj 20130917
			//operatorCode = request.getRequestBody().getAgentCode();
			operatorCode = request.getRequestBody().getOrgCode();
		}
		Date nowTime = new Date();
		vcNormalVerifiedDet.setCreatedBy(operatorCode);
		vcNormalVerifiedDet.setDateCreated(nowTime);
		vcNormalVerifiedDet.setUpdatedBy(operatorCode);
		vcNormalVerifiedDet.setDateUpdated(nowTime);
		list.add(vcNormalVerifiedDet);
		
		return list;
	}
	
	/**
	 * 转储历史表
	 * @param vcNormalVerification
	 * @return
	 */
	public VcNormalVerificationHis generateVcNormalVerificationHis(DocDoReprintedRequest request,
			VcNormalVerification vcNormalVerification){
		VcNormalVerificationHis vcNormalVerificationHis = new VcNormalVerificationHis();
		String operatorCode = "";
		if(StringUtils.isNotBlank(request.getRequestBody().getOperatorCode())) {
			operatorCode = request.getRequestBody().getOperatorCode();
		} else {
			//作废时，如果未传操作员代码，作废人填写操作员所属部门代码
		  //modify by whj 20130917
			//operatorCode = request.getRequestBody().getAgentCode();
			operatorCode = request.getRequestBody().getOrgCode();
		}
		Date nowTime = new Date();
		//拷贝主表
		Beans.copy(vcNormalVerificationHis, vcNormalVerification);
		vcNormalVerificationHis.setCreatedBy(operatorCode);
		vcNormalVerificationHis.setDateCreated(nowTime);
		vcNormalVerificationHis.setUpdatedBy(operatorCode);
		vcNormalVerificationHis.setDateUpdated(nowTime);
		if(StringUtils.isBlank(vcNormalVerificationHis.getUseType())){
			vcNormalVerificationHis.setUseType("1");//正常核销表暂无此字段，先付默认值
		}
		//拷贝子表
		List<VcNormalVerifiedDet> vcNormalVerifiedDetlist = vcNormalVerification.getVcNormalVerifiedDet();
		if(vcNormalVerifiedDetlist!=null && vcNormalVerifiedDetlist.size()>0){
			List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHisList = new ArrayList<VcNormalVerifiedDetHis>();
			for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerifiedDetlist){
				VcNormalVerifiedDetHis vcNormalVerifiedDetHis = new VcNormalVerifiedDetHis();
				Beans.copy(vcNormalVerifiedDetHis, vcNormalVerifiedDet);
				vcNormalVerifiedDetHis.setCreatedBy(operatorCode);
				vcNormalVerifiedDetHis.setDateCreated(nowTime);
				vcNormalVerifiedDetHis.setUpdatedBy(operatorCode);
				vcNormalVerifiedDetHis.setDateUpdated(nowTime);
				vcNormalVerifiedDetHis.setCreatedBy(operatorCode);
				vcNormalVerifiedDetHis.setDateCreated(nowTime);
				vcNormalVerifiedDetHis.setUpdatedBy(operatorCode);
				vcNormalVerifiedDetHis.setDateUpdated(nowTime);
				vcNormalVerifiedDetHis.setVcNormalVerificationHis(vcNormalVerificationHis);
				vcNormalVerifiedDetHisList.add(vcNormalVerifiedDetHis);
			}
			vcNormalVerificationHis.setVcNormalVerifiedDetHis(vcNormalVerifiedDetHisList);
		}
		return vcNormalVerificationHis;
	}
}
