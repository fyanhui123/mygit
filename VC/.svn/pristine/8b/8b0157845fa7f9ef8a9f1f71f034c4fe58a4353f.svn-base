package com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tapi.tcs.common.webservice.ResponseHeadDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.InvoiceExportService;
import com.tapi.tcs.vc.schema.model.PubUserDef;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerificationHis;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDetHis;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcBaseInfoDao;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.BusinessNoDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequest;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponse;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocDoAnnuledOldResponseDTO;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.bean.DocSerialNoScope;
import com.tapi.tcs.vc.webservice.provider.docDoAnnuledOld.service.DocAnnuledService;

public class DocAnnuledServiceImpl implements DocAnnuledService {

	private NormalVerificationDao normalVerificationDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	private VcBaseInfoDao vcBaseInfoDao;
	private InvoiceExportService invoiceExportService;
	
	
	@Override
	public DocDoAnnuledOldResponse saveDocAnnul(DocDoAnnuledOldRequest request) throws BusinessException, Exception{
		DocDoAnnuledOldResponse response = new DocDoAnnuledOldResponse();
		ResponseHeadDTO responseHead = new ResponseHeadDTO();
		DocDoAnnuledOldResponseDTO responseBody = new DocDoAnnuledOldResponseDTO();

		DocDoAnnuledOldRequestDTO requestBody = request.getRequestBody();
	
		// 单证类型id转换成单证类型code
		String  docVerCode=vcBaseInfoDao.getDocVerCode(requestBody.getDocVersionID()); 
		if(docVerCode.equals("")){ 
			throw new  BusinessException("单证类型不能为空！");  
		}
		// 转换用户代码
		//String  operator=vcBaseInfoDao.translateUserCode(requestBody.getUserId());
		PubUserDef pubUserDef = vcBaseInfoDao.getPubUserDefByOldId(requestBody.getUserId());
		if(pubUserDef==null){
			throw new  BusinessException("用户信息不存在！"); 
		}
		String operator = pubUserDef.getEmployeeId();
		String operatorName = pubUserDef.getUserName();
		if(operator.equals("")){
			throw new  BusinessException("用户代码不能为空！"); 
		}
		//转换机构代码
     	/*String  comCode = vcBaseInfoDao.translateOrgCode(Long.parseLong(requestBody.getCompanyId()));*/ 
		String  comCode = vcBaseInfoDao.translateOrgCode(requestBody.getDepartId(), requestBody.getDepartCode());
     	if(comCode==null || comCode.equals("")){
     		throw new  BusinessException("机构代码不能为空！");  
     	}
     	
		Date nowDate = new Date();
		List<DocSerialNoScope> docSerialNoScopes = requestBody.getDocSerialNoScope();
		// 最后删除的正常核销记录
		List<VcNormalVerification> normalVerifications = new ArrayList<VcNormalVerification>();
		// 最后保存的正常核销记录
		List<VcAbnormalVerification> abnormalVerifications = new ArrayList<VcAbnormalVerification>();
		//正常核销历史表 add by chy 20130812
		List<VcNormalVerificationHis> vcNormalVerificationHisList = new ArrayList<VcNormalVerificationHis>();
		//发票代码（印刷批次）
		String pressBatchNo = requestBody.getPressBatchNo();
		if(StringUtils.isBlank(pressBatchNo)){
			if(SysConst.TAXATION_ORGS.indexOf(comCode.substring(0, 4))>-1){
				throw new BusinessException("发票代码不能为空!");
			}else{
				pressBatchNo = "000";
			}
		}
		for (DocSerialNoScope docSerialNoScope : docSerialNoScopes) {
			//业务号
			/*String businessNo = docSerialNoScope.getPolicyNo();
			if(StringUtils.isNotEmpty(docSerialNoScope.getEndorseNo())){
				businessNo = docSerialNoScope.getEndorseNo();
			}
			Long startNum = Long.valueOf(docSerialNoScope.getDocBeginSerialNo());
			Long endNum = Long.valueOf(docSerialNoScope.getDocEndSerialNo());
			//格式化流水号；防止丢失前缀0
			DecimalFormat df = new DecimalFormat("0");
			df.setMinimumIntegerDigits(docSerialNoScope.getDocBeginSerialNo().length());*/
			
			//正常核销记录
			VcNormalVerification vcNormalVerification = null;
			//流水号
			String docNum = docSerialNoScope.getDocNum();
			//是否红冲作废
			String isCounteract = docSerialNoScope.getIsCounteract();
			//业务号列表
			List<BusinessNoDTO> businessNoDtos = docSerialNoScope.getBusinessNoDTOs();
			//非正常核销明细列表 add by chy 20130810
			List<VcAbnormalVerifiedDet> vcAbnormalVerifiedDetList = new ArrayList<VcAbnormalVerifiedDet>();
			// 查询对应正常核销信息
			//for (long i = startNum; i < endNum + 1; i++) {
			if(businessNoDtos!=null && businessNoDtos.size()>0){
				for(BusinessNoDTO businessNoDTO : businessNoDtos){
					//业务号
					String businessNo = businessNoDTO.getBusinessNo();
					//批单对应的业务号
					String relationBusinessNo = businessNoDTO.getRelationBusinessNo();
					//缴费期次
					String payNo = businessNoDTO.getPayNo();
					if(StringUtils.isEmpty(payNo)){
						payNo = "1";
					}
					//拆分批次
					String batchNo = businessNoDTO.getBatchNo();
					
					/*QueryRule queryRule = QueryRule.getInstance();
					queryRule.addEqual("docVerCode", docVerCode);
					queryRule.addEqual("docNum", docNum);
					queryRule.addEqual("pressBatchNo", pressBatchNo);//印刷批次
					queryRule.addEqual("businessNo", businessNo);//业务号
					queryRule.addEqual("docStatus", "U1");
					vcNormalVerification = normalVerificationDao.findUnique(queryRule);*/
					vcNormalVerification = normalVerificationDao.findVcNormalVerification(docVerCode, 
							docNum, pressBatchNo, businessNo, payNo);
	
					if (vcNormalVerification == null) {
						responseHead.setResponseCode("999");
						responseHead.setResponseMessage("未找到正常核销记录");
	
						responseBody.setDoAnnuledStatus("1");
						responseBody.setDocVerCode(docVerCode);
						responseBody.setDocSerialNo(docNum);
	
						response.setResponseHead(responseHead);
						response.setResponseBody(responseBody);
						return response;
					} else {
						normalVerifications.add(vcNormalVerification);
	
						//保存非正常核销明细
						VcAbnormalVerifiedDet vcAbnormalVerifiedDet = new VcAbnormalVerifiedDet();
						vcAbnormalVerifiedDet.setBusinessNo(businessNo);
						vcAbnormalVerifiedDet.setRelBusinessNo(relationBusinessNo);
						vcAbnormalVerifiedDet.setPayNo(payNo);
						vcAbnormalVerifiedDet.setBatchNo(batchNo);//拆分批次
						vcAbnormalVerifiedDet.setCreatedBy(operator);
						vcAbnormalVerifiedDet.setDateCreated(nowDate);
						vcAbnormalVerifiedDet.setUpdatedBy(operator);
						vcAbnormalVerifiedDet.setDateUpdated(nowDate);
						vcAbnormalVerifiedDetList.add(vcAbnormalVerifiedDet);
					}
				}
			}else{
				//不传业务号列表的情况
				vcNormalVerification = normalVerificationDao.findVcNormalVerification(docVerCode, 
						docNum, pressBatchNo, null, null);

				if (vcNormalVerification == null) {
					responseHead.setResponseCode("999");
					responseHead.setResponseMessage("未找到正常核销记录");

					responseBody.setDoAnnuledStatus("1");
					responseBody.setDocVerCode(docVerCode);
					responseBody.setDocSerialNo(docNum);

					response.setResponseHead(responseHead);
					response.setResponseBody(responseBody);
					return response;
				} else {
					normalVerifications.add(vcNormalVerification);
				}
			}
			VcAbnormalVerification abnormalVerification = new VcAbnormalVerification();
			abnormalVerification.setDocVerCode(docVerCode);
			abnormalVerification.setPressBatchNo(pressBatchNo);
			abnormalVerification.setDocNum(docNum);
			//取核销记录中的业务号
			abnormalVerification.setBusinessNo(vcNormalVerification.getBusinessNo());
			// 如果正常核销日期跟非正常核销日期在同一个月份，为C2，如不在同一个月份为C3
			Date nVerificationDate = vcNormalVerification.getVerifiedTime();
			int months = DateUtils.getMonthDifference(nVerificationDate, nowDate);
			String docStatus = "C3";
			if (months == 0) {
				docStatus = "C2";
			}
			abnormalVerification.setDocStatus(docStatus);
			abnormalVerification.setVerifiedOprCode(operator);
			abnormalVerification.setVerifiedOrgCode(comCode);
			abnormalVerification.setVerifiedTime(nowDate);
			//如果是红冲，则非正常核销类别为H：红冲作废核销
			if("1".equals(isCounteract)){
				abnormalVerification.setType("H");
			}else{
				abnormalVerification.setType("C");
			}
			abnormalVerification.setCreatedBy(operator);
			abnormalVerification.setDateCreated(nowDate);
			abnormalVerification.setUpdatedBy(operator);
			abnormalVerification.setDateUpdated(nowDate);
			//设置明细表
			abnormalVerification.setVcAbnormalVerifiedDetList(vcAbnormalVerifiedDetList);
			//设置主表
			for(VcAbnormalVerifiedDet vcAbnormalVerifiedDet:vcAbnormalVerifiedDetList){
				vcAbnormalVerifiedDet.setVcAbnormalVerification(abnormalVerification);
			}
			abnormalVerifications.add(abnormalVerification);
			
			//add by chy 20130812 核销记录转储his表 begin
			VcNormalVerificationHis vcNormalVerificationHis = new VcNormalVerificationHis();
			Beans.copy(vcNormalVerificationHis, vcNormalVerification);
			if(vcNormalVerification.getVcNormalVerifiedDet()!=null){
				List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHisList = new ArrayList<VcNormalVerifiedDetHis>();
				for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerification.getVcNormalVerifiedDet()){
					VcNormalVerifiedDetHis vcNormalVerifiedDetHis = new VcNormalVerifiedDetHis();
					Beans.copy(vcNormalVerifiedDetHis, vcNormalVerifiedDet);
					vcNormalVerifiedDetHis.setIdVcNormalVerifiedDetHis(null);
					vcNormalVerifiedDetHis.setVcNormalVerificationHis(vcNormalVerificationHis);
					vcNormalVerifiedDetHis.setCreatedBy(operator);
					vcNormalVerifiedDetHis.setDateCreated(nowDate);
					vcNormalVerifiedDetHis.setUpdatedBy(operator);
					vcNormalVerifiedDetHis.setDateUpdated(nowDate);
					vcNormalVerifiedDetHisList.add(vcNormalVerifiedDetHis);
				}
				vcNormalVerificationHis.setVcNormalVerifiedDetHis(vcNormalVerifiedDetHisList);
			}
			vcNormalVerificationHis.setIdVcNormalVerificationHis(null);
			if(StringUtils.isBlank(vcNormalVerificationHis.getUseType())){
				vcNormalVerificationHis.setUseType("1");//暂时付默认值
			}
			vcNormalVerificationHis.setCreatedBy(operator);
			vcNormalVerificationHis.setDateCreated(nowDate);
			vcNormalVerificationHis.setUpdatedBy(operator);
			vcNormalVerificationHis.setDateUpdated(nowDate);
			vcNormalVerificationHisList.add(vcNormalVerificationHis);
			//add by chy 20130812 核销记录转储his表 end
			
			//******对地税平台的处理 begin****
			VcInvoicePrint vcInvoicePrint = abnormalVerificationDao.findVcInvoicePrint(docVerCode, pressBatchNo, docNum);
			if(vcInvoicePrint!=null && comCode.length()>=4){
				this.processInvoicePlat(vcInvoicePrint, comCode, operator, operatorName, nowDate);
			}
			//******对地税平台的处理 end****
		}

		// 作废保存

		abnormalVerificationDao.saveAll(abnormalVerifications);
		normalVerificationDao.deleteAll(normalVerifications);
		//add by chy 20130812 增加核销历史表的保存
		normalVerificationDao.saveAll(vcNormalVerificationHisList);

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
			//浙江地区、大连地区、河南地区、重庆地区、山西地区、上海、黑龙江
			if(SysConst.COMCODE_ZJ.equals(comCode.substring(0,4))
					||SysConst.COMCODE_DL.equals(comCode.substring(0,4))
					||SysConst.COMCODE_HeNan.equals(comCode.substring(0,4))
					||SysConst.COMCODE_ChongQing.equals(comCode.substring(0,4))
					||SysConst.COMCODE_SX.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_SHANGHAI.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_HLJ.equals(comCode.substring(0, 4))){
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
			//江苏地区、贵州地区
			else if(SysConst.COMCODE_JS.equals(comCode.substring(0, 4))
					||SysConst.COMCODE_GZ.equals(comCode.substring(0, 4))){
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

	public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}

	public void setAbnormalVerificationDao(AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}
	public void setVcBaseInfoDao(VcBaseInfoDao vcBaseInfoDao) {
		this.vcBaseInfoDao = vcBaseInfoDao;
	}
	public void setInvoiceExportService(InvoiceExportService invoiceExportService) {
		this.invoiceExportService = invoiceExportService;
	}
}
