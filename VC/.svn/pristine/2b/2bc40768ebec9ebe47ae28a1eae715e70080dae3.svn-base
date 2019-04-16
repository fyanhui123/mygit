package com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.HeadDTO;
import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
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
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.BillDTO;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.bean.NewInvoiceDocDoAnnuledRequest;
import com.tapi.tcs.vc.webservice.provider.newDocDoAnnuled.dao.NewInvoiceDocDoAnnuledDao;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.dao.NewInvoiceDoUsedDao;

public class VcNewInvoiceDocDoAnnuledServiceImpl implements VcNewInvoiceDocDoAnnuledService{
	private NormalVerificationDao normalVerificationDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	private inputStorage  inputStorageDao;
	private NewInvoiceDocDoAnnuledDao newInvoiceDocDoAnnuledDao;
	private NewInvoiceDoUsedDao doUsedDao;
	private DocExecuteUsedDao docExecuteUsedDao;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@SuppressWarnings("unused")
	@Override
	public StorageResponse executeNewDocDoAnnuled(
			NewInvoiceDocDoAnnuledRequest request) {
		logger.error("进入executeNewDocDoAnnuled方法");
		logger.error("请求报文"+request);
		HeadDTO head=request.getHeadDTO();
		ResponseDTO responsehead=new ResponseDTO();
		responsehead.setfLOWINTIME(head.getfLOWINTIME());
		responsehead.setrEQSERIALNO(head.getrEQSERIALNO());
		responsehead.setrEQUESTTYPE(head.getrEQUESTTYPE());
		StorageResponseDTO responsebody=new StorageResponseDTO();
		StorageResponse reponse =new StorageResponse();
		try {
		String docVerCode=null;
		String TakeCode=null;
		String org=null;
		String pressBatchNo=request.getBody().getPressBatchNo();
		String docNum=request.getBody().getDocNum();
		String orgcode= request.getBody().getOrgcode();
		String biaozhi=request.getBody().getFlag();
		try {
			org=inputStorageDao.queryUserInfo(request.getBody().getTakeCode(), "3", null);
			docVerCode = inputStorageDao.queryDocverCode(request.getBody().getDocVerCode());
			TakeCode = inputStorageDao.queryUserInfo(request.getBody().getTakeCode(), "1",orgcode);
		} catch (Exception e) {
			logger.error("异常信息"+e);
			throw new  BusinessException(e.getMessage()); 
		}
		/* 作废
		 * 查询正常核销表
		 * 如果查询到的话  删除正常核销表 
		 * 插入非正常核销表
		 * */
		// 正常核销表
		List<VcNormalVerification> normalVerifications = new ArrayList<VcNormalVerification>();
		// 非正常核销表
		List<VcAbnormalVerification> abnormalVerifications = new ArrayList<VcAbnormalVerification>();	
		
			//正常核销历史表 add by chy 20130810
		List<VcNormalVerificationHis> vcNormalVerificationHisList = new ArrayList<VcNormalVerificationHis>();	
		Date nowDate = new Date();
		List<VcAbnormalVerifiedDet> vcAbnormalVerifiedDetList = new ArrayList<VcAbnormalVerifiedDet>();
		List<BillDTO> billDTO=request.getBody().getBillDTO();//获取保单信息
		String flag=request.getBody().getCounteractFlag();//是不是红冲 0  不是  1是
		//红冲，修改核销表 （新）    插入非正常核销（老的） fyh  
		String counteractedInvoiceCode=request.getBody().getCounteractedInvoiceCode();//冲红发票代码
		String counteractedInvoiceNo=request.getBody().getCounteractedInvoiceNo();//冲红发票号码
		VcNormalVerification vcNormalVerification = null;
		VcAbnormalVerification vcInvoice =null;
		List <VcNormalVerifiedDet> listBusinessNo;
		try {
			vcNormalVerification = normalVerificationDao.findVcNormalVerification(docVerCode, docNum, pressBatchNo, null);
			listBusinessNo=normalVerificationDao.findVcNormalVerifiedDet(vcNormalVerification.getIdVcNormalVerification());
			vcInvoice =newInvoiceDocDoAnnuledDao.findVcAbNormalVerification(docVerCode, docNum, pressBatchNo, null);
			logger.error("核销明细表保单数-->"+listBusinessNo.size());
			if(vcInvoice!=null){
				throw new  BusinessException("查询失败,发票"+docNum+"已经作废或红冲"); 
			}
			if (vcNormalVerification == null) {
				throw new  BusinessException("查询失败,发票"+docNum+"无数据"); 
			}
			logger.error("比较-->"+(vcInvoice!=null));
		} catch (Exception e) {
			logger.error("核销查询异常"+e.getMessage());
			throw new  BusinessException("核销查询异常"); 
		}
		String payNo="";
		if(biaozhi.equals("2")){   //合并的保单
			for(int k=0;k<listBusinessNo.size();k++){
				normalVerifications.add(vcNormalVerification);
				//add by chy 20130810 begin
				//保存非正常核销明细
				payNo=vcNormalVerification.getVcNormalVerifiedDet().get(k).getPayNo();
				VcAbnormalVerifiedDet vcAbnormalVerifiedDet = new VcAbnormalVerifiedDet();
				vcAbnormalVerifiedDet.setBusinessNo(listBusinessNo.get(k).getBusinessNo());
				vcAbnormalVerifiedDet.setRelBusinessNo("");
				if(null==payNo||payNo.equals("")){
					payNo="1";
				}
				vcAbnormalVerifiedDet.setPayNo(payNo);
				vcAbnormalVerifiedDet.setBatchNo(vcNormalVerification.getVcNormalVerifiedDet().get(k).getBatchNo());//拆分批次
				//modify by chy 20131017 end
				//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
				vcAbnormalVerifiedDet.setPayerCode(vcNormalVerification.getVcNormalVerifiedDet().get(k).getPayerCode());//共保付款人
				//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
				vcAbnormalVerifiedDet.setCreatedBy(TakeCode);
				vcAbnormalVerifiedDet.setDateCreated(nowDate);
				vcAbnormalVerifiedDet.setUpdatedBy(TakeCode);
				vcAbnormalVerifiedDet.setDateUpdated(nowDate);
				if(flag.equals("1")){
					vcAbnormalVerifiedDet.setCounteractFlag("1");
				}else{
					vcAbnormalVerifiedDet.setCounteractFlag(vcNormalVerification.getVcNormalVerifiedDet().get(k).getCounteractFlag());
				}
				vcAbnormalVerifiedDet.setCorrectionNo(vcNormalVerification.getVcNormalVerifiedDet().get(k).getCorrectionNo());
				vcAbnormalVerifiedDetList.add(vcAbnormalVerifiedDet);	
	    }
		}else{
			for(int k=0;k<billDTO.size();k++){
				 payNo=billDTO.get(k).getPayNo();//缴费期次
				if(payNo==null||payNo.length()<=0){
					payNo="1";
				}
				String endorNo=billDTO.get(k).getPayNo();//批改序号
				String businessNo=billDTO.get(k).getPolicyNo();//保单号
				normalVerifications.add(vcNormalVerification);
				//add by chy 20130810 begin
				//保存非正常核销明细
				VcAbnormalVerifiedDet vcAbnormalVerifiedDet = new VcAbnormalVerifiedDet();
			    vcAbnormalVerifiedDet.setBusinessNo(vcNormalVerification.getBusinessNo());
				vcAbnormalVerifiedDet.setRelBusinessNo(vcNormalVerification.getBusinessNo());
				//modify by chy 20131017 begin
				//vcAbnormalVerifiedDet.setPayNo("1");
				vcAbnormalVerifiedDet.setPayNo(payNo);
				vcAbnormalVerifiedDet.setBatchNo(vcNormalVerification.getVcNormalVerifiedDet().get(k).getBatchNo());//拆分批次
				//modify by chy 20131017 end
				//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” begin
				vcAbnormalVerifiedDet.setPayerCode(vcNormalVerification.getVcNormalVerifiedDet().get(k).getPayerCode());//共保付款人
				//modifY by zhxiao3 VC-129 发票打印新增字段“共保付款人” end
				vcAbnormalVerifiedDet.setCreatedBy(TakeCode);
				vcAbnormalVerifiedDet.setDateCreated(nowDate);
				vcAbnormalVerifiedDet.setUpdatedBy(TakeCode);
				vcAbnormalVerifiedDet.setDateUpdated(nowDate);
				if(flag.equals("1")){
					vcAbnormalVerifiedDet.setCounteractFlag("1");
				}else{
					vcAbnormalVerifiedDet.setCounteractFlag(vcNormalVerification.getVcNormalVerifiedDet().get(k).getCounteractFlag());
				}
				vcAbnormalVerifiedDet.setCorrectionNo(vcNormalVerification.getVcNormalVerifiedDet().get(k).getCorrectionNo());
				vcAbnormalVerifiedDetList.add(vcAbnormalVerifiedDet);
			}
		}
		VcAbnormalVerification abnormalVerification = new VcAbnormalVerification();
		abnormalVerification.setDocVerCode(docVerCode);
		abnormalVerification.setPressBatchNo(pressBatchNo);
		//如果是红冲的记录非正常核销表原数据 更新正常核销表的docnum 
		abnormalVerification.setDocNum(docNum);
		abnormalVerification.setBusinessNo(vcNormalVerification.getBusinessNo());
		// 如果正常核销日期跟非正常核销日期在同一个月份，为C2，如不在同一个月份为C3
		Date nVerificationDate = vcNormalVerification.getVerifiedTime();
		int months = DateUtils.getMonthDifference(nVerificationDate, nowDate);
		String docStatus="";
		if(flag.equals("1")){
			docStatus="H";//红冲
		}else{
			 docStatus = "C3";
				if (months == 0) {
					docStatus = "C2";
				}
		}
		abnormalVerification.setDocStatus(docStatus);
		abnormalVerification.setVerifiedOprCode(TakeCode);
		abnormalVerification.setVerifiedOrgCode(org);
		abnormalVerification.setVerifiedTime(nowDate);
		//modify by chy 20131017 begin
		//abnormalVerification.setType("C");
		//如果是红冲，则非正常核销类别为H：红冲作废核销
		if(flag.equals("1")){
			abnormalVerification.setType("H");
		}else{
			abnormalVerification.setType("C");
		}
		//modify by chy 20131017 end
		abnormalVerification.setCreatedBy(TakeCode);
		abnormalVerification.setDateCreated(nowDate);
		abnormalVerification.setUpdatedBy(TakeCode);
		abnormalVerification.setDateUpdated(nowDate);
		if (!StringUtils.isEmpty(vcNormalVerification.getRelBusinessNo())) {
			abnormalVerification.setRelBusinessNo("");
		}
			abnormalVerification.setCOUNTERACTEDINVOICENO(counteractedInvoiceNo);
			abnormalVerification.setCOUNTERACTEDINVOICECODE(counteractedInvoiceCode);
		//设置明细表
		abnormalVerification.setVcAbnormalVerifiedDetList(vcAbnormalVerifiedDetList);
		//设置主表
		for(VcAbnormalVerifiedDet vcAbnormalVerifiedDets:vcAbnormalVerifiedDetList){
			vcAbnormalVerifiedDets.setVcAbnormalVerification(abnormalVerification);
		}
		abnormalVerifications.add(abnormalVerification);
		//写入历史记录表开始
		VcNormalVerificationHis vcNormalVerificationHis = new VcNormalVerificationHis();
		Beans.copy(vcNormalVerificationHis, vcNormalVerification);
		if(vcNormalVerification.getVcNormalVerifiedDet()!=null){
			List<VcNormalVerifiedDetHis> vcNormalVerifiedDetHisList = new ArrayList<VcNormalVerifiedDetHis>();
			for(VcNormalVerifiedDet vcNormalVerifiedDet:vcNormalVerification.getVcNormalVerifiedDet()){
				VcNormalVerifiedDetHis vcNormalVerifiedDetHis = new VcNormalVerifiedDetHis();
				Beans.copy(vcNormalVerifiedDetHis, vcNormalVerifiedDet);
				vcNormalVerifiedDetHis.setIdVcNormalVerifiedDetHis(null);
				vcNormalVerifiedDetHis.setVcNormalVerificationHis(vcNormalVerificationHis);
				vcNormalVerifiedDetHis.setCreatedBy(TakeCode);
				vcNormalVerifiedDetHis.setDateCreated(nowDate);
				vcNormalVerifiedDetHis.setUpdatedBy(TakeCode);
				vcNormalVerifiedDetHis.setDateUpdated(nowDate);
				vcNormalVerifiedDetHisList.add(vcNormalVerifiedDetHis);
			}
			vcNormalVerificationHis.setVcNormalVerifiedDetHis(vcNormalVerifiedDetHisList);
		}
		vcNormalVerificationHis.setIdVcNormalVerificationHis(null);
		if(StringUtils.isBlank(vcNormalVerificationHis.getUseType())){
			vcNormalVerificationHis.setUseType("1");//暂时付默认值
		}
		vcNormalVerificationHis.setCreatedBy(TakeCode);
		vcNormalVerificationHis.setDateCreated(nowDate);
		vcNormalVerificationHis.setUpdatedBy(TakeCode);
		vcNormalVerificationHis.setDateUpdated(nowDate);
		vcNormalVerificationHisList.add(vcNormalVerificationHis);
		//写入历史记录表结束
		//fyh
        if(flag.equals("1")){
        	List<VcAvailableDoc> list= doUsedDao.checkDocNumIsValid(counteractedInvoiceNo, docVerCode, pressBatchNo);
        	if(list.size()==1){
        		VcNormalVerification vcNor=normalVerifications.get(0);
        		List<VcNormalVerifiedDet> vcNorDet=normalVerifications.get(0).getVcNormalVerifiedDet();
        		for (int k=0;k<vcNorDet.size();k++){
        			VcNormalVerifiedDet va=vcNorDet.get(k);
        			va.setCounteractFlag("1");
        		}
        		vcNor.setCreatedBy(TakeCode);
        		vcNor.setUpdatedBy(TakeCode);
        		vcNor.setDateCreated(nowDate);
        		vcNor.setDateUpdated(nowDate);
        		vcNor.setVerifiedTime(nowDate);
        		vcNor.setVcNormalVerifiedDet(vcNorDet);
        		vcNor.setDocNum(counteractedInvoiceNo);
        		vcNor.setCountctedInvoiceCode(counteractedInvoiceCode);
        		vcNor.setCounterActedInvoiceNO(counteractedInvoiceNo);
        		docExecuteUsedDao.deleteAll(list);
        		abnormalVerificationDao.saveAll(abnormalVerifications);
            	normalVerificationDao.update(vcNor);
            	responsebody.setErrorInfo("红冲成功");
    			responsebody.setResultType("000");
    			reponse.setHead(responsehead);
    			reponse.setResult(responsebody);
    			return reponse;
        	}else{
        		throw new  BusinessException("红冲失败"); 
        	}
		}else{
			abnormalVerificationDao.saveAll(abnormalVerifications);
			normalVerificationDao.deleteAll(normalVerifications);
			//保存历史表 add by chy 20130810
			normalVerificationDao.saveAll(vcNormalVerificationHisList);
			responsebody.setErrorInfo("作废成功");
			responsebody.setResultType("000");
			reponse.setHead(responsehead);
			reponse.setResult(responsebody);
			return reponse;
		}
		} catch (Exception e1) {
			responsebody.setErrorInfo("异常信息"+e1.getMessage());
			responsebody.setResultType("999");
			reponse.setHead(responsehead);
			reponse.setResult(responsebody);
			return reponse;
		}
	}
	public NormalVerificationDao getNormalVerificationDao() {
		return normalVerificationDao;
	}
	public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}
	public AbnormalVerificationDao getAbnormalVerificationDao() {
		return abnormalVerificationDao;
	}
	public void setAbnormalVerificationDao(
			AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}
	public inputStorage getInputStorageDao() {
		return inputStorageDao;
	}
	public void setInputStorageDao(inputStorage inputStorageDao) {
		this.inputStorageDao = inputStorageDao;
	}
	public NewInvoiceDocDoAnnuledDao getNewInvoiceDocDoAnnuledDao() {
		return newInvoiceDocDoAnnuledDao;
	}
	public void setNewInvoiceDocDoAnnuledDao(
			NewInvoiceDocDoAnnuledDao newInvoiceDocDoAnnuledDao) {
		this.newInvoiceDocDoAnnuledDao = newInvoiceDocDoAnnuledDao;
	}

	public void setDoUsedDao(NewInvoiceDoUsedDao doUsedDao) {
		this.doUsedDao = doUsedDao;
	}
	public DocExecuteUsedDao getDocExecuteUsedDao() {
		return docExecuteUsedDao;
	}
	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}
	public NewInvoiceDoUsedDao getDoUsedDao() {
		return doUsedDao;
	}
	
}
