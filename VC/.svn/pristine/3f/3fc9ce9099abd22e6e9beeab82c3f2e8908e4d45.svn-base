package com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.service;

import java.io.IOException;
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
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcNormalVerifiedDet;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.common.dao.VcDocStatusDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsed.dao.DocDoUsedDao;
import com.tapi.tcs.vc.webservice.provider.docDoUsedOld.dao.DocExecuteUsedDao;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.NewInvoiceDoUsedRequest;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.NewInvoiceDoUsedRequestDTO;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.bean.PolicyDTO;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.dao.NewInvoiceDoUsedDao;

public class VcNewInvoiceDoUsedServiceImpl implements  VcNewInvoiceDoUsedService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private inputStorage inputStorageDao;
	private NewInvoiceDoUsedDao doUsedDao;
	private DocExecuteUsedDao docExecuteUsedDao;
	private NormalVerificationDao normalVerificationDao;
	@Override
	public StorageResponse executeInvoiceDoUsedService(NewInvoiceDoUsedRequest request) throws DaoException {
		HeadDTO head=request.getHeadDTO();
		String headType=head.getrEQUESTTYPE();
		String headNo=head.getrEQSERIALNO();
		String headTime=head.getfLOWINTIME();
		ResponseDTO responseHead=new ResponseDTO();
		responseHead.setfLOWINTIME(headTime);
		responseHead.setrEQSERIALNO(headNo);
		responseHead.setrEQUESTTYPE(headType);
		StorageResponse response=new StorageResponse();
		StorageResponseDTO responsebody=new StorageResponseDTO();
		NewInvoiceDoUsedRequestDTO requestBody=request.getRequestBody();
		String docVerCode=null;
		List<VcNormalVerification> saveList = new ArrayList<VcNormalVerification>();
		List<VcAvailableDoc> delList = new ArrayList<VcAvailableDoc>();
		try {	
			 docVerCode=inputStorageDao.queryDocverCode(requestBody.getDocVerCode()) ;//单证类型
			 	logger.error("转化后的单证类型"+docVerCode);
			String pressBatchNo=requestBody.getPressBatchNo();//发票代码
			String docNum=requestBody.getDocNum();//发票号
    		String orgCode="";//核销机构代码
			String OprCode =requestBody.getVerifiedOprCode();//得到的核销操作人 代码example: zhchundi
			String verifiedOprCode="";
			if(inputStorageDao.resultType(docVerCode)){
				orgCode=requestBody.getOrgCode();//核销机构代码
				verifiedOprCode=inputStorageDao.queryUserInfo(OprCode, "5", null);	
			}else{
				 verifiedOprCode=inputStorageDao.queryUserInfo(OprCode, "1",orgCode);//核销操作人代码
				 orgCode=inputStorageDao.queryUserInfo(OprCode, "3", null);
			}
			String verifiedReason=requestBody.getVerifiedReason();//开具原因
			//红冲标志:0-非红冲；1-红冲；
			String counteractFlag=requestBody.getCounteractFlag();//红冲标志
			if(StringUtils.isEmpty(counteractFlag)){
				counteractFlag = "0";
			}
			String counteractedInvoiceCode=requestBody.getCounteractedInvoiceCode();//被冲红的发票代码
			
			String counteractedInvoiceNo=requestBody.getCounteractedInvoiceNo();//冲红的发票号码
			
			//查找可使用表内库存是否还存在
			List<VcAvailableDoc> vcAvailableDocList=doUsedDao.checkDocNumIsValid(docNum, docVerCode, pressBatchNo);
			if (null==vcAvailableDocList || vcAvailableDocList.size() < 1) {
				throw new DaoException("核销失败，单证流水号不可用！");
			}
			// 判断是否已经核销
			List<VcNormalVerifiedDet> vcNormalVerifiedDetList = new ArrayList<VcNormalVerifiedDet>();
			List<PolicyDTO> list=requestBody.getPolicyDTO();
			VcNormalVerification vcNormalVerifications = null;
			for(int i=0;i<list.size();i++){
				PolicyDTO  policy=list.get(i);
				String businessNo=policy.getPolicyNo();//保单号
				String payNo=policy.getPayNo();//缴费期次
				String endorNo=policy.getEndorNo();//批改序号
				//判断一下保单是否已经核销 只根据发票号，发票类型，印刷号
				vcNormalVerifications = normalVerificationDao.findVcNormalVerification(docVerCode, docNum, pressBatchNo, null);
				if (vcNormalVerifications!=null ) {
					responsebody.setResultType("999");
					responsebody.setErrorInfo("核销失败，业务号已核销！");
					response.setResult(responsebody);
					response.setHead(responseHead);
				}else{
					VcNormalVerifiedDet vcNormalVerifiedDet = new VcNormalVerifiedDet();
					vcNormalVerifiedDet.setBusinessNo(businessNo);
					vcNormalVerifiedDet.setRelBusinessNo("");
					vcNormalVerifiedDet.setPayNo(payNo);
					vcNormalVerifiedDet.setCounteractFlag(counteractFlag);//红冲标志
					vcNormalVerifiedDet.setBatchNo("");//拆分批次
					vcNormalVerifiedDet.setCreatedBy(verifiedOprCode);
					vcNormalVerifiedDet.setDateCreated(new Date());
					vcNormalVerifiedDet.setUpdatedBy(verifiedOprCode);
					vcNormalVerifiedDet.setDateUpdated(new Date());
					vcNormalVerifiedDet.setCorrectionNo(endorNo);//批改序号
					vcNormalVerifiedDetList.add(vcNormalVerifiedDet);
				}
			}
			VcAvailableDoc vcAvailableDoc = vcAvailableDocList.get(0);
			VcNormalVerification vcNormalVerification = new VcNormalVerification();
			vcNormalVerification.setDocVerCode(docVerCode);
			vcNormalVerification.setDocNum(docNum);
			vcNormalVerification.setPressBatchNo(pressBatchNo);
			//取第一个业务号
			vcNormalVerification.setBusinessNo(list.get(0).getPolicyNo());
			vcNormalVerification.setDocStatus("U1");
			vcNormalVerification.setVerifiedOprCode(verifiedOprCode);
			vcNormalVerification.setVerifiedOrgCode(orgCode);
			vcNormalVerification.setVerifiedTime(new Date());
			// 核销原因（1：打印）
			vcNormalVerification.setVerifiedReason("1");
			vcNormalVerification.setCreatedBy(verifiedOprCode);
			vcNormalVerification.setDateCreated(new Date());
			vcNormalVerification.setUpdatedBy(verifiedOprCode);
			vcNormalVerification.setDateUpdated(new Date());
			vcNormalVerification.setCountctedInvoiceCode(counteractedInvoiceCode);
			vcNormalVerification.setCounterActedInvoiceNO(counteractedInvoiceNo);
			//批单对应的保单号  取第一个
				if (!StringUtils.isEmpty(list.get(0).getPolicyNo())){
					vcNormalVerification.setRelBusinessNo(list.get(0).getPolicyNo());
				}
				vcNormalVerification.setVcNormalVerifiedDet(vcNormalVerifiedDetList);
				//设置明细表和主表的关联关系
				for(VcNormalVerifiedDet vcNormalVerifiedDets:vcNormalVerifiedDetList){
					vcNormalVerifiedDets.setVcNormalVerification(vcNormalVerification);
				}
				saveList.add(vcNormalVerification);
				delList.add(vcAvailableDoc);
				docExecuteUsedDao.saveVcNormalVerificationList(saveList);
				// 删除可使用单证表
				docExecuteUsedDao.deleteVcAvailableDocList(delList);
		} catch (Exception e) {
			logger.info("单证核销失败" + e.getMessage()+e);
			throw new DaoException("核销失败"+e.getMessage());
		}
		responsebody.setResultType("000");
		responsebody.setErrorInfo("核销成功！");
		response.setResult(responsebody);
		response.setHead(responseHead);
		return response;
	}
	public void setInputStorageDao(inputStorage inputStorageDao) {
		this.inputStorageDao = inputStorageDao;
	}
	public void setDoUsedDao(NewInvoiceDoUsedDao doUsedDao) {
		this.doUsedDao = doUsedDao;
	}
	public void setDocExecuteUsedDao(DocExecuteUsedDao docExecuteUsedDao) {
		this.docExecuteUsedDao = docExecuteUsedDao;
	}
	public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}
}
