package com.tapi.tcs.vc.webservice.provider.docDoAnnuled.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.InvoiceExportService;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerificationHis;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDetHis;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DoAnnuledDocNumInfoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.DocDoAnnuledResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.bean.ExtendDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuled.service.DocDoAnnuledQueryService;

public class DocDoAnnuledQueryServiceImpl implements DocDoAnnuledQueryService {

	private NormalVerificationDao normalVerificationDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	private VcBaseInfoDao vcBaseInfoDao;
	private InvoiceExportService invoiceExportService;

	/****
	 * 单证作废接口 新核心系统
	 */
	@Override
	public DocDoAnnuledResponse executeDocDoAnnuled(DocDoAnnuledRequestDTO requestBody) throws BusinessException {
		DocDoAnnuledResponse response = new DocDoAnnuledResponse();
		DocDoAnnuledResponseDTO responseBody = new DocDoAnnuledResponseDTO();

		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		// 单证类型代码
		String docVerCode = requestBody.getDocVerCode();
		// 操作员代码
		String operatorCode = requestBody.getOperatorCode();
		// 操作员归属机构代码
		String orgCode = requestBody.getOrgCode();
		// 印刷批次
		String pressBatchNo = requestBody.getPressBatchNo();

		try {
			String operatorName = "";
			// 外部接口操作人可为空，保存为操作机构代码
			if (StringUtils.isEmpty(operatorCode)) {
				operatorCode = orgCode;
				operatorName = vcBaseInfoDao.findCompanyNameByCode(operatorCode);
			}else{
				operatorName = vcBaseInfoDao.findUserNameByEmployeeId(operatorCode);
			}
			// 根据单证类型查询关联单证种类信息
			VcDocType vcDocType = vcBaseInfoDao.getVcDocType(requestBody.getDocVerCode()).get(0);
			String docType = vcDocType.getDocType();

			// 作废单证类型为财务类，印刷批次不能为空
			if (docType.equals(SysConst.DOC_TYPE_FINANCE) && StringUtils.isEmpty(pressBatchNo)) {
				throw new BusinessException("印刷批次不能为空！");
			}
			// 业务单证默认是000
			if (StringUtils.isBlank(pressBatchNo)) {
				pressBatchNo = "000";
			}
			// 单证流水列表
			List<DoAnnuledDocNumInfoDTO> docNumInfoDTOs = requestBody.getDocNumInfoDTOs();

			// 正常核销表
			List<VcNormalVerification> normalVerifications = new ArrayList<VcNormalVerification>();
			// 非正常核销表
			List<VcAbnormalVerification> abnormalVerifications = new ArrayList<VcAbnormalVerification>();
			//正常核销历史表 add by chy 20130810
			List<VcNormalVerificationHis> vcNormalVerificationHisList = new ArrayList<VcNormalVerificationHis>();

			Date nowDate = new Date();
			for (DoAnnuledDocNumInfoDTO doAnnuledDocNumInfoDTO : docNumInfoDTOs) {
				// 单证流水号
				String docNum = doAnnuledDocNumInfoDTO.getDocNum();
				// 是否红冲作废
				String isCounteract = doAnnuledDocNumInfoDTO.getIsCounteract();
				//正常核销记录
				VcNormalVerification vcNormalVerification = null;
				// 业务号列表
				List<BusinessNoDTO> businessNoDTOs = doAnnuledDocNumInfoDTO.getBusinessNoDTOs();
				//非正常核销明细列表 add by chy 20130810
				List<VcAbnormalVerifiedDet> vcAbnormalVerifiedDetList = new ArrayList<VcAbnormalVerifiedDet>();
				for (BusinessNoDTO businessNoDTO : businessNoDTOs) {
					// 业务号
					String businessNo = businessNoDTO.getBusinessNo();
					// 批单对应的保单业务号
					String relationBusinessNo = businessNoDTO.getRelationBusinessNo();
					//缴费期次
					String payNo = businessNoDTO.getPayNo();
					if(StringUtils.isEmpty(payNo)){
						payNo = "1";
					}
					//拆分批次
					String batchNo = businessNoDTO.getBatchNo();

					// 流水号截取8位
					// String docNum=docNumString.substring(docNumString.length()-8);

					//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
					String payerCode = "";
					List<ExtendDTO> extendLists = null;
					if(businessNoDTO.getExtendList() != null && businessNoDTO.getExtendList().size() >0 ){
						extendLists = businessNoDTO.getExtendList();
                    	//收付系统共保业务发票打印
                    	for(ExtendDTO extendDTO : extendLists){
                    		if(("payerCode").equals(extendDTO.getKey())){
                    			payerCode = extendDTO.getValue();
                    		}
                    	}
                    }
					//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
					
					//add by chy 20130812 begin
					vcNormalVerification = normalVerificationDao.findVcNormalVerification(docVerCode, 
							docNum, pressBatchNo, businessNo);
					//add by chy 20130812 begin
					if (vcNormalVerification == null) {
						responseHead.setResponseCode("999");
						responseHead.setResponseMessage("作废失败,未找到正常核销记录");
						responseBody.setDoAnnuledStatus("1");
						responseBody.setDocVerCode(docVerCode);
						responseBody.setDocNum(docNum);
						response.setResponseHead(responseHead);
						response.setResponseBody(responseBody);
						return response;
					} else {
						normalVerifications.add(vcNormalVerification);
						//add by chy 20130810 begin
						//保存非正常核销明细
						VcAbnormalVerifiedDet vcAbnormalVerifiedDet = new VcAbnormalVerifiedDet();
						vcAbnormalVerifiedDet.setBusinessNo(businessNo);
						vcAbnormalVerifiedDet.setRelBusinessNo(relationBusinessNo);
						//modify by chy 20131017 begin
						//vcAbnormalVerifiedDet.setPayNo("1");
						vcAbnormalVerifiedDet.setPayNo(payNo);
						vcAbnormalVerifiedDet.setBatchNo(batchNo);//拆分批次
						//modify by chy 20131017 end
						//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
						vcAbnormalVerifiedDet.setPayerCode(payerCode);//共保付款人
						//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
						vcAbnormalVerifiedDet.setCreatedBy(operatorCode);
						vcAbnormalVerifiedDet.setDateCreated(nowDate);
						vcAbnormalVerifiedDet.setUpdatedBy(operatorCode);
						vcAbnormalVerifiedDet.setDateUpdated(nowDate);
						vcAbnormalVerifiedDetList.add(vcAbnormalVerifiedDet);
						//add by chy 20130810 end
					}
				}
				//add by chy 20130810 begin
				VcAbnormalVerification abnormalVerification = new VcAbnormalVerification();
				abnormalVerification.setDocVerCode(docVerCode);
				abnormalVerification.setPressBatchNo(vcNormalVerification.getPressBatchNo());
				abnormalVerification.setDocNum(docNum);
				abnormalVerification.setBusinessNo(vcNormalVerification.getBusinessNo());
				// 如果正常核销日期跟非正常核销日期在同一个月份，为C2，如不在同一个月份为C3
				Date nVerificationDate = vcNormalVerification.getVerifiedTime();
				int months = DateUtils.getMonthDifference(nVerificationDate, nowDate);
				String docStatus = "C3";
				if (months == 0) {
					docStatus = "C2";
				}
				abnormalVerification.setDocStatus(docStatus);
				abnormalVerification.setVerifiedOprCode(operatorCode);
				abnormalVerification.setVerifiedOrgCode(orgCode);
				abnormalVerification.setVerifiedTime(nowDate);
				//modify by chy 20131017 begin
				//abnormalVerification.setType("C");
				//如果是红冲，则非正常核销类别为H：红冲作废核销
				if("1".equals(isCounteract)){
					abnormalVerification.setType("H");
				}else{
					abnormalVerification.setType("C");
				}
				//modify by chy 20131017 end
				abnormalVerification.setCreatedBy(operatorCode);
				abnormalVerification.setDateCreated(nowDate);
				abnormalVerification.setUpdatedBy(operatorCode);
				abnormalVerification.setDateUpdated(nowDate);
				if (!StringUtils.isEmpty(vcNormalVerification.getRelBusinessNo())) {
					abnormalVerification.setRelBusinessNo(vcNormalVerification.getRelBusinessNo());
				}
				//设置明细表
				abnormalVerification.setVcAbnormalVerifiedDetList(vcAbnormalVerifiedDetList);
				//设置主表
				for(VcAbnormalVerifiedDet vcAbnormalVerifiedDet:vcAbnormalVerifiedDetList){
					vcAbnormalVerifiedDet.setVcAbnormalVerification(abnormalVerification);
				}
				abnormalVerifications.add(abnormalVerification);
				//转储his表
				VcNormalVerificationHis vcNormalVerificationHis = new VcNormalVerificationHis();
				Beans.copy(vcNormalVerificationHis, vcNormalVerification);
				if(vcNormalVerification.getVcNormalVerifiedDet()!=null){
					List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHisList = new ArrayList<VcNormalVerifiedDetHis>();
					for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerification.getVcNormalVerifiedDet()){
						VcNormalVerifiedDetHis vcNormalVerifiedDetHis = new VcNormalVerifiedDetHis();
						Beans.copy(vcNormalVerifiedDetHis, vcNormalVerifiedDet);
						vcNormalVerifiedDetHis.setIdVcNormalVerifiedDetHis(null);
						vcNormalVerifiedDetHis.setVcNormalVerificationHis(vcNormalVerificationHis);
						vcNormalVerifiedDetHis.setCreatedBy(operatorCode);
						vcNormalVerifiedDetHis.setDateCreated(nowDate);
						vcNormalVerifiedDetHis.setUpdatedBy(operatorCode);
						vcNormalVerifiedDetHis.setDateUpdated(nowDate);
						vcNormalVerifiedDetHisList.add(vcNormalVerifiedDetHis);
					}
					vcNormalVerificationHis.setVcNormalVerifiedDetHis(vcNormalVerifiedDetHisList);
				}
				vcNormalVerificationHis.setIdVcNormalVerificationHis(null);
				if(StringUtils.isBlank(vcNormalVerificationHis.getUseType())){
					vcNormalVerificationHis.setUseType("1");//暂时付默认值
				}
				vcNormalVerificationHis.setCreatedBy(operatorCode);
				vcNormalVerificationHis.setDateCreated(nowDate);
				vcNormalVerificationHis.setUpdatedBy(operatorCode);
				vcNormalVerificationHis.setDateUpdated(nowDate);
				vcNormalVerificationHisList.add(vcNormalVerificationHis);
				//add by chy 20130810 end
				
				if(!"1".equals(isCounteract)){
					//******对地税平台的处理 begin****
					VcInvoicePrint vcInvoicePrint = abnormalVerificationDao.findVcInvoicePrint(docVerCode, pressBatchNo, docNum);
					if(vcInvoicePrint!=null && orgCode.length()>=4){
						this.processInvoicePlat(vcInvoicePrint, orgCode, operatorCode, operatorName,  nowDate);
					}
					//******对地税平台的处理 end****
				}
			}
			// 作废成功、把正常核销表中的记录删掉、添加到非正常核销表中
			abnormalVerificationDao.saveAll(abnormalVerifications);
			normalVerificationDao.deleteAll(normalVerifications);
			//保存历史表 add by chy 20130810
			normalVerificationDao.saveAll(vcNormalVerificationHisList);
		} catch (DaoException e) {
			throw new BusinessException(e.getMessage());
		}
		responseHead.setResponseCode("000");
		responseHead.setResponseMessage("作废成功");

		responseBody.setDoAnnuledStatus("0");
		responseBody.setDocVerCode(docVerCode);

		response.setResponseHead(responseHead);
		response.setResponseBody(responseBody);

		return response;
	}
	
	/**
	 * 对于地税的处理
	 * @param vcInvoicePrint
	 * @param comCode
	 * @param operator
	 * @param operatorName
	 * @param nowDate
	 * @throws BusinessException
	 */
	private void processInvoicePlat(VcInvoicePrint vcInvoicePrint,String comCode,
			String operator,String operatorName,Date nowDate) throws BusinessException{
		try{
			//浙江地区、大连地区、河南地区、重庆地区、山西地区、上海、黑龙江、航保中心
			if(SysConst.COMCODE_ZJ.equals(comCode.substring(0,4))
					||SysConst.COMCODE_DL.equals(comCode.substring(0,4))
					||SysConst.COMCODE_HeNan.equals(comCode.substring(0,4))
					||SysConst.COMCODE_ChongQing.equals(comCode.substring(0,4))
					||SysConst.COMCODE_SX.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_SHANGHAI.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_HLJ.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_HANGBAO.equals(comCode.substring(0, 4))){
				//如果打印信息已上传平台，则作废重新生成一条数据上传平台
				if("1".equals(vcInvoicePrint.getIsUploadPlat())){
					VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,nowDate);
					vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
					//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
					vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
					vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
					vcInvoicePrintNew.setCanceldDate(nowDate);//作废时间
					vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
					vcInvoicePrintNew.setStatus("1");//有效
					abnormalVerificationDao.save(vcInvoicePrintNew);
				}else{
					//如果打印信息未上传平台，则直接上传作废信息。即替换掉打印信息
					VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,nowDate);
					vcInvoicePrint.setStatus("0");
					//begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrint.setDateUpdated(nowDate);
					//end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
					//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
					vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
					vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
					vcInvoicePrintNew.setCanceldDate(nowDate);//作废时间
					vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
					vcInvoicePrintNew.setStatus("1");//有效
					abnormalVerificationDao.update(vcInvoicePrint);
					abnormalVerificationDao.save(vcInvoicePrintNew);
				}
			}
			//厦门地区、天津地区
			else if(SysConst.COMCODE_XM.equals(comCode.substring(0,4))
					||SysConst.COMCODE_TJ.equals(comCode.substring(0,4))){
				//1、打印已上传，则不传作废；
				//2、打印、作废都未上传，则上传作废；
				if(!"1".equals(vcInvoicePrint.getIsUploadPlat())){
					VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,nowDate);
					vcInvoicePrint.setStatus("0");
					//begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrint.setDateUpdated(nowDate);
					//end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
					//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
					vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
					vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
					vcInvoicePrintNew.setCanceldDate(nowDate);//作废时间
					vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
					vcInvoicePrintNew.setStatus("1");//有效
					abnormalVerificationDao.update(vcInvoicePrint);
					abnormalVerificationDao.save(vcInvoicePrintNew);
				}
			}
			//江苏地区、贵州地区、北京地区
			else if(SysConst.COMCODE_JS.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_GZ.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_BEIJING.equals(comCode.substring(0, 4))){
				//不管是否上传平台，重新生成一条作废数据
				VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,nowDate);
				vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
				//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
				vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
				vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
				vcInvoicePrintNew.setCanceldDate(nowDate);//作废时间
				vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
				vcInvoicePrintNew.setStatus("1");//有效
				abnormalVerificationDao.save(vcInvoicePrintNew);
			}
			//福建
			else if(SysConst.COMCODE_FJ.equals(comCode.substring(0,4))){
				//打印未上传，则上传作废
				//打印已上传，不允许作废
				if("1".equals(vcInvoicePrint.getIsUploadPlat())){
					//throw new BusinessException("该发票已上传地税，不能作废，请做冲红处理。");
				}else{
					VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,nowDate);
					vcInvoicePrint.setStatus("0");
					//begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrint.setDateUpdated(nowDate);
					//end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
					vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
					//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
					vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
					vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
					vcInvoicePrintNew.setCanceldDate(nowDate);//作废时间
					vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
					vcInvoicePrintNew.setStatus("1");//有效
					abnormalVerificationDao.update(vcInvoicePrint);
					abnormalVerificationDao.save(vcInvoicePrintNew);
				}
			}
		}/*catch(BusinessException e){
			throw e;
		}*/catch(Exception e){
			throw new BusinessException("保存发票作废信息失败！", e);
		}
	}

	public void setNormalVerificationDao(
			NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}

	public void setAbnormalVerificationDao(
			AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}

	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}

	public void setInvoiceExportService(InvoiceExportService invoiceExportService) {
		this.invoiceExportService = invoiceExportService;
	}

}
