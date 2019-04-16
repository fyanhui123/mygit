package com.tapi.tcs.vc.webservice.provider.docDoUsed.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.RequestHeadDTO;
import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedBusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DoUsedDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponse;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.bean.DocDoUsedResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.dao.DocDoUsedDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.service.DocDoUsedQueryService;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;

public class DocDoUsedQueryServiceImpl implements DocDoUsedQueryService {
	/***************************************************************************
	 * 新核心系统 单证核销接口
	 * 
	 * @param request
	 * @return
	 */
	private DocDoUsedDao docDoUsedDao;
	/** 单证状态查询公用方法DAO */
	private VcDocStatusDao vcDocStatusDao;
	/** 单证核销接口DAO */
	private DocExecuteUsedDao docExecuteUsedDao;
	private VcBaseInfoDao vcBaseInfoDao;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public DocDoUsedResponse executeDocDoUsedHandle(DocDoUsedRequest request)
			throws BusinessException {
	    RequestHeadDTO requestHead = request.getRequestHead();
	    DocDoUsedRequestDTO body=request.getRequestBody();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoUsedResponseDTO responseBody = new DocDoUsedResponseDTO();
		DocDoUsedResponse response = new DocDoUsedResponse();
		// 单证种类代码
		String docTypeCode = body.getDocTypeCode();
		// 单证类型代码
		String docVerCode = body.getDocVerCode();
		// 操作员代码
		String operatorCode = body.getOperatorCode();
		// 操作员归属机构代码
		String orgCode = body.getOrgCode();
		// 印刷批次
		String pressBatchNo = body.getPressBatchNo();
		// 中介机构代码
		String agentCode = body.getAgentCode();
		//add by whj 20130917
        agentCode=null;
        
        //系统代码
        String sysCode=requestHead.getSystemCode();
        

		try {
			// 根据单证类型查询关联单证种类信息
			VcDocType vcDocType = vcBaseInfoDao.getVcDocType(body.getDocVerCode()).get(0);
			String docType = vcDocType.getDocType();

			// 核销单证类型为财务类，印刷批次不能为空
			if (docType.equals(SysConst.DOC_TYPE_FINANCE) && StringUtils.isEmpty(pressBatchNo)) {
				throw new BusinessException("印刷批次不能为空！");
			}
			// 业务单证默认是000
			if (StringUtils.isBlank(pressBatchNo)) {
				pressBatchNo = "000";
			}
			// 单证流水号列表(单证流水号/业务号列表)
			List<DoUsedDocNumInfoDTO> docNumInfoDTOs = body.getDocNumInfoDTOs();
			// 装载实体、用于保存核销表
			List<VcNormalVerification> saveList = new ArrayList<VcNormalVerification>();
			// 装载实体、用于删除可使用单证表
			List<VcAvailableDoc> delList = new ArrayList<VcAvailableDoc>();
			// 1 for start
			for (DoUsedDocNumInfoDTO doUsedDocNumInfoDTO : docNumInfoDTOs) {
				// 单证流水号
				String docNum = doUsedDocNumInfoDTO.getDocNum();
				
				//add by chyi 20130810  begin
				// 判断流水号(单证)是否可用
				String tempOpreator=operatorCode;
				if (SysConst.EXTERNAL_SYSTEM_CODE.indexOf(sysCode)>-1){
		            //外部系统
				    tempOpreator=null;
		        }
				List<VcAvailableDoc> vcAvailableDocList = vcDocStatusDao
						.checkDocNumIsValid(docNum, docVerCode, tempOpreator,
								orgCode, pressBatchNo, agentCode);
				//add by chyi 20130810  end
				// 业务号列表(业务号/保单业务号)
				List<DoUsedBusinessNoDTO> businessNoDTOs = doUsedDocNumInfoDTO.getBusinessNoDTOs();
				// 核销明细列表 add by chy
				List<VcNormalVerifiedDet> vcNormalVerifiedDetList = new ArrayList<VcNormalVerifiedDet>();
				for (DoUsedBusinessNoDTO doUsedBusinessNoDTO : businessNoDTOs) {
					// 业务号(保单号、批单号)
					String businessNo = doUsedBusinessNoDTO.getBusinessNo();
					// 批单对应的保单业务号
					String relationBusinessNo = doUsedBusinessNoDTO.getRelationBusinessNo();
					//add by chy 20131017 新增缴费期次、红冲标志、拆分标志字段 begin
					//缴费期次
					String payNo = doUsedBusinessNoDTO.getPayNo();
					if(StringUtils.isEmpty(payNo)){
						payNo = "1";//默认等于1
					}
					//红冲标志:0-非红冲；1-红冲负数；2-红冲正数
					String counteractFlag = doUsedBusinessNoDTO.getCounteractFlag();
					if(StringUtils.isEmpty(counteractFlag)){
						counteractFlag = "0";
					}
					//拆分批次
					String batchNo = doUsedBusinessNoDTO.getBatchNo();
					//add by chy 20131017 新增缴费期次、红冲标志、拆分标志字段 end
					// 若单证类型的种类为批单，批单关联的保单业务号不可为空
					if (StringUtils.isEmpty(relationBusinessNo) && docTypeCode.equals(SysConst.DOC_TYPE_CODE_ENDORSEMENT)){
						throw new BusinessException("批单关联保单号不能为空！");
					}

					// 流水号截取8位
					// String docNum=docNumString.substring(docNumString.length()-8);
					// 判断是否已经核销
					//modify by chy 20131017 begin
					/*List<Object[]> vcNormalVerifications = docDoUsedDao.checkVcNormal(businessNo, docVerCode);*/
					List<Object[]> vcNormalVerifications = vcDocStatusDao.checkBizNoIsUsed(businessNo,
							payNo, docVerCode, counteractFlag, batchNo,"");
					//modify by chy 20131017 end
					//List<Object[]> vcNormalVerifications = docDoUsedDao.queryVcNormal(businessNo, docVerCode, "U1");
					if (vcNormalVerifications!=null && vcNormalVerifications.size() > 0) {
						Object[] obj = vcNormalVerifications.get(0);
						// 已核销更新数据
						VcNormalVerification vcNormal = (VcNormalVerification) obj[0];

						// 已核销（同一个流水号）
						//modify by chy 20131017 判断流水号是同一个需加上印刷批次和单证类型 begin
						//if (vcNormal.getDocNum().equals(docNum)) {
						if (docVerCode.equals(vcNormal.getDocVerCode())
								&&pressBatchNo.equals(vcNormal.getPressBatchNo())
								&&docNum.equals(vcNormal.getDocNum())) {
						//modify by chy 20131017 判断流水号是同一个需加上印刷批次和单证类型 end
							responseHead.setResponseCode("000");
							responseHead.setResponseMessage("核销成功，业务号已经核销！");
							responseBody.setStatus("0");
							//responseBody.setBusinessNo(vcNormal.getBusinessNo());
							responseBody.setBusinessNo(businessNo);
							responseBody.setDocVerCode(vcNormal.getDocVerCode());
							responseBody.setDocNum(vcNormal.getDocNum()); // 核销过的流水号
							response.setResponseHead(responseHead);
							response.setResponseBody(responseBody);
						} else {
							//业务号已核销且流水号不同，返回已核销的流水号并给出提示信息，是否作废
							responseHead.setResponseCode("999");
							responseHead.setResponseMessage("核销失败，业务号已存在，是否作废！");
							responseBody.setStatus("2");
							//responseBody.setBusinessNo(vcNormal.getBusinessNo());
							responseBody.setBusinessNo(businessNo);
							responseBody.setDocVerCode(vcNormal.getDocVerCode());
							// 核销的业务号，关联的流水号
							responseBody.setDocNum(vcNormal.getDocNum()); 
							//核销的业务号，关联的流水号的批次号
							responseBody.setPressBatchNo(vcNormal.getPressBatchNo());
							response.setResponseHead(responseHead);
							response.setResponseBody(responseBody);
							return response;
						}

						// 核销时间在同一个月
						Date nowDate = new Date();
						Date verifiedTime = vcNormal.getVerifiedTime();
						int months = DateUtils.getMonthDifference(verifiedTime, nowDate);
						String docStatus = "U3";
						if (months == 0) {
							docStatus = "U1";
						}
						//modify by zhxiao3 2013-1-9 核销时间分区，跨年 重打报错 begin
						//vcNormal.setVerifiedTime(nowDate);
						//modify by zhxiao3 2013-1-9 核销时间分区，跨年 重打报错 end
						vcNormal.setDocStatus(docStatus);
						if (!StringUtils.isEmpty(operatorCode)) {
							// 承保、收付系统操作人不为空
							vcNormal.setUpdatedBy(operatorCode);
						} else {
							// 外部接口操作人可为空，保存为机构数据
							vcNormal.setUpdatedBy(orgCode);
						}
						vcNormal.setDateUpdated(nowDate);
						docDoUsedDao.updateVcNormalVerificationList(vcNormal);
						/*
						 * String docStatus = "U1"; for(VcNormalVerification
						 * vc:vcNormalVerifications){
						 * vc.setVerifiedTime(nowDate);
						 * vc.setDocStatus(docStatus);
						 * docDoUsedDao.updateVcNormalVerificationList(vc); }
						 */
						return response;
					}
					// 若流水号不可用，返回错误提示信息
					if (vcAvailableDocList!=null && vcAvailableDocList.size() < 1) {
						responseHead.setResponseCode("999");
						responseHead.setResponseMessage("核销失败，单证流水号不可用！");
						responseBody.setStatus("1");
						responseBody.setDocNum(docNum);
						response.setResponseHead(responseHead);
						response.setResponseBody(responseBody);
						return response;
					}
					//add by chy 20130810 增加核销明细表的保存 begin
					VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet();
					vcNormalVerifiedDet.setBusinessNo(businessNo);
					if (!StringUtils.isEmpty(relationBusinessNo)){
						vcNormalVerifiedDet.setRelBusinessNo(relationBusinessNo);
					}
					//add b chy 20131017 增加缴费期次、红冲标志、拆分标志字段的保存 begin
					//vcNormalVerifiedDet.setPayNo("1");//缴费期次默认1
					vcNormalVerifiedDet.setPayNo(payNo);
					vcNormalVerifiedDet.setCounteractFlag(counteractFlag);//红冲标志
					vcNormalVerifiedDet.setBatchNo(batchNo);//拆分批次
					//add b chy 20131017 增加缴费期次、红冲标志、拆分标志字段的保存 end
					if (!StringUtils.isEmpty(operatorCode)) {
						// 承保、收付系统操作人不为空
						vcNormalVerifiedDet.setCreatedBy(operatorCode);
					} else {
						// 外部接口操作人可为空，创建人保存为操作机构
						vcNormalVerifiedDet.setCreatedBy(orgCode);
					}
					vcNormalVerifiedDet.setDateCreated(new Date());
					//vcNormalVerifiedDet.setUpdatedBy(orgCode);
					if (!StringUtils.isEmpty(operatorCode)) {
						// 承保、收付系统操作人不为空
						vcNormalVerifiedDet.setUpdatedBy(operatorCode);
					} else {
						// 外部接口操作人可为空，创建人保存为操作机构
						vcNormalVerifiedDet.setUpdatedBy(orgCode);
					}
					
					vcNormalVerifiedDet.setDateUpdated(new Date());
					vcNormalVerifiedDetList.add(vcNormalVerifiedDet);
					//add by chy 20130810 增加核销明细表的保存 end
				}
				//业务号无核销记录且流水号可用，进行核销操作
				VcAvailableDoc vcAvailableDoc = vcAvailableDocList.get(0);
				
				// 若流水号不可用，返回错误提示信息
                if (DateUtils.compare(DateUtils.reset(vcAvailableDoc.getDeadline()), DateUtils.reset(new Date())) < 0) {
                    responseHead.setResponseCode("999");
                    responseHead.setResponseMessage("核销失败，单证["+docNum+"]已过使用截止期！");
                    responseBody.setStatus("1");
                    responseBody.setDocNum(docNum);
                    response.setResponseHead(responseHead);
                    response.setResponseBody(responseBody);
                    return response;
                }
				VcNormalVerification vcNormalVerification = new VcNormalVerification();
				vcNormalVerification.setDocVerCode(docVerCode);
				vcNormalVerification.setDocNum(docNum);
				vcNormalVerification.setPressBatchNo(pressBatchNo);
				//取第一个业务号
				vcNormalVerification.setBusinessNo(vcNormalVerifiedDetList.get(0).getBusinessNo());
				vcNormalVerification.setDocStatus("U1");
				if (!StringUtils.isEmpty(operatorCode)) {
					// 承保、收付系统操作人不为空
					vcNormalVerification.setVerifiedOprCode(operatorCode);
				} else {
					// 外部接口操作人可为空，操作人保存为操作机构
					vcNormalVerification.setVerifiedOprCode(orgCode);
				}
				vcNormalVerification.setVerifiedOrgCode(orgCode);
				vcNormalVerification.setVerifiedTime(new Date());
				// 核销原因（1：打印/2：补录/3：其他）
				vcNormalVerification.setVerifiedReason("1");
				if (!StringUtils.isEmpty(operatorCode)) {
					// 承保、收付系统操作人不为空
					vcNormalVerification.setCreatedBy(operatorCode);
				} else {
					// 外部接口操作人可为空，创建人保存为操作机构
					vcNormalVerification.setCreatedBy(orgCode);
				}
				vcNormalVerification.setDateCreated(new Date());
				//vcNormalVerification.setUpdatedBy(orgCode);
				if (!StringUtils.isEmpty(operatorCode)) {
					// 承保、收付系统操作人不为空
					vcNormalVerification.setUpdatedBy(operatorCode);
				} else {
					// 外部接口操作人可为空，创建人保存为操作机构
					vcNormalVerification.setUpdatedBy(orgCode);
				}
				vcNormalVerification.setDateUpdated(new Date());
				//取第一个业务号
				if (!StringUtils.isEmpty(vcNormalVerifiedDetList.get(0).getRelBusinessNo())){
					vcNormalVerification.setRelBusinessNo(vcNormalVerifiedDetList.get(0).getRelBusinessNo());
				}
				//设置明细列表
				vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
				//设置明细表和主表的关联关系
				for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerifiedDetList){
					vcNormalVerifiedDet.setVcNormalVerification(vcNormalVerification);
				}
				saveList.add(vcNormalVerification);
				delList.add(vcAvailableDoc);
			}
			//add by chy 20130810 挪到循环外面 begin
			// 保存核销表
			docExecuteUsedDao.saveVcNormalVerificationList(saveList);
			// 删除可使用单证表
			docExecuteUsedDao.deleteVcAvailableDocList(delList);
			//add by chy 20130810 挪到循环外面 end
		} catch (DaoException e) {
			logger.info("单证核销，系统数据库操作异常" + e.getMessage(),e);
			throw new BusinessException("单证核销，系统业务错误:" + e.getMessage());
		}
		responseHead.setResponseCode("000");
		responseHead.setResponseMessage("核销成功！");
		responseBody.setStatus("0");
		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);
		return response;
	}

	public void setDocDoUsedDao(DocDoUsedDao docDoUsedDao) {
		this.docDoUsedDao = docDoUsedDao;
	}

	public void setVcDocStatusDao(VcDocStatusDao vcDocStatusDao) {
		this.vcDocStatusDao = vcDocStatusDao;
	}

	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}

}
