/**
 * 
 */
package com.tapi.tcs.vc.visausage.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcLevelDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.InvoiceExportService;
import com.tapi.tcs.vc.schema.model.PubCompany;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcCancel;
import com.tapi.tcs.vc.schema.model.VcCancelDet;
import com.tapi.tcs.vc.schema.model.VcInvoicePrint;
import com.tapi.tcs.vc.schema.model.VcInvoiceRevoke;
import com.tapi.tcs.vc.schema.model.VcNormalVerification;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;
import com.tapi.tcs.vc.visausage.dao.CancelDao;
import com.tapi.tcs.vc.visausage.dao.NormalVerificationDao;
import com.tapi.tcs.vc.visausage.service.CancelService;

/**
 * @author hanmiao.diao
 *
 */
public class CancelServiceImpl implements CancelService {
	
	private CancelDao cancelDao;
	
	private ApproveDao approveDao;
	
	private NormalVerificationDao normalVerificationDao;
	
	private AbnormalVerificationDao abnormalVerificationDao;
	
	private VcStorageDao vcStorageDao;
	
	private TakeNoDao takeNoDao;
	
	private InvoiceExportService invoiceExportService;
	
	private VcLevelDao vcLevelDao;
	
	public InvoiceExportService getInvoiceExportService() {
		return invoiceExportService;
	}

	public void setInvoiceExportService(InvoiceExportService invoiceExportService) {
		this.invoiceExportService = invoiceExportService;
	}

	public TakeNoDao getTakeNoDao() {
		return takeNoDao;
	}

	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}

	public AbnormalVerificationDao getAbnormalVerificationDao() {
		return abnormalVerificationDao;
	}

	public void setAbnormalVerificationDao(
			AbnormalVerificationDao abnormalVerificationDao) {
		this.abnormalVerificationDao = abnormalVerificationDao;
	}

	public NormalVerificationDao getNormalVerificationDao() {
		return normalVerificationDao;
	}

	public void setNormalVerificationDao(NormalVerificationDao normalVerificationDao) {
		this.normalVerificationDao = normalVerificationDao;
	}

	public CancelDao getCancelDao() {
		return cancelDao;
	}

	public void setCancelDao(CancelDao cancelDao) {
		this.cancelDao = cancelDao;
	}
	
	public ApproveDao getApproveDao() {
		return approveDao;
	}

	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}

	public VcStorageDao getVcStorageDao() {
		return vcStorageDao;
	}

	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}
	
	public void setVcLevelDao(VcLevelDao vcLevelDao) {
		this.vcLevelDao = vcLevelDao;
	}

	/* (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#saveCancelS(com.tapi.tcs.vc.schema.model.VcCancel)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String saveCancelS(VcCancel cancel) throws BusinessException {
		/**
		 * 校验每个输入域是否符合规则；
		 * 校验单证类型代码不可重复；
		 * 如果为库存作废，则：从VC_STORAGE（单证库存表）里使用如下查询条件进行查询：
		 * 单证类型代码＋印刷批次＋作废号段的所有单证流水号＋当前操作员归属机构＋单证状态（＝S1、S2或S3），
		 * 如果查询结果>0，则校验通过；
		 */
		try{
			List<VcCancelDet> vcCancelDets = cancel.getVcCancelDets();
			List<VcCancelDet> vcCancelDetsClone = new ArrayList<VcCancelDet>();
			
			if(vcCancelDets!=null && vcCancelDets.size()>0) {
				for (Iterator it = vcCancelDets.iterator(); it.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					
					String start=vcCancelDet.getStartNum();
					String end = vcCancelDet.getEndNum();
					String docVerCode = vcCancelDet.getDocVerCode();
					String pressBatchNo = vcCancelDet.getPressBatchNo();
					String docStatus = vcCancelDet.getDocStatus();
					String orgCode = cancel.getCancelOrgCode();
					
					int i= vcStorageDao.isExist(start, end, docVerCode, pressBatchNo, docStatus, orgCode);
					if(i == 0) {
						throw new BusinessException("单证类型代码[" + docVerCode + "]作废段号区间["+start+","+end+"]状态["+docStatus+"]不符" );
					}
				}
			} else {
				throw new BusinessException("作废申请明细为空");
			}
			
			// 按照上面的注释对明细每行进行校验
			Long id = cancel.getIdVcCancel();
			Date sysdate = new Date();
			if(id==null) {
				// 作废业务表存储
				String cancelCode=null;
				try {
					cancelCode = takeNoDao.getNo("DC");
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("生成作废单号出错");
				}
				cancel.setCancelCode(cancelCode);
				cancel.setVcCancelDets(null);
				cancel.setDateCreated(sysdate);
				cancel.setDateUpdated(sysdate);
				cancel.setModifyTime(sysdate);
				cancel.setModifyOprCode(cancel.getUpdatedBy());
				cancelDao.insertVcCancel(cancel);
			} else {
				VcCancel dbObject = cancelDao.get(id);
				
				dbObject.setCancelStatus(cancel.getCancelStatus());
				dbObject.setCancelReason(cancel.getCancelReason());
				dbObject.setVcCancelDets(null);
				dbObject.setDateUpdated(sysdate);
				dbObject.setModifyOprCode(cancel.getUpdatedBy());
				dbObject.setModifyTime(sysdate);
				
				cancel.setCreatedBy(dbObject.getCreatedBy());
				cancel.setDateCreated(dbObject.getDateCreated());
				cancel.setUpdatedBy(dbObject.getUpdatedBy());
				cancel.setDateUpdated(dbObject.getDateUpdated());
				// 
				cancelDao.update(dbObject);
				// 删除已存在的作废申请子表记录
				cancelDao.deleteVcCancelDetByVcCancelId(id);
			}
			// 插入页面录入的多行记录
			for (Iterator it = vcCancelDets.iterator(); it.hasNext();) {
				VcCancelDet vcCancelDet = (VcCancelDet) it.next();
				VcCancelDet temp = new VcCancelDet();
				Beans.copy(temp, vcCancelDet);
				temp.setIdVcCancel(cancel.getIdVcCancel());
				temp.setIdVcCancelDet(null);
				
				temp.setCreatedBy(cancel.getCreatedBy());
				temp.setDateCreated(cancel.getDateCreated());
				temp.setUpdatedBy(cancel.getUpdatedBy());
				temp.setDateUpdated(cancel.getDateUpdated());
				
				vcCancelDetsClone.add(temp);
			}
			
			cancelDao.saveAll(vcCancelDetsClone);
			
			if("2".equals(cancel.getCancelStatus())) {  // 将2改写
				
				saveVcApproveWhenApply(cancel);
				
				// 2按规则进行拆分,对每个单证类型进行拆分,如果其中一个拆分失败,整体回滚
				
				for (Iterator it = vcCancelDets.iterator(); it
						.hasNext();) {
					VcCancelDet vcCancelDet = (VcCancelDet) it.next();
					String start= vcCancelDet.getStartNum();
					String end = vcCancelDet.getEndNum();
					String docVerCode = vcCancelDet.getDocVerCode();
					String pressBatchNo = vcCancelDet.getPressBatchNo();
					String docStatus = vcCancelDet.getDocStatus();
					String orgCode = cancel.getCancelOrgCode();
					vcStorageDao.splitStorage(start, end, docVerCode, pressBatchNo, orgCode, docStatus, "CT", null,cancel.getUpdatedBy());
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return "操作成功";
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#queryCancelListByPages(java.util.Map, int, int)
	 */
	@Override
	public Page queryCancelListByPages(Map<String, Object> params,
			int page, int rows) throws BusinessException {
		Page result = null;
		try{
			result = cancelDao.findCancelListByPages(params, page, rows);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#getVcCancel(java.lang.Long)
	 */
	@Override
	public VcCancel getVcCancel(Long id) throws BusinessException {
		VcCancel vcCancel = null;
		try{
			vcCancel = cancelDao.get(id);
		}catch(Exception e){
			throw new BusinessException("查询数据失败！", e);
		}
		return vcCancel;
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#deleteByLogic(java.lang.String[], java.lang.String)
	 */
	@Override
	public String deleteByLogic(String[] idArray, String currentUser)
			throws BusinessException {
		StringBuffer sb = new StringBuffer();
		try{
			Date sysdate = new Date();
			for (int i = 0; i < idArray.length; i++) {
				Long id = Long.valueOf(idArray[i]);
				VcCancel vcCancel = cancelDao.get(id);
	
				if("1".equals(vcCancel.getCancelStatus())) { // 改写字符串1
					vcCancel.setCancelStatus("0");
					vcCancel.setUpdatedBy(currentUser);
					vcCancel.setDateUpdated(sysdate);
					try {
						cancelDao.update(vcCancel);
						sb.append("作废单号["+vcCancel.getCancelCode()+"]删除成功");
					} catch (Exception e) {
						e.printStackTrace();
						sb.append("作废单号["+vcCancel.getCancelCode()+"]删除失败");
					}
				} else {
					sb.append("作废单号["+vcCancel.getCancelCode()+"]状态发生变更");
				}
			}
		}catch(Exception e){
			throw new BusinessException("删除数据失败！", e);
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#saveCancelUsage(com.tapi.tcs.vc.schema.model.VcCancel)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String saveCancelUsage(VcCancel cancelUsage) throws BusinessException {
		try{
			List<VcCancelDet> vcCancelDets = cancelUsage.getVcCancelDets();
			List<VcCancelDet> vcCancelDetsClone = new ArrayList<VcCancelDet>();
			// 对明细每行进行校验
			List<VcNormalVerification> vcNormalVerificationList = validateUsageDocStatus(cancelUsage, vcCancelDets);
			
			Long id = cancelUsage.getIdVcCancel();
			Date sysdate = new Date();
			
			if(id==null) {
				// 作废业务表存储
				String cancelCode=null;
				try {
					cancelCode = takeNoDao.getNo("DC");
				} catch (Exception e) {
					e.printStackTrace();
					throw new BusinessException("生成作废单号出错");
				}
				cancelUsage.setCancelCode(cancelCode);
				cancelUsage.setVcCancelDets(null);
				cancelUsage.setDateCreated(sysdate);
				cancelUsage.setDateUpdated(sysdate);
				cancelUsage.setModifyOprCode(cancelUsage.getCreatedBy());
				cancelUsage.setModifyTime(sysdate);			
				cancelDao.insertVcCancel(cancelUsage);
			} else {
				VcCancel dbObject = cancelDao.get(id);
				dbObject.setCancelStatus(cancelUsage.getCancelStatus());
				dbObject.setCancelReason(cancelUsage.getCancelReason());
				dbObject.setVcCancelDets(null);
				
				dbObject.setModifyOprCode(cancelUsage.getUpdatedBy());
				dbObject.setModifyTime(sysdate);
				dbObject.setUpdatedBy(cancelUsage.getUpdatedBy());
				dbObject.setDateUpdated(sysdate);
				
				cancelUsage.setDateCreated(dbObject.getDateCreated());
				cancelUsage.setCreatedBy(dbObject.getCreatedBy());
				cancelUsage.setDateUpdated(sysdate);
				
				// 删除已存在的作废申请子表记录
				cancelDao.deleteVcCancelDetByVcCancelId(id);
				cancelDao.update(dbObject);
			}
			// 插入页面录入的多行记录
			for (Iterator it = vcCancelDets.iterator(); it.hasNext();) {
				VcCancelDet vcCancelDet = (VcCancelDet) it.next();
				VcCancelDet temp = new VcCancelDet();
				Beans.copy(temp, vcCancelDet);
				temp.setIdVcCancel(cancelUsage.getIdVcCancel());
				temp.setIdVcCancelDet(null);
				
				temp.setCreatedBy(cancelUsage.getCreatedBy());
				temp.setDateCreated(cancelUsage.getDateCreated());
				temp.setUpdatedBy(cancelUsage.getUpdatedBy());
				temp.setDateUpdated(cancelUsage.getDateUpdated());
				
				vcCancelDetsClone.add(temp);
			}
			
			cancelDao.saveAll(vcCancelDetsClone);
	
			if("2".equals(cancelUsage.getCancelStatus())) {
				// 1保存公共轨迹表
				saveVcApproveWhenApply(cancelUsage);
				
				// 2 提交成功后，如果为使用作废，更新VC_NORMAL_VERIFICATION（单证正常核销表）表里如下信息
				for (Iterator it = vcNormalVerificationList.iterator(); it
						.hasNext();) {
					VcNormalVerification vcNormalVerification = (VcNormalVerification) it
							.next();
					vcNormalVerification.setDocStatus("CT");
					normalVerificationDao.update(vcNormalVerification);
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(BusinessException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("操作数据库异常！", e);
		}
		return "操作成功";
	}

	/**
	 * 保存公共审批结果
	 * @param vcCancel 业务对象
	 */
	private void saveVcApproveWhenApply(VcCancel vcCancel) throws BusinessException {
		try{
			VcApprove vcApprove = new VcApprove();
			Date sysdate = new Date();
			vcApprove.setApplyId(vcCancel.getIdVcCancel());
			vcApprove.setApplyType("C");
			vcApprove.setCheckExpl("作废申请提交");
			vcApprove.setCheckOprId(vcCancel.getCancelOprCode()); // 
			vcApprove.setCheckOrgId(vcCancel.getCancelOrgCode());
			vcApprove.setCheckStatus("0");        // 将0改写
			vcApprove.setCreatedBy(vcCancel.getUpdatedBy());
			vcApprove.setCheckTime(sysdate);
			
			vcApprove.setDateCreated(sysdate);
			vcApprove.setUpdatedBy(vcCancel.getUpdatedBy());
			vcApprove.setDateUpdated(sysdate);
			
			approveDao.save(vcApprove);
		}catch(Exception e){
			throw new BusinessException("保存数据失败！", e);
		}
	}

	/**
	 * 校验已使用作废申请明细
	 * @param cancelUsage 作废主对象
	 * @param vcCancelDets 作废明细集合
	 * @return 校验结果
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<VcNormalVerification> validateUsageDocStatus(VcCancel cancelUsage, List<VcCancelDet> vcCancelDets) throws BusinessException {
		List<VcNormalVerification> list = new ArrayList<VcNormalVerification>();
		
		/**
		 * 校验每个输入域是否符合规则；
		 * 校验单证类型代码不可重复；
		 * 如果为使用作废，则：从VC_NORMAL_VERIFICATION（单证正常核销表）
		 * 里使用如下查询条件进行查询：单证类型代码＋印刷批次＋作废号段的所有单证流水＋当前操作员归属机构＋单证状态（=U1或U2），
		 * 如果查询结果>0，则校验通过。
		 */
		try{
			Map<String, Object> params = null;
			List subList = null;
			for (Iterator it = vcCancelDets.iterator(); it.hasNext();) {
				VcCancelDet vcCancelDet = (VcCancelDet) it.next();
				String docStatus = vcCancelDet.getDocStatus();
				String docNumStart = vcCancelDet.getStartNum();
				String docNumEnd = vcCancelDet.getEndNum();
				String docVerCode = vcCancelDet.getDocVerCode();
				String pressBatchNo = vcCancelDet.getPressBatchNo();
				String verifiedOrgCode = cancelUsage.getCancelOrgCode(); // 从session中来？
				
				params = new HashMap<String, Object>();
				params.put("verifiedOrgCode", verifiedOrgCode);
				params.put("docVerCode", docVerCode);
				params.put("pressBatchNo", pressBatchNo);
				params.put("docNumStart", docNumStart);
				params.put("docNumEnd", docNumEnd);
				params.put("docStatus", docStatus);
				
				subList = normalVerificationDao.findNormalVerificationList(params);
				list.addAll(subList);
				if(subList!=null && !subList.isEmpty()) {
					int count1 = subList.size();
					int count2 = Integer.valueOf(docNumEnd) - Integer.valueOf(docNumStart) + 1;
					
					if(count1 != count2) {
						throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误");
					}
					
					params = null;
					subList = null;				
				} else {
					throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误");
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#saveCancelApprove(com.tapi.tcs.vc.schema.model.VcCancel)
	 */
	@Override
	public String saveCancelApprove(VcCancel vcCancel) throws BusinessException {
		try{
			Long id= vcCancel.getIdVcCancel();
			Date sysdate = new Date();
			VcCancel dbObject = getVcCancel(id);
			dbObject.setApproveOprCode(vcCancel.getApproveOprCode());
	//		dbObject.setApproveOrgCode(vcCancel.getApproveOrgCode());
			dbObject.setCancelApproveRemark(vcCancel.getCancelApproveRemark());
			dbObject.setUpdatedBy(vcCancel.getUpdatedBy());
			dbObject.setDateUpdated(sysdate);
			
			if(!"2".equals(dbObject.getCancelStatus())) {
				throw new BusinessException("作废单号[" +dbObject.getCancelCode()+ "]状态已发生改变,请重新查询后继续操作");
			} else {
				String cancelApprove = vcCancel.getCancelApprove();
				List<VcCancelDet> detList = dbObject.getVcCancelDets();
				
				String checkState = "";
				String cancelStatus="";
				if("2".equals(cancelApprove)) {
					// 同意
					checkState = "2";
					cancelStatus="4";
					
					if("U".equals(dbObject.getCancelType())) {
						// 已使用
						usageCancelApproveAgree(dbObject, detList, sysdate);
					}
					
					if("S".equals(dbObject.getCancelType())) {
						// 库存
						storageCancelApproveAgree(dbObject, detList, sysdate);
					}
				} else if("1".equals(cancelApprove)) {
					// 不同意
					if(StringUtils.isEmpty(vcCancel.getCancelApproveRemark())) {
						throw new BusinessException("审批退回时审批意见不可以为空");
					}
					checkState = "1";
					cancelStatus="3";
					
					if("U".equals(dbObject.getCancelType())) {
						// 已使用
						usageCancelApproveDisAgree(dbObject, detList, sysdate);
					}
					
					if("S".equals(dbObject.getCancelType())) {
						// 库存
						storageCancelApproveDisAgree(dbObject, detList, sysdate);
					}
				}
				
				//
				saveVcApproveWhenApprove(dbObject, sysdate, checkState);
				
				dbObject.setCancelStatus(cancelStatus);
				dbObject.setApproveTime(sysdate);
				cancelDao.update(dbObject);
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(BusinessException e){
			throw new BusinessException(e.getMessage(), e);
		}catch(Exception e){
			throw new BusinessException("更新数据失败！", e);
		}
		return "审批操作成功";
	}

	/**
	 * 保存公共审批信息
	 * @param dbObject 业务对象
	 * @param sysdate 操作时间
	 * @param checkState 审批结果
	 */
	private void saveVcApproveWhenApprove(VcCancel dbObject, Date sysdate,
			String checkState) throws BusinessException{
		try{
			// 写vc_approve表
			VcApprove vcApprove = new VcApprove();
			vcApprove.setApplyId(dbObject.getIdVcCancel());
			vcApprove.setApplyType("C");
			vcApprove.setCheckExpl(dbObject.getCancelApproveRemark());
			vcApprove.setCheckOprId(dbObject.getApproveOprCode());
			vcApprove.setCheckOrgId(dbObject.getApproveOrgCode());
			vcApprove.setCheckStatus(checkState);
			vcApprove.setCheckTime(sysdate);
			
			vcApprove.setCreatedBy(dbObject.getUpdatedBy());
			vcApprove.setDateCreated(sysdate);
			vcApprove.setUpdatedBy(dbObject.getUpdatedBy());
			vcApprove.setDateUpdated(sysdate);
			
			approveDao.save(vcApprove);
		}catch(Exception e){
			throw new BusinessException("保存数据出错！", e);
		}
	}

	/**
	 * 库存作废审批退回时的操作
	 * @param vcCancel 作废主对象
	 * @param detList 作废明细集合
	 * @param sysdate 操作时间
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	private void storageCancelApproveDisAgree(VcCancel vcCancel,
			List<VcCancelDet> detList, Date sysdate) throws BusinessException {
		/**
		 * 如果是库存作废，更新VC_STORAGE（单证库存表）表里如下信息，另需将原来拆分的号段进行合并
		 */
		try{
			VcCancelDet vcCancelDet=null;
			String orgCode = vcCancel.getCancelOrgCode();
	
			for (Iterator it = detList.iterator(); it.hasNext();) {
				vcCancelDet = (VcCancelDet) it.next();
				String docVerCode= vcCancelDet.getDocVerCode();
				String pressBatchNo =  vcCancelDet.getPressBatchNo();
				String start =  vcCancelDet.getStartNum();
				String end = vcCancelDet.getEndNum();
				String docStatus = "CT";
				VcStorage vcStorage = vcStorageDao.fullEqual(vcCancelDet.getStartNum(), vcCancelDet.getEndNum(), docVerCode, pressBatchNo, docStatus, orgCode);
				if(vcStorage!=null) {
					//1.恢复状态
	//				vcStorage.setDocStatus(vcCancelDet.getDocStatus());
	//				vcStorageDao.update(vcStorage);
	//				步骤1已被包含于步骤2
					//2.合并号段 
					vcStorageDao.mergeStorage(start, end, docVerCode, pressBatchNo, orgCode, docStatus, vcCancelDet.getDocStatus(), vcCancel.getCancelOprCode().toString(),vcStorage.getDeadline());
				} else {
					throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误");
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * 使用作废审批退回时的操作
	 * @param vcCancel 作废主对象
	 * @param detList 作废明细集合
	 * @param sysdate 操作时间
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	private void usageCancelApproveDisAgree(VcCancel vcCancel,
			List<VcCancelDet> detList, Date sysdate) throws BusinessException {
		/**
		 * 如果是使用作废，更新VC_NORMAL_VERIFICATION（单证正常核销表）
		 */
		try{
			Map<String, Object> params=null;
			VcCancelDet vcCancelDet=null;
			String orgCode = vcCancel.getCancelOrgCode();
			for (Iterator it = detList.iterator(); it
					.hasNext();) {
				vcCancelDet = (VcCancelDet) it.next();
				params = new HashMap<String, Object>();
				params.put("verifiedOrgCode", orgCode);
				params.put("docVerCode", vcCancelDet.getDocVerCode());
				params.put("pressBatchNo", vcCancelDet.getPressBatchNo());
				params.put("docNumStart", vcCancelDet.getStartNum());
				params.put("docNumEnd", vcCancelDet.getEndNum());
				params.put("docStatus", "CT");
				List rsList =  normalVerificationDao.findNormalVerificationList(params);
				int count1 = rsList.size();
				int count2 = Integer.valueOf(vcCancelDet.getEndNum()) - Integer.valueOf(vcCancelDet.getStartNum()) + 1;
				if(count1 != count2) {
					throw new BusinessException("单证代码[" + vcCancelDet.getDocVerCode() + "]流水号段的单证状态有误");
				} else {
					for (Iterator iterator = rsList.iterator(); iterator
							.hasNext();) {
						
						VcNormalVerification normal = (VcNormalVerification) iterator.next();
						normal.setDocStatus(vcCancelDet.getDocStatus());
						normal.setUpdatedBy(vcCancel.getUpdatedBy());
						normal.setDateUpdated(sysdate);
					}
					
					try {
						normalVerificationDao.saveAll(rsList);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("更新VC_NORMAL_VERIFICATION发生数据库异常");
					}
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * 使用作废审批同意时的操作
	 * @param vcCancel 作废主对象
	 * @param detList 作废明细集合
	 * @param sysdate 操作时间
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	private void usageCancelApproveAgree(VcCancel vcCancel, List<VcCancelDet> detList,
			Date sysdate) throws BusinessException {
		try{
			Map<String, Object> params=null;
			VcCancelDet vcCancelDet=null;
			List<VcAbnormalVerification> abnormalVerificationList=null;
			String orgCode = vcCancel.getCancelOrgCode();
			String yyyyMM = DateUtils.format(sysdate, "yyyyMM");
			//作废人代码
			String operator = vcCancel.getCancelOprCode();
			//作废人名称
			String operatorName = vcLevelDao.getUnitNameByUnitCode(operator);
			/**
			 * 如果单证状态为“已使用”，
			 * 则：从VC_NORMAL_VERIFICATION（单证正常核销表）里使用如下查询条件进行查询：
			 * 单证类型代码＋印刷批次＋作废号段的所有单证流水＋当前操作员归属机构＋单证状态=（CT），
			 * 如果查询结果>0，则校验通过。
			 */
			for (Iterator it = detList.iterator(); it
					.hasNext();) {
				vcCancelDet = (VcCancelDet) it.next();
				params = new HashMap<String, Object>();
				params.put("verifiedOrgCode", orgCode);
				params.put("docVerCode", vcCancelDet.getDocVerCode());
				params.put("pressBatchNo", vcCancelDet.getPressBatchNo());
				params.put("docNumStart", vcCancelDet.getStartNum());
				params.put("docNumEnd", vcCancelDet.getEndNum());
				params.put("docStatus", "CT");
				List rsList =  normalVerificationDao.findNormalVerificationList(params);
				int count1 = rsList.size();
				int count2 = Integer.valueOf(vcCancelDet.getEndNum()) - Integer.valueOf(vcCancelDet.getStartNum()) + 1;
				if(count1 != count2) {
					throw new BusinessException("单证代码[" + vcCancelDet.getDocVerCode() + "]流水号段的单证状态有误");
				} else {
					abnormalVerificationList = new ArrayList<VcAbnormalVerification>();
					for (Iterator iterator = rsList.iterator(); iterator
							.hasNext();) {
						/*
						 * 如果是使用核销，另需更新VC_NORMAL_VERIFICATION（单证正常核销表）表里数据，
						 * 更新规则，从该表中删除作废的号段。在VC_ABNORMAL_VERIFICATION（单证非正常核销表）
						 * 里插入作废的号段。在对已使用的单证进行手工作废核销时，若作废核销日期（即审批通过时间）
						 * 与单证使用日期在同一个月份，则单证流水号变为作废状态C2，
						 * 该种情况在月报报表中需冲销一个当月已使用核销数量；
						 * 若本次作废核销日期（即审批通过时间）与单证使用日期不在同一个月份，
						 * 则原单证流水号变为作废状态C3，C3状态不计入当月统计，只用于查询
						 */
						
						VcNormalVerification normal = (VcNormalVerification) iterator.next();
						Date verifiedTime =  normal.getVerifiedTime();
						VcAbnormalVerification abNormal = new VcAbnormalVerification();
						Beans.copy(abNormal, normal);
						
						abNormal.setCreatedBy(vcCancel.getUpdatedBy());
						abNormal.setDateCreated(sysdate);
						abNormal.setUpdatedBy(vcCancel.getUpdatedBy());
						abNormal.setDateUpdated(sysdate);
						if(verifiedTime!=null) {
							String yyyyMM1 = DateUtils.format(verifiedTime, "yyyyMM");
							if(yyyyMM.endsWith(yyyyMM1)) {
								abNormal.setDocStatus("C2");
							} else {
								abNormal.setDocStatus("C3");
							}
							abNormal.setType("C");
						} else {
							throw new BusinessException("单证正常核销表[" + normal.getDocNum() +"]数据异常");
						}
						//******add by chenyi,对地税平台的处理 begin**********
						VcInvoicePrint vcInvoicePrint = abnormalVerificationDao.findVcInvoicePrint(normal.getDocVerCode(),
								normal.getPressBatchNo(), normal.getDocNum());
						if(vcInvoicePrint!=null && orgCode.length()>=4){
							//浙江地区、大连、河南、重庆、山西地区、黑龙江、航保中心
							if(SysConst.COMCODE_ZJ.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_DL.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_HeNan.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_ChongQing.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_SX.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_SHANGHAI.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_HLJ.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_HANGBAO.equals(orgCode.substring(0, 4))){
								//如果打印信息已上传平台，则作废重新生成一条数据上传平台
								if("1".equals(vcInvoicePrint.getIsUploadPlat())){
									VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,sysdate);
									vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
									//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
									vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
									vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
									vcInvoicePrintNew.setCanceldDate(sysdate);//作废时间
									vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
									vcInvoicePrintNew.setStatus("1");//有效
									abnormalVerificationDao.save(vcInvoicePrintNew);
								}else{
								//如果打印信息未上传平台，则直接上传作废信息。即替换掉打印信息
									VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,sysdate);
									vcInvoicePrint.setStatus("0");
									vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
									//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
									vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
									vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
									vcInvoicePrintNew.setCanceldDate(sysdate);//作废时间
									vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
									vcInvoicePrintNew.setStatus("1");//有效
									abnormalVerificationDao.update(vcInvoicePrint);
									abnormalVerificationDao.save(vcInvoicePrintNew);
								}
							}
							//厦门地区、天津地区
							else if(SysConst.COMCODE_XM.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_TJ.equals(orgCode.substring(0,4))){
								//1、打印已上传，则不传作废；
								//2、打印、作废都未上传，则上传作废；
								if(!"1".equals(vcInvoicePrint.getIsUploadPlat())){
									VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,sysdate);
									vcInvoicePrint.setStatus("0");
									vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
									//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
									vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
									vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
									vcInvoicePrintNew.setCanceldDate(sysdate);//作废时间
									vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
									vcInvoicePrintNew.setStatus("1");//有效
									abnormalVerificationDao.update(vcInvoicePrint);
									abnormalVerificationDao.save(vcInvoicePrintNew);
								}
							}
							//江苏地区、贵州地区
							else if(SysConst.COMCODE_JS.equals(orgCode.substring(0, 4))
									||SysConst.COMCODE_GZ.equals(orgCode.substring(0, 4))){
								//不管是否上传平台，都重新生成一条作废数据
								VcInvoicePrint vcInvoicePrintNew = invoiceExportService.copyVcInvoicePrint(vcInvoicePrint,operator,sysdate);
								vcInvoicePrintNew.setPrintKind("3");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
								//vcInvoicePrintNew.setCanceledOpr(operator);//作废人
								vcInvoicePrintNew.setCanceledOprCode(operator);//作废人代码
								vcInvoicePrintNew.setCanceledOpr(operatorName);//作废人名称
								vcInvoicePrintNew.setCanceldDate(sysdate);//作废时间
								vcInvoicePrintNew.setIsUploadPlat("0");//未上传平台
								vcInvoicePrintNew.setStatus("1");//有效
								abnormalVerificationDao.save(vcInvoicePrintNew);
							}
						}
						//******add by chenyi,对地税平台的处理 end**********
						
						abnormalVerificationList.add(abNormal);
					}
					
					try {
						abnormalVerificationDao.saveAll(abnormalVerificationList);
						normalVerificationDao.deleteAll(rsList);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("更新VC_NORMAL_VERIFICATION,VC_ABNORMAL_VERIFICATION发生数据库异常");
					}
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/**
	 * 库存作废审批同意时的操作
	 * @param vcCancel 作废主对象
	 * @param detList 作废明细集合
	 * @param sysdate 操作时间
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	private void storageCancelApproveAgree(VcCancel vcCancel,
			List<VcCancelDet> detList, Date sysdate) throws BusinessException {
		try{
			VcCancelDet vcCancelDet=null;
			String orgCode = vcCancel.getCancelOrgCode();
			List<VcAbnormalVerification> abNormalList = null;
			/**
			 * 如果单证状态为“库存”，则：从VC_STORAGE（单证库存表）里使用如下查询条件进行查询：
			 * 单证类型代码＋印刷批次＋作废号段的所有单证流水号＋当前操作员归属机构＋单证状态（＝CT），
			 * 如果查询结果>0，则校验通过
			 */
			//作废人代码
			String operator = vcCancel.getCancelOprCode();
			//作废人名称
			String operatorName = vcLevelDao.getUnitNameByUnitCode(operator);
			
			for (Iterator it = detList.iterator(); it.hasNext();) {
				vcCancelDet = (VcCancelDet) it.next();
				String docVerCode= vcCancelDet.getDocVerCode();
				String pressBatchNo =  vcCancelDet.getPressBatchNo();
				String start =  vcCancelDet.getStartNum();
				String end = vcCancelDet.getEndNum();
				String docStatus = "CT";
				Long start_ =  Long.valueOf(start);
				Long end_ =  Long.valueOf(end);
				int i = vcStorageDao.isExist(start, end, docVerCode, pressBatchNo, docStatus, orgCode);
				if(i>0) {
					/**
					 * 如果是库存核销，从VC_STORAGE里删除状态为CT（作废中）的记录，
					 * 另需在VC_ABNORMAL_VERIFICATION（单证非正常核销表）里插入作废的号段。
					 */
					try {
						vcStorageDao.deleteVcStorage(vcCancelDet.getStartNum(), vcCancelDet.getEndNum(), docVerCode, pressBatchNo, docStatus, orgCode);
						
						abNormalList = new ArrayList<VcAbnormalVerification>();
						VcAbnormalVerification abNormalV = null;
						String tempDocNum=null;
						//格式化流水号为10位
				        DecimalFormat df = new DecimalFormat("0");
				        df.setMinimumIntegerDigits(vcCancelDet.getEndNum().length());
				        //通过单证类型判断是否为发票
						boolean isInvoice = abnormalVerificationDao.checkDocType(docVerCode);
						
						for(long j = start_; j<=end_; j++) {
							abNormalV = new VcAbnormalVerification();
							abNormalV.setBusinessNo("");
							//abNormalV.setDocNum(String.valueOf(j));
							//长度格式化
							abNormalV.setDocNum(df.format(j));
							abNormalV.setDocStatus("C1");
							abNormalV.setDocVerCode(docVerCode);
							abNormalV.setPressBatchNo(pressBatchNo);
							abNormalV.setType("C");
							abNormalV.setVerifiedOprCode(vcCancel.getCancelOprCode());
							abNormalV.setVerifiedOrgCode(vcCancel.getCancelOrgCode());
							abNormalV.setVerifiedTime(sysdate);
							
							abNormalV.setCreatedBy(vcCancel.getUpdatedBy());
							abNormalV.setDateCreated(sysdate);
							abNormalV.setUpdatedBy(vcCancel.getUpdatedBy());
							abNormalV.setDateUpdated(sysdate);
							abNormalList.add(abNormalV);
							
							//******add by chenyi,对地税平台的处理 begin**********
							if(isInvoice && orgCode.length()>=4){
								//浙江、山西、河南、重庆、天津、黑龙江
								if(SysConst.COMCODE_ZJ.equals(orgCode.substring(0,4))
										||SysConst.COMCODE_SX.equals(orgCode.substring(0, 4))
										||SysConst.COMCODE_HeNan.equals(orgCode.substring(0, 4))
										||SysConst.COMCODE_ChongQing.equals(orgCode.substring(0, 4))
										||SysConst.COMCODE_TJ.equals(orgCode.substring(0, 4))
										||SysConst.COMCODE_HLJ.equals(orgCode.substring(0, 4))){
									String TaxpayerName = "";//纳税人名称
									String TaxpayerCode = "";//纳税人识别号
									PubCompany PubCompany = abnormalVerificationDao.queryPubCompany(orgCode);
									TaxpayerCode = abnormalVerificationDao.queryPubCompany(orgCode).getTaxNumber();
									TaxpayerName = abnormalVerificationDao.queryPubCompany(orgCode).getCompanyCname();
									VcInvoicePrint vcInvoicePrint = new VcInvoicePrint();
									vcInvoicePrint.setTaxpayerName(TaxpayerName);
									vcInvoicePrint.setTaxpayerCode(TaxpayerCode);
									vcInvoicePrint.setDocVerCode(docVerCode);
									vcInvoicePrint.setOrgCode(orgCode);
									vcInvoicePrint.setInvoiceCode(pressBatchNo);
									vcInvoicePrint.setInvoiceNo(df.format(j));
									vcInvoicePrint.setPrintDate(sysdate);
									vcInvoicePrint.setPayerName("空白作废");
									vcInvoicePrint.setAmount(new BigDecimal("0.00"));
									vcInvoicePrint.setPrintKind("4");//开具类型：1-正常；2-负数；3-作废；[4-空白作废(系统中作废使用)]
									vcInvoicePrint.setIsUploadPlat("0");
									vcInvoicePrint.setStatus("1");
									//vcInvoicePrint.setCanceledOpr(vcCancel.getCancelOprCode());
									vcInvoicePrint.setRecipientName(operatorName);//收款人名称
									vcInvoicePrint.setCanceledOprCode(operator);//作废人代码
									vcInvoicePrint.setCanceledOpr(operatorName);//作废人名称
									vcInvoicePrint.setCanceldDate(sysdate);
									vcInvoicePrint.setCreatedBy(vcCancel.getUpdatedBy());									
									vcInvoicePrint.setDateCreated(sysdate);
									vcInvoicePrint.setUpdatedBy(vcCancel.getUpdatedBy());
									vcInvoicePrint.setDateUpdated(sysdate);
									abnormalVerificationDao.save(vcInvoicePrint);
								}
								//厦门暂不做处理
								/*else if(SysConst.COMCODE_XM.equals(orgCode.substring(0,4))){
									
								}*/
							}
						}
						if(isInvoice && orgCode.length()>=4){
							//上海、福建地区：库存作废做缴销处理
							if(SysConst.COMCODE_SHANGHAI.equals(orgCode.substring(0,4))
									||SysConst.COMCODE_HANGBAO.equals(orgCode.substring(0, 4))
									//modify by zhxiao3 VC-119福建发票缴销功能完善 begin
									||SysConst.COMCODE_FJ.equals(orgCode.substring(0,4))){
									//modify by zhxiao3 VC-119福建发票缴销功能完善 end
								VcInvoiceRevoke vcInvoiceRevoke = new VcInvoiceRevoke();
								vcInvoiceRevoke.setDocVerCode(docVerCode);//单证类型代码
								vcInvoiceRevoke.setInvoiceCode(pressBatchNo);//发票代码
								vcInvoiceRevoke.setStartNum(start);//起始号码
								vcInvoiceRevoke.setEndNum(end);//终止号码
								vcInvoiceRevoke.setQuantity(vcCancelDet.getCancelNum());//发票分数
								vcInvoiceRevoke.setRevokeOprCode(operator);//缴销人代码
								vcInvoiceRevoke.setRevokeOprName(operatorName);//缴销人名称
								vcInvoiceRevoke.setStatus("1");//有效
								vcInvoiceRevoke.setIsUploadPlat("0");//未上传平台
								vcInvoiceRevoke.setRevokeType("50");//缴销类型代码：50-空白发票；70-遗失发票
								vcInvoiceRevoke.setRevokeCode("4");//缴销情形代码
								vcInvoiceRevoke.setOrgCode(orgCode);//缴销机构代码
								vcInvoiceRevoke.setLostDate(null);//遗失日期
								vcInvoiceRevoke.setRegisterDate(sysdate);//登记日期：审批通过日期
								vcInvoiceRevoke.setCreatedBy(vcCancel.getUpdatedBy());
								vcInvoiceRevoke.setDateCreated(sysdate);
								vcInvoiceRevoke.setUpdatedBy(vcCancel.getUpdatedBy());
								vcInvoiceRevoke.setDateUpdated(sysdate);
								abnormalVerificationDao.save(vcInvoiceRevoke);
							}
						}
						//******add by chenyi,对地税平台的处理 end**********
						
						abnormalVerificationDao.saveAll(abNormalList);
					} catch (Exception e) {
						e.printStackTrace();
						throw new BusinessException("保存数据出错！", e);
					}
				} else {
					throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误");
				}
			}
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.tapi.tcs.vc.visausage.service.CancelService#getVcApprove(java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List getVcApprove(Long id, String applyType)	throws BusinessException {
		List list = null;
		try{
			list = approveDao.queryApproves(applyType, id);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}

}
