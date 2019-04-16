/**
 * 
 */
package com.tapi.tcs.vc.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.HeilongjiangInvoiceService;
import com.tapi.tcs.vc.schema.model.VcAllot;
import com.tapi.tcs.vc.schema.model.VcAllotDet;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.AllotDao;
import com.tapi.tcs.vc.store.dao.ApplyDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.service.AllotService;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;

/**
 * @author hanmiao.diao
 * 
 */



public class AllotServiceImpl implements AllotService {
	private AllotDao allotDao;
    private static final Logger logger = Logger.getLogger(AllotServiceImpl.class);
    private TakeNoDao takeNoDao;
    private ApproveDao approveDao;
    private VcDocMngRuleDao vcDocMngRuleDao;
    private VcStorageDao vcStorageDao;
    private HeilongjiangInvoiceService heilongjiangInvoiceService;
    private ApplyDao applyDao;
	public ApplyDao getApplyDao() {
		return applyDao;
	}
	public void setApplyDao(ApplyDao applyDao) {
		this.applyDao = applyDao;
	}
	public void setHeilongjiangInvoiceService(
			HeilongjiangInvoiceService heilongjiangInvoiceService) {
		this.heilongjiangInvoiceService = heilongjiangInvoiceService;
	}
	public VcDocMngRuleDao getVcDocMngRuleDao() {
		return vcDocMngRuleDao;
	}
	public void setVcDocMngRuleDao(VcDocMngRuleDao vcDocMngRuleDao) {
		this.vcDocMngRuleDao = vcDocMngRuleDao;
	}
	public TakeNoDao getTakeNoDao() {
		return takeNoDao;
	}
	public void setTakeNoDao(TakeNoDao takeNoDao) {
		this.takeNoDao = takeNoDao;
	}
	public ApproveDao getApproveDao() {
		return approveDao;
	}
	public void setApproveDao(ApproveDao approveDao) {
		this.approveDao = approveDao;
	}
	public AllotDao getAllotDao() {
		return allotDao;
	}
	public void setAllotDao(AllotDao allotDao) {
		this.allotDao = allotDao;
	}
	
	public VcStorageDao getVcStorageDao() {
		return vcStorageDao;
	}
	public void setVcStorageDao(VcStorageDao vcStorageDao) {
		this.vcStorageDao = vcStorageDao;
	}
	
	 @SuppressWarnings("rawtypes")
	    public List getVcApprove(Long id, String appType) throws BusinessException {
	    	try{
	          return approveDao.queryApproves(appType, id);
	    	}catch(DaoException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	    }
	@Override
	public Long localValidStorageNum(String orgCode, String docVerCode) throws BusinessException {
		try{
		   if (StringUtils.isEmpty(orgCode)) {
	            throw new BusinessException("查询单证有效库存当前机构不可以为空 ");
	        }
	        if (StringUtils.isEmpty(docVerCode)) {
	            throw new BusinessException("查询单证有效库存单证类型不可以为空 ");
	        }
		return applyDao.localValidStorageNum(orgCode, docVerCode);
		}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }catch(BusinessException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
	}
	@Override
	public Page queryAllotListByPages(Map<String, Object> params, int pageNo,
			int pageSize) throws BusinessException {
		try {
			return allotDao.queryAllotList(params, pageNo, pageSize);
		} catch (Exception e) {
			 throw new BusinessException(e.getMessage(),e);
		}
	}
	   public void saveProvideConfirm(VcAllot vcProvideConfirm) throws BusinessException {
	        Long id = vcProvideConfirm.getId();
	        Date sysdate = new Date();
	   try{
	        List<VcAllotDet> detList = allotDao.getAllotDetListByVcAllotId(id);
	        // 修改申领单状态
	        VcAllot vcAllot = allotDao.get(id);

	        vcAllot.setConfirmOprCode(vcProvideConfirm.getConfirmOprCode());
	        vcAllot.setAllotStatus(vcProvideConfirm.getAllotStatus());
	        vcAllot.setConfirmTime(sysdate);
	        vcAllot.setUpdatedBy(vcProvideConfirm.getUpdatedBy());
	        vcAllot.setDateUpdated(sysdate);
	        
	        allotDao.update(vcAllot);

	        String approveRs = vcProvideConfirm.getAllotStatus();
	        String checkStatus = "";
	        if (StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_CONFIRMED.equals(approveRs)) {
	            for (Iterator it = detList.iterator(); it.hasNext();) {
	            	VcAllotDet vcAllotDet = (VcAllotDet) it.next();

	                String docVerCode = vcAllotDet.getDocVerCode();
	                String pressBatchNo = vcAllotDet.getPressBatchNo();
	                String docStatus = "PT";
	                VcStorage vcStorage = vcStorageDao.fullEqual(vcAllotDet.getStartNum(), vcAllotDet.getEndNum(),
	                        docVerCode, pressBatchNo, docStatus, vcAllot.getProvideOrgCode());
	                if (vcStorage != null) {
	                    /**
	                     * 更新VC_STORAGE表，更新规则：将VC_STORAGE表申领申请里的发放号段（单证状态=PT）的记录进行更新
	                     */
	                    try {
	                        vcStorage.setOrgCode(vcAllot.getAllotOrgCode());
	                        vcStorage.setModifyTime(sysdate);
	                        vcStorage.setInStoreTime(sysdate);
	                        vcStorage.setModifyUserCode(vcProvideConfirm.getConfirmOprCode());

	                        vcStorageDao.update(vcStorage);
	                        vcStorageDao.mergeStorage(vcAllotDet.getStartNum(), vcAllotDet.getEndNum(), docVerCode,
	                                pressBatchNo, vcAllot.getAllotOrgCode(), docStatus, "S2", vcProvideConfirm
	                                        .getConfirmOprCode(),vcAllotDet.getDeadline());
	                        //add by chy 20131029 黑龙江地税-领用入库时需更新领购表中的机构代码 begin
	                        String allotOrgCode = vcAllot.getAllotOrgCode();
	                        if(allotOrgCode.length()>=4 && SysConst.COMCODE_HLJ.equals(allotOrgCode.substring(0,4))){
	                        	//若申领机构维护了管理编码，则更新领购表机构代码
	                        	VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(allotOrgCode);
	                        	if(vcManageCodeHlj != null){
		                        	VcInvoiceBuyHlj conditions = new VcInvoiceBuyHlj();
		                        	conditions.setDocVerCode(docVerCode);
		                        	conditions.setInvoiceCode(pressBatchNo);
		                        	conditions.setStartNum(vcAllotDet.getStartNum());
		                        	VcInvoiceBuyHlj vcInvoiceBuyHlj = heilongjiangInvoiceService.findVcInvoiceBuyHlj(conditions);
		                        	if(vcInvoiceBuyHlj!=null){
		                        		vcInvoiceBuyHlj.setOrgCode(allotOrgCode);
		                        		vcInvoiceBuyHlj.setOperatorCode(vcAllot.getConfirmOprCode());
		                        		heilongjiangInvoiceService.updateVcInvoiceBuyHlj(vcInvoiceBuyHlj);
		                        	}
	                        	}
	                        }
	                        //add by chy 20131029 黑龙江地税-领用入库时需更新领购表中的机构代码 end
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                        throw new BusinessException("回收审批同意合并号段失败");
	                    }
	                } else {
	                    throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误 ");
	                }
	            }
	            checkStatus = "4";
	        }

	        if (StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_CONFIRMEBACK.equals(approveRs)) {
	            for (Iterator it = detList.iterator(); it.hasNext();) {
	                VcAllotDet vcAllotDet = (VcAllotDet) it.next();

	                String docVerCode = vcAllotDet.getDocVerCode();
	                String pressBatchNo = vcAllotDet.getPressBatchNo();
	                String start = vcAllotDet.getStartNum();
	                String end = vcAllotDet.getEndNum();
	                String docStatus = "PT";
	                VcStorage vcStorage = vcStorageDao.fullEqual(vcAllotDet.getStartNum(), vcAllotDet.getEndNum(),
	                        docVerCode, pressBatchNo, docStatus, vcAllot.getProvideOrgCode());
	                if (vcStorage != null) {
	                    // 1.恢复状态
	                    // vcStorage.setDocStatus(vcCancelDet.getDocStatus());
	                    // vcStorageDao.update(vcStorage);
	                    // 步骤1已被包含于步骤2
	                    // 2.合并号段
	                    vcStorageDao.mergeStorage(start, end, docVerCode, pressBatchNo, vcAllot.getProvideOrgCode(),
	                            docStatus, vcAllotDet.getDocStatus(), vcAllot.getConfirmOprCode(),null);
	                } else {
	                    throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误 ");
	                }
	            }
	            checkStatus = "3";
	        }

	        // 调拨公共审批表
	        VcApprove vcApprove = new VcApprove();
	        vcApprove.setApplyId(vcAllot.getId());
	        vcApprove.setApplyType("B");
	        vcApprove.setCheckExpl(vcProvideConfirm.getBackReason());
	        vcApprove.setCheckOprId(vcAllot.getConfirmOprCode());
	        vcApprove.setCheckOrgId(vcAllot.getAllotOrgCode());
	        vcApprove.setCheckStatus(checkStatus);
	        vcApprove.setCheckTime(new Date());
	        vcApprove.setCreatedBy(vcProvideConfirm.getUpdatedBy());
	        vcApprove.setDateCreated(sysdate);
	        vcApprove.setUpdatedBy(vcProvideConfirm.getUpdatedBy());
	        vcApprove.setDateUpdated(sysdate);

	        approveDao.save(vcApprove);
	    }catch (BusinessException e) {
	        e.printStackTrace();
	        throw new BusinessException(e.getMessage(), e);
	    }catch (Exception e) {
	        e.printStackTrace();
	        throw new BusinessException(e.getMessage(), e);
	    }
	    }
	
	
	
	
	
	
	  @SuppressWarnings("rawtypes")
	  private VcAllot updateVcAllot(VcAllot pageObject, Long id) throws BusinessException {
	        // 先删除子表内容,再重新插入一遍
	    	VcAllot dbObject=null;
	       try{
	        allotDao.deleteAllotDetByVcAllotId(id);

	        // 修改主表信息
	        dbObject = allotDao.get(id);

	        List<String> enabledUpdateList = new ArrayList<String>() {
	            /**
				 * 
				 */
	            private static final long serialVersionUID = -8302230466427965528L;

	            {
	            	  add(StoreStatusValues.VC_ALLOT_STATUS_ALLOT_TEMPSAVE);
	                  add(StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_BACK);
	            }
	        };

	        if (!enabledUpdateList.contains(dbObject.getAllotStatus())) {
	            throw new BusinessException("调拨单号[" + dbObject.getAllotCode() + "]状态已发生变化,请重新查询后继续操作");
	        }

	        dbObject.setModifyOprCode(pageObject.getUpdatedBy());
	        dbObject.setModifyTime(pageObject.getDateUpdated());
	        dbObject.setAllotTime(pageObject.getAllotTime());
	        dbObject.setAllotReason(pageObject.getAllotReason());
	        dbObject.setAllotStatus(pageObject.getAllotStatus());
	        dbObject.setDateUpdated(pageObject.getDateUpdated());
	        dbObject.setUpdatedBy(pageObject.getUpdatedBy());

	        try {
	            allotDao.update(dbObject);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BusinessException("修改VC_Allot记录失败,数据库异常 ",e);
	        }
	        List<VcAllotDet> vcAllotDetList = pageObject.getAllotDetList();

	        for (Iterator it = vcAllotDetList.iterator(); it.hasNext();) {
	            VcAllotDet vcAllotDet = (VcAllotDet) it.next();
	            vcAllotDet.setIdVcallot(dbObject.getId());
	            vcAllotDet.setVcallot(null);
	            vcAllotDet.setCreatedBy(dbObject.getCreatedBy());
	            vcAllotDet.setDateCreated(dbObject.getDateCreated());
	            vcAllotDet.setUpdatedBy(pageObject.getUpdatedBy());
	            vcAllotDet.setDateUpdated(pageObject.getDateUpdated());
	        }

	        try {
	            allotDao.saveAll(vcAllotDetList);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new BusinessException("修改VC_Allot_DET记录失败,数据库异常 ",e);
	        }
	       }catch(DaoException e){
	    	   throw new BusinessException(e.getMessage(),e);
	       }catch(BusinessException e){
	    	   throw new BusinessException(e.getMessage(),e);
	       }
	        return dbObject;
	    }

	@Override
	  public String saveAllot(VcAllot pageObject) throws BusinessException {
        if (logger.isInfoEnabled()) {
            logger.info("saveAllot(VcAllot) - start"); //$NON-NLS-1$
        }
        StringBuffer sb = new StringBuffer();
        Date sysdate = new Date();
        try {
            Long id = pageObject.getId();

            if (id == null) {
                pageObject.setDateCreated(sysdate);
                pageObject.setDateUpdated(sysdate);
                pageObject.setModifyTime(sysdate);
                pageObject.setAllotTime(sysdate);

                pageObject.setModifyOprCode(pageObject.getCreatedBy());

                String AllotCode = saveVcAllot(pageObject);

                sb.append("调拨单号[" + AllotCode + "]创建成功");
            } else {
                logger.info("update Id " + id);
                pageObject.setDateUpdated(sysdate);
                pageObject.setAllotTime(sysdate);

                VcAllot dbObject = updateVcAllot(pageObject, id);

                sb.append("调拨单号[" + dbObject.getAllotCode() + "]修改成功");
            }

            // 如果是调拨申领提交,往公共审批表里写数据
            if (StoreStatusValues.VC_ALLOT_STATUS_ALLOT_AWAITPROVIDE.equals(pageObject.getAllotStatus())) {
                VcApprove vcApprove = new VcApprove();
                vcApprove.setApplyId(pageObject.getId());
                vcApprove.setApplyType("B");
                vcApprove.setCheckExpl("提交");
                vcApprove.setCheckOprId(pageObject.getAllotOprCode());
                vcApprove.setCheckOrgId(pageObject.getAllotOrgCode());
                vcApprove.setCheckStatus(StoreStatusValues.VC_APPROVE_SUBMITAP);
                vcApprove.setCheckTime(new Date());

                vcApprove.setCreatedBy(pageObject.getUpdatedBy());
                vcApprove.setDateCreated(pageObject.getDateUpdated());
                vcApprove.setUpdatedBy(pageObject.getUpdatedBy());
                vcApprove.setDateUpdated(pageObject.getDateUpdated());
                
                approveDao.save(vcApprove);
            }

        }catch (DaoException e) {

        	throw new BusinessException(e.getMessage(),e);
        } catch (BusinessException e) {

        	throw new BusinessException(e.getMessage(),e);
        }

        if (logger.isInfoEnabled()) {
            logger.info("saveAllot(VcAllot) - end"); //$NON-NLS-1$
        }

        return sb.toString();
    }
		
	/**
	 * 提交调拨申请保存到明细表里面   和提交申请表里面
	 * @param pageObject
	 * @return
	 * @throws BusinessException
	 */
	  private String saveVcAllot(VcAllot pageObject) throws BusinessException {
	        String AllotCode = null;
	        try {
	            AllotCode = takeNoDao.getNo("DB");
	        pageObject.setAllotCode(AllotCode);

	        allotDao.save(pageObject);

	        List<VcAllotDet> vcAllotDetList = pageObject.getAllotDetList();
	        for (Iterator it = vcAllotDetList.iterator(); it.hasNext();) {
	            VcAllotDet vcAllotDet = (VcAllotDet) it.next();
	            vcAllotDet.setIdVcallot(pageObject.getId());
	            vcAllotDet.setVcallot(null);
	            vcAllotDet.setCreatedBy(pageObject.getCreatedBy());
	            vcAllotDet.setUpdatedBy(pageObject.getUpdatedBy());
	            vcAllotDet.setDateCreated(pageObject.getDateCreated());
	            vcAllotDet.setDateUpdated(pageObject.getDateUpdated());

	            allotDao.save(vcAllotDet);
	        }
	        } catch (DaoException e) {

	        	throw new BusinessException(e.getMessage(),e);
	        }
	        return AllotCode;
	    }
	@Override
	public VcAllot getVcAllot(Long id) throws BusinessException {
		try{
	          return allotDao.get(id);
	    	}catch(Exception e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	}
	@Override
	  public List<VcAllotDet> getCloneAllotDetListByVcAllotId(Long vcAllotId) throws BusinessException {
        List<VcAllotDet> vcAllotDetList = getAllotDetListByVcAllotId(vcAllotId);
        List<VcAllotDet> cloneList = new ArrayList<VcAllotDet>();
      try{
        for (Iterator it = vcAllotDetList.iterator(); it.hasNext();) {
            VcAllotDet vcAllotDet = (VcAllotDet) it.next();

            VcAllotDet cloneDet = new VcAllotDet();

            Beans.copy(cloneDet, vcAllotDet);
            cloneDet.setIdVcallotDet(null);

            cloneList.add(cloneDet);
        }
      }catch(Exception e){
    	   throw new BusinessException(e.getMessage(),e);
      }
        return cloneList;
    }
	public List<VcAllotDet> getAllotDetListByVcAllotId(Long vcAllotId)
			throws BusinessException {
		try{
	        List<VcAllotDet> vcAllotDetList = allotDao.getAllotDetListByVcAllotId(vcAllotId);
	        return vcAllotDetList;
	    	}catch(DaoException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	}
	@Override
	public int getMaxStoreTime(String docVerCode, String mngType,
			String mngObjectCode) throws BusinessException {
		 try{
	         return  vcDocMngRuleDao.getMaxStoreTime(docVerCode, mngType, mngObjectCode);
	      }catch (DaoException e) {
	         throw new BusinessException(e.getMessage(), e);
	      }
	}
	@Override
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId,
			String docVerCode) throws BusinessException {
		List<VcDocVersionInfo> list = null;
		try{
			list = allotDao.getVcDocVersionInfoList(applyId, docVerCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	@Override
	public void saveProvide(VcAllot provide) throws BusinessException {
		   if (logger.isInfoEnabled()) {
	            logger.info("saveProvide(VcProvide) - start"); //$NON-NLS-1$
	        }
	        try {
	            // 先拿到申领单号
	            Long id = provide.getId();
	            VcAllot vcAllot = allotDao.get(id);
	            Date sysdate = new Date();
	            String checkStatus = "";
	            // 先判断申领单
	            if (vcAllot != null) {
	            	vcAllot.setUpdatedBy(provide.getUpdatedBy());
	            	vcAllot.setDateUpdated(sysdate);

	                if (!StoreStatusValues.VC_ALLOT_STATUS_ALLOT_AWAITPROVIDE.equals(vcAllot.getAllotStatus())) {
	                    throw new BusinessException("调拨单号[" + provide.getAllotCode() + "]状态已发生变更,请确认后重新操作");
	                }
	                if (StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_BACK.equals(provide.getAllotStatus())) {
	                    // 拒绝
	                	vcAllot.setAllotStatus(provide.getAllotStatus());
	                    allotDao.update(vcAllot);
	                    checkStatus = "1";
	                }

	                if (StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_AWAITCONFIRM.equals(provide.getAllotStatus())) {
	                    // 提交发放单
	                	vcAllot.setProvideOprCode(provide.getProvideOprCode());
	                	vcAllot.setProvideOrgCode(provide.getProvideOrgCode());
	                	vcAllot.setAllotStatus(provide.getAllotStatus());
	                	vcAllot.setProvideTime(sysdate);

	                    allotDao.update(vcAllot);

	                    List<VcAllotDet> list = provide.getAllotDetList();
	                    //用于存放最新申请明细信息
	                    List<VcAllotDet> detListNew = new ArrayList<VcAllotDet>();

	                    for (Iterator it = list.iterator(); it.hasNext();) {
	                    	VcAllotDet vcAllotDet = (VcAllotDet) it.next();
	                    	VcAllotDet dbO = allotDao.getVcAllotDet(id, vcAllotDet.getDocVerCode());

	                        if (dbO == null) {
	                            throw new BusinessException("单证类型[" + vcAllotDet.getDocVerCode() + "]申请未找到 ");
	                        }

	                        VcStorage vcs = vcStorageDao.scopeEqual(vcAllotDet.getStartNum(), vcAllotDet.getEndNum(),
	                        		vcAllotDet.getDocVerCode(), vcAllotDet.getPressBatchNo(), "S1,S2,S3", provide
	                                        .getProvideOrgCode());

	                        /*Beans.copy(dbO, vcApplyDet);*/

	                        if (vcs != null) {
	                            String docStatus = vcs.getDocStatus();

	                            vcStorageDao.splitStorage(vcAllotDet.getStartNum(), vcAllotDet.getEndNum(), vcAllotDet
	                                    .getDocVerCode(), vcAllotDet.getPressBatchNo(), provide.getProvideOrgCode(),
	                                    docStatus, "PT", null,provide.getProvideOprCode());

	                            /*dbO.setDocStatus(docStatus);
	                            dbO.setUpdatedBy(provide.getUpdatedBy());
	                            dbO.setDateUpdated(sysdate);
	                            applyDao.update(dbO);*/
	                            VcAllotDet vcAllotDetNew = new VcAllotDet();
	                            Beans.copy(vcAllotDetNew, vcAllotDet);
	                            vcAllotDetNew.setIdVcallot(id);
	                            vcAllotDetNew.setDocStatus(docStatus);
	                            vcAllotDetNew.setCreatedBy(provide.getUpdatedBy());
	                            vcAllotDetNew.setUpdatedBy(provide.getUpdatedBy());
	                            vcAllotDetNew.setDateCreated(sysdate);
	                            vcAllotDetNew.setDateUpdated(sysdate);
	                            detListNew.add(vcAllotDetNew);
	                        } else {
	                            throw new BusinessException("单证类型[" + vcAllotDet.getDocVerCode() + "]发放的单证区间有误 ");
	                        }
	                    }
	                    
	                    //先删后插
	                    allotDao.deleteAllotDetByVcAllotId(id);
	                    allotDao.saveAll(detListNew);

	                    checkStatus = "2";
	                }

	                // approve  单证调拨申请时申请状态是B
	                VcApprove vcApprove = new VcApprove();
	                vcApprove.setApplyId(vcAllot.getId());
	                vcApprove.setApplyType("B");
	                vcApprove.setCheckExpl(provide.getBackReason());
	                vcApprove.setCheckOprId(provide.getProvideOprCode());
	                vcApprove.setCheckOrgId(provide.getProvideOrgCode());
	                vcApprove.setCheckStatus(checkStatus);
	                vcApprove.setCheckTime(sysdate);

	                vcApprove.setCreatedBy(provide.getUpdatedBy());
	                vcApprove.setDateCreated(sysdate);
	                vcApprove.setUpdatedBy(provide.getUpdatedBy());
	                vcApprove.setDateUpdated(sysdate);
	                approveDao.save(vcApprove);
	            }

	        }catch (BusinessException e) {
	            e.printStackTrace();
	            throw new BusinessException(e.getMessage(), e);
	        }catch (Exception e) {
	            e.printStackTrace();
	            throw new BusinessException(e.getMessage(), e);
	        }

	        if (logger.isInfoEnabled()) {
	            logger.info("saveProvide(VcProvide) - end"); //$NON-NLS-1$
	        }
		
	}
	@Override
	public String getUnitNameByUnitCode(String unitCode)
			throws BusinessException {
			String unitName = "";
			try{
				unitName = allotDao.getUnitNameByUnitCode(unitCode);
			}catch(DaoException e){
				throw new BusinessException(e.getMessage(), e);
			}
			return unitName;
	}
	@Override
	public Long getValidStorageNum(String orgCode, String docVerCode)
			throws BusinessException {
		try{
	        if (StringUtils.isEmpty(orgCode)) {
	            throw new BusinessException("查询单证有效库存当前机构不可以为空 ");
	        }
	        if (StringUtils.isEmpty(docVerCode)) {
	            throw new BusinessException("查询单证有效库存单证类型不可以为空 ");
	        }
	        return applyDao.validStorageNum(orgCode, docVerCode);
	    	}catch(DaoException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }catch(BusinessException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	}
	@Override
	public String deleteByAllotId(String[] idArray, String currentUser)
			throws BusinessException {
		    StringBuffer sb = new StringBuffer();
	        try{
	        List<String> enabledDeleteList = new ArrayList<String>() {
	            private static final long serialVersionUID = -8302230466427965528L;
	            {
	                add(StoreStatusValues.VC_ALLOT_STATUS_ALLOT_TEMPSAVE);
	                add(StoreStatusValues.VC_ALLOT_STATUS_PROVIDE_BACK);
	            }
	        };
	        if (idArray != null && idArray.length > 0) {
	            for (int i = 0; i < idArray.length; i++) {
	                Long id = Long.valueOf(idArray[i]);
	                VcAllot vcAllot = allotDao.get(id);

	                if (!enabledDeleteList.contains(vcAllot.getAllotStatus())) {
	                    sb.append("调拨单号[" + vcAllot.getAllotCode() + "]状态已发生变化,删除失败\n");
	                    continue;
	                }

	                vcAllot.setAllotStatus("0");
	                //begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
	                vcAllot.setDateUpdated(new Date());
	                //end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
	                applyDao.update(vcAllot);
	                sb.append("调拨单号[" + vcAllot.getAllotCode() + "]删除成功\n");
	            }
	        }}catch(Exception e){
	     	   throw new BusinessException(e.getMessage(),e);
	        } 
	        return sb.toString();
	}
}
