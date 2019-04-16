package com.tapi.tcs.vc.webservice.provider.newCancelInvoice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapi.tcs.common.webservice.HeadDTO;
import com.tapi.tcs.common.webservice.ResponseDTO;
import com.tapi.tcs.common.webservice.StorageResponse;
import com.tapi.tcs.common.webservice.StorageResponseDTO;
import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.webservice.provider.inStorage.dao.inputStorage;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequest;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.bean.CancelInvoiceRequestBody;
import com.tapi.tcs.vc.webservice.provider.newCancelInvoice.dao.CancelInvoiceDao;
import com.tapi.tcs.vc.webservice.provider.newInvoiceDoUsed.dao.NewInvoiceDoUsedDao;
import com.tapi.tcs.vc.webservice.provider.outStorage.dao.OutStorageDao;

public class VcCancelInvoiceServiceImpl implements VcCancelInvoiceService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private VcStorageDao vcStorageDao;
	private inputStorage inputStorageDao;
	private AbnormalVerificationDao abnormalVerificationDao;
	 private OutStorageDao outStorageDao;
	 private NewInvoiceDoUsedDao doUsedDao;
     private CancelInvoiceDao cancelInvoiceDaos;
	@Override//空白发票作废
	public StorageResponse saveVcCancelInvoice(CancelInvoiceRequest cancelStorage) throws DaoException {
		logger.error("进入到saveVcCancelInvoice方法");
		CancelInvoiceRequestBody requestBody= cancelStorage.getBody();
		HeadDTO head=cancelStorage.getHeadDTO();
		ResponseDTO responsehead=new ResponseDTO();
		responsehead.setfLOWINTIME(head.getfLOWINTIME());
		responsehead.setrEQSERIALNO(head.getrEQSERIALNO());
		responsehead.setrEQUESTTYPE(head.getrEQUESTTYPE());
		StorageResponseDTO responsebody=new StorageResponseDTO();
		String docVerCode=null;
		Date date=new Date();
		StorageResponse reponse =new StorageResponse();
		StringBuffer errors=new StringBuffer();
		String pressBatchNo=requestBody.getPressBatchNo();
		String docNum=requestBody.getDocNum();//发票号
		String canceTakeCode=requestBody.getCanceTakeCode();//作废人代码
		String takeOrgcode=requestBody.getTakeOrgcode();//所属机构
		String creatcode;
		String org;
		List<VcAbnormalVerification> vcAbnormalVerifications = new ArrayList<VcAbnormalVerification>();
		try {
			 creatcode=inputStorageDao.queryUserInfo(canceTakeCode, "2",takeOrgcode);
			 org=inputStorageDao.queryUserInfo(canceTakeCode, "4", null);
			 docVerCode = inputStorageDao.queryDocverCode(requestBody.getDocVerCode());
			 logger.error("转化后的单证类型"+docVerCode);
			 List<VcAvailableDoc> vcAvailableDocList=doUsedDao.checkDocNumIsValid(docNum, docVerCode, pressBatchNo);
			  if(vcAvailableDocList.size()== 1){
				  VcAbnormalVerification abnormal=new VcAbnormalVerification();
					abnormal.setDocVerCode(vcAvailableDocList.get(0).getDocVerCode());
					abnormal.setPressBatchNo(vcAvailableDocList.get(0).getPressBatchNo());
					abnormal.setDocNum(docNum);
					abnormal.setBusinessNo("");
					abnormal.setDocStatus("C1");
					abnormal.setVerifiedOprCode(creatcode);
					abnormal.setVerifiedOrgCode(vcAvailableDocList.get(0).getOrgCode());
					abnormal.setVerifiedTime(date);
					abnormal.setType("C");
					abnormal.setCreatedBy(creatcode);
					abnormal.setDateCreated(date);
					abnormal.setUpdatedBy(creatcode);
					abnormal.setDateUpdated(date);
					abnormal.setRelBusinessNo("");
					abnormal.setCOUNTERACTEDINVOICECODE("");
					abnormal.setCOUNTERACTEDINVOICENO("");
					vcAbnormalVerifications.add(abnormal);
					cancelInvoiceDaos.deleteVcAvailableDocList(vcAvailableDocList);
					abnormalVerificationDao.saveAll(vcAbnormalVerifications);
					responsebody.setErrorInfo("作废成功");
				    responsebody.setResultType("000");
					reponse.setResult(responsebody);
					reponse.setHead(responsehead);
					}else{
						throw new DaoException("使用人名下无对应发票号");
			  }
		} catch (DaoException e) {
			throw new DaoException(e.getMessage());
		} catch (Exception e) {
			logger.error("异常信息-->"+e);
			throw new DaoException(e.getMessage());
		}
		return reponse;
	}
	public VcStorageDao getVcStorageDao() {
		return vcStorageDao;
	}
	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}
	public inputStorage getInputStorageDao() {
		return inputStorageDao;
	}
	public void setInputStorageDao(inputStorage inputStorageDao) {
		this.inputStorageDao = inputStorageDao;
	}
	public AbnormalVerificationDao getAbnormalVerificationDao() {
		return abnormalVerificationDao;
	}
	public void setAbnormalVerificationDao(
			AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}
	public OutStorageDao getOutStorageDao() {
		return outStorageDao;
	}
	public void setOutStorageDao(OutStorageDao outStorageDao) {
		this.outStorageDao = outStorageDao;
	}
	public NewInvoiceDoUsedDao getDoUsedDao() {
		return doUsedDao;
	}
	public void setDoUsedDao(NewInvoiceDoUsedDao doUsedDao) {
		this.doUsedDao = doUsedDao;
	}
	public CancelInvoiceDao getCancelInvoiceDaos() {
		return cancelInvoiceDaos;
	}
	public void setCancelInvoiceDaos(CancelInvoiceDao cancelInvoiceDaos) {
		this.cancelInvoiceDaos = cancelInvoiceDaos;
	}
	
}
