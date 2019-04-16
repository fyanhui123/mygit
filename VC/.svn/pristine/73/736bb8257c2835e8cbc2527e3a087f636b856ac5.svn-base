/**
 * 
 */
package com.tapi.tcs.vc.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.common.util.DateUtils;
import com.tapi.tcs.vc.common.util.SysConst;
import com.tapi.tcs.vc.invoice.service.HeilongjiangInvoiceService;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.schema.model.VcInvoiceBuyHlj;
import com.tapi.tcs.vc.schema.model.VcManageCodeHlj;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.ApplyDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.service.ProvideService;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;

/**
 * @author hanmiao.diao
 * 
 */
public class ProvideServiceImpl implements ProvideService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ProvideServiceImpl.class);

    private ApplyDao applyDao;

    private ApproveDao approveDao;

    private TakeNoDao takeNoDao;

    private VcStorageDao vcStorageDao;

    private VcDocMngRuleDao vcDocMngRuleDao;
    
    private HeilongjiangInvoiceService heilongjiangInvoiceService;

    
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

    public VcStorageDao getVcStorageDao() {
        return vcStorageDao;
    }

    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

    public TakeNoDao getTakeNoDao() {
        return takeNoDao;
    }

    public void setTakeNoDao(TakeNoDao takeNoDao) {
        this.takeNoDao = takeNoDao;
    }

    public ApplyDao getApplyDao() {
        return applyDao;
    }

    public void setApplyDao(ApplyDao applyDao) {
        this.applyDao = applyDao;
    }

    public ApproveDao getApproveDao() {
        return approveDao;
    }

    public void setApproveDao(ApproveDao approveDao) {
        this.approveDao = approveDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.provide.service.ProvideService#saveProvide(com.tapi.tcs
     * .vc.schema.model.VcProvide)
     */
    @SuppressWarnings("rawtypes")
    public void saveProvide(VcApply provide) throws BusinessException {
        if (logger.isInfoEnabled()) {
            logger.info("saveProvide(VcProvide) - start"); //$NON-NLS-1$
        }

        try {
            // 先拿到申领单号
            Long id = provide.getId();
            VcApply vcApply = applyDao.get(id);
            Date sysdate = new Date();
            String checkStatus = "";
            // 先判断申领单
            if (vcApply != null) {
                vcApply.setUpdatedBy(provide.getUpdatedBy());
                vcApply.setDateUpdated(sysdate);

                if (!StoreStatusValues.VC_APPLY_STATUS_APPLY_AWAITPROVIDE.equals(vcApply.getApplyStatus())) {
                    throw new BusinessException("申领单号[" + provide.getApplyCode() + "]状态已发生变更,请确认后重新操作");
                }
                if (StoreStatusValues.VC_APPLY_STATUS_PROVIDE_BACK.equals(provide.getApplyStatus())) {
                    // 拒绝
                    vcApply.setApplyStatus(provide.getApplyStatus());
                    applyDao.update(vcApply);
                    checkStatus = "1";
                }

                if (StoreStatusValues.VC_APPLY_STATUS_PROVIDE_AWAITCONFIRM.equals(provide.getApplyStatus())) {
                    // 提交发放单
                    vcApply.setProvideOprCode(provide.getProvideOprCode());
                    vcApply.setProvideOrgCode(provide.getProvideOrgCode());
                    vcApply.setApplyStatus(provide.getApplyStatus());
                    vcApply.setProvideTime(sysdate);

                    applyDao.update(vcApply);

                    List<VcApplyDet> list = provide.getApplyDetList();
                    //用于存放最新申请明细信息
                    List<VcApplyDet> detListNew = new ArrayList<VcApplyDet>();

                    for (Iterator it = list.iterator(); it.hasNext();) {
                        VcApplyDet vcApplyDet = (VcApplyDet) it.next();
                        VcApplyDet dbO = applyDao.getVcApplyDet(id, vcApplyDet.getDocVerCode());

                        if (dbO == null) {
                            throw new BusinessException("单证类型[" + vcApplyDet.getDocVerCode() + "]申请未找到 ");
                        }

                        VcStorage vcs = vcStorageDao.scopeEqual(vcApplyDet.getStartNum(), vcApplyDet.getEndNum(),
                                vcApplyDet.getDocVerCode(), vcApplyDet.getPressBatchNo(), "S1,S2,S3", provide
                                        .getProvideOrgCode());

                        /*Beans.copy(dbO, vcApplyDet);*/

                        if (vcs != null) {
                            String docStatus = vcs.getDocStatus();

                            vcStorageDao.splitStorage(vcApplyDet.getStartNum(), vcApplyDet.getEndNum(), vcApplyDet
                                    .getDocVerCode(), vcApplyDet.getPressBatchNo(), provide.getProvideOrgCode(),
                                    docStatus, "PT", null,provide.getProvideOprCode());

                            /*dbO.setDocStatus(docStatus);
                            dbO.setUpdatedBy(provide.getUpdatedBy());
                            dbO.setDateUpdated(sysdate);
                            applyDao.update(dbO);*/
                            VcApplyDet vcApplyDetNew = new VcApplyDet();
                            Beans.copy(vcApplyDetNew, vcApplyDet);
                            vcApplyDetNew.setIdVcApply(id);
                            vcApplyDetNew.setDocStatus(docStatus);
                            vcApplyDetNew.setCreatedBy(provide.getUpdatedBy());
                            vcApplyDetNew.setUpdatedBy(provide.getUpdatedBy());
                            vcApplyDetNew.setDateCreated(sysdate);
                            vcApplyDetNew.setDateUpdated(sysdate);
                            detListNew.add(vcApplyDetNew);
                        } else {
                            throw new BusinessException("单证类型[" + vcApplyDet.getDocVerCode() + "]发放的单证区间有误 ");
                        }
                    }
                    
                    //先删后插
                    applyDao.deleteApplyDetByVcApplyId(id);
                    applyDao.saveAll(detListNew);

                    checkStatus = "2";
                }

                // approve
                VcApprove vcApprove = new VcApprove();
                vcApprove.setApplyId(vcApply.getId());
                vcApprove.setApplyType("A");
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.ProvideService#saveProvideConfirm(com.tapi
     * .tcs.vc.schema.model.VcApply)
     */
    @SuppressWarnings("rawtypes")
    public void saveProvideConfirm(VcApply vcProvideConfirm) throws BusinessException {
        Long id = vcProvideConfirm.getId();
        Date sysdate = new Date();
   try{
        List<VcApplyDet> detList = applyDao.getApplyDetListByVcApplyId(id);

        // 修改申领单状态
        VcApply vcApply = applyDao.get(id);

        vcApply.setConfirmOprCode(vcProvideConfirm.getConfirmOprCode());
        vcApply.setApplyStatus(vcProvideConfirm.getApplyStatus());
        vcApply.setConfirmTime(sysdate);
        vcApply.setUpdatedBy(vcProvideConfirm.getUpdatedBy());
        vcApply.setDateUpdated(sysdate);

        applyDao.update(vcApply);

        String approveRs = vcProvideConfirm.getApplyStatus();
        String checkStatus = "";
        if (StoreStatusValues.VC_APPLY_STATUS_PROVIDE_CONFIRMED.equals(approveRs)) {
            for (Iterator it = detList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();

                String docVerCode = vcApplyDet.getDocVerCode();
                String pressBatchNo = vcApplyDet.getPressBatchNo();
                String docStatus = "PT";
                VcStorage vcStorage = vcStorageDao.fullEqual(vcApplyDet.getStartNum(), vcApplyDet.getEndNum(),
                        docVerCode, pressBatchNo, docStatus, vcApply.getProvideOrgCode());
                if (vcStorage != null) {
                    /**
                     * 更新VC_STORAGE表，更新规则：将VC_STORAGE表申领申请里的发放号段（单证状态=PT）的记录进行更新
                     */
                    try {
                        vcStorage.setOrgCode(vcApply.getApplyOrgCode());
                        vcStorage.setModifyTime(sysdate);
                        vcStorage.setInStoreTime(sysdate);
                        vcStorage.setModifyUserCode(vcProvideConfirm.getConfirmOprCode());

                        vcStorageDao.update(vcStorage);
                        vcStorageDao.mergeStorage(vcApplyDet.getStartNum(), vcApplyDet.getEndNum(), docVerCode,
                                pressBatchNo, vcApply.getApplyOrgCode(), docStatus, "S2", vcProvideConfirm
                                        .getConfirmOprCode(),vcApplyDet.getDeadline());
                        //add by chy 20131029 黑龙江地税-领用入库时需更新领购表中的机构代码 begin
                        String applyOrgCode = vcApply.getApplyOrgCode();
                        if(applyOrgCode.length()>=4 && SysConst.COMCODE_HLJ.equals(applyOrgCode.substring(0,4))){
                        	//若申领机构维护了管理编码，则更新领购表机构代码
                        	VcManageCodeHlj vcManageCodeHlj = heilongjiangInvoiceService.findManageCodeByOrgCode(applyOrgCode);
                        	if(vcManageCodeHlj != null){
	                        	VcInvoiceBuyHlj conditions = new VcInvoiceBuyHlj();
	                        	conditions.setDocVerCode(docVerCode);
	                        	conditions.setInvoiceCode(pressBatchNo);
	                        	conditions.setStartNum(vcApplyDet.getStartNum());
	                        	VcInvoiceBuyHlj vcInvoiceBuyHlj = heilongjiangInvoiceService.findVcInvoiceBuyHlj(conditions);
	                        	if(vcInvoiceBuyHlj!=null){
	                        		vcInvoiceBuyHlj.setOrgCode(applyOrgCode);
	                        		vcInvoiceBuyHlj.setOperatorCode(vcApply.getConfirmOprCode());
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

        if (StoreStatusValues.VC_APPLY_STATUS_PROVIDE_CONFIRMEBACK.equals(approveRs)) {
            for (Iterator it = detList.iterator(); it.hasNext();) {
                VcApplyDet vcApplyDet = (VcApplyDet) it.next();

                String docVerCode = vcApplyDet.getDocVerCode();
                String pressBatchNo = vcApplyDet.getPressBatchNo();
                String start = vcApplyDet.getStartNum();
                String end = vcApplyDet.getEndNum();
                String docStatus = "PT";
                VcStorage vcStorage = vcStorageDao.fullEqual(vcApplyDet.getStartNum(), vcApplyDet.getEndNum(),
                        docVerCode, pressBatchNo, docStatus, vcApply.getProvideOrgCode());
                if (vcStorage != null) {
                    // 1.恢复状态
                    // vcStorage.setDocStatus(vcCancelDet.getDocStatus());
                    // vcStorageDao.update(vcStorage);
                    // 步骤1已被包含于步骤2
                    // 2.合并号段
                    vcStorageDao.mergeStorage(start, end, docVerCode, pressBatchNo, vcApply.getProvideOrgCode(),
                            docStatus, vcApplyDet.getDocStatus(), vcApply.getConfirmOprCode(),null);
                } else {
                    throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误 ");
                }
            }
            checkStatus = "3";
        }

        // 公共审批表
        VcApprove vcApprove = new VcApprove();
        vcApprove.setApplyId(vcApply.getId());
        vcApprove.setApplyType("A");
        vcApprove.setCheckExpl(vcProvideConfirm.getBackReason());
        vcApprove.setCheckOprId(vcApply.getConfirmOprCode());
        vcApprove.setCheckOrgId(vcApply.getApplyOrgCode());
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

    /**
     * 根据单证类型代码、对象类别、对象代码获取最大单证保存天数
     * <p>
     * 按以下三步查找，直至查询到为止
     * 1、查询单证管理控制规则表 VC_DOC_MNG_RULE
     * 2、查询单证类型管控信息表VC_DOC_VERSION_INFO_MNG
     * 3、查询常量表VC_CONSTANT_CONFIG
     * <p>
     * @param docVerCode
     * @param mngType 0-机构  1-单证使用人
     * @param mngObjectCode 机构、人员代码
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 9, 2014
     */
   @Override
   public int getMaxStoreTime(String docVerCode,String mngType,String mngObjectCode) throws BusinessException {
      try{
         return  vcDocMngRuleDao.getMaxStoreTime(docVerCode, mngType, mngObjectCode);
      }catch (DaoException e) {
         throw new BusinessException(e.getMessage(), e);
      }
   }

	@Override
	public int getMaxExistTime(String maxStoreType, String mngObjectCode)
			throws BusinessException {
		VcConstantConfig  vcConstantConfig=null;
	   try {
		     vcConstantConfig=vcDocMngRuleDao.getConfigByQueryMaxExistsTime(maxStoreType, mngObjectCode);
		   
		   if(vcConstantConfig ==null){
			   return 0;
		   }
		}catch (Exception e) { 
			    e.printStackTrace();
	            throw new BusinessException(e.getMessage(), e);
	        }
		 return  Integer.valueOf(vcConstantConfig.getConstantValue());
		}

	@Override
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws BusinessException {
		List<VcDocVersionInfo> list = null;
		try{
			list = applyDao.getVcDocVersionInfoList(applyId, docVerCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return list;
	}
	
	@Override
	public Integer getApplyNum(Long applyId, String docVerCode) throws BusinessException {
		Integer result = 0;
		try{
			result = applyDao.getApplyNum(applyId, docVerCode);
		}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
		return result;
	}
}
