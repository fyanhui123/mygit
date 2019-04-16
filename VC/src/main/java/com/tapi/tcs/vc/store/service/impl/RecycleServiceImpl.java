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

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcRecycle;
import com.tapi.tcs.vc.schema.model.VcRecycleDet;
import com.tapi.tcs.vc.schema.model.VcStorage;
import com.tapi.tcs.vc.store.dao.RecycleDao;
import com.tapi.tcs.vc.store.dao.VcStorageDao;
import com.tapi.tcs.vc.store.service.RecycleService;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;
import com.tapi.tcs.vc.visausage.dao.AbnormalVerificationDao;

/**
 * @author hanmiao.diao
 * 
 */
public class RecycleServiceImpl implements RecycleService {

    private ApproveDao approveDao;

    private RecycleDao recycleDao;

    private TakeNoDao takeNoDao;

    private VcStorageDao vcStorageDao;

    private AbnormalVerificationDao abnormalVerificationDao;

    public AbnormalVerificationDao getAbnormalVerificationDao() {
        return abnormalVerificationDao;
    }

    public void setAbnormalVerificationDao(AbnormalVerificationDao abnormalVerificationDao) {
        this.abnormalVerificationDao = abnormalVerificationDao;
    }

    public VcStorageDao getVcStorageDao() {
        return vcStorageDao;
    }

    public void setVcStorageDao(VcStorageDao vcStorageDao) {
        this.vcStorageDao = vcStorageDao;
    }

    public ApproveDao getApproveDao() {
        return approveDao;
    }

    public void setApproveDao(ApproveDao approveDao) {
        this.approveDao = approveDao;
    }

    public TakeNoDao getTakeNoDao() {
        return takeNoDao;
    }

    public void setTakeNoDao(TakeNoDao takeNoDao) {
        this.takeNoDao = takeNoDao;
    }

    public RecycleDao getRecycleDao() {
        return recycleDao;
    }

    public void setRecycleDao(RecycleDao recycleDao) {
        this.recycleDao = recycleDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#saveRecycle(com.tapi.tcs
     * .vc.schema.model.VcRecycle)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public String saveRecycle(VcRecycle vcRecycle, boolean isBatch) throws BusinessException {
        Date sysdate = new Date();
        Long id = vcRecycle.getIdVcRecycle();

        try{
        // 校验每条明细是否符合要求
        List<VcRecycleDet> list = vcRecycle.getVcRecycleDets();
        if (list != null && !list.isEmpty()) {
            for (Iterator it = list.iterator(); it.hasNext();) {
                VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();

                String start = vcRecycleDet.getStartNum();
                String end = vcRecycleDet.getEndNum();
                String docVerCode = vcRecycleDet.getDocVerCode();
                String pressBatchNo = vcRecycleDet.getPressBatchNo();
                String docStatus = vcRecycleDet.getDocStatus();
                String orgCode = vcRecycle.getRecycleOrgCode();

                int i = vcStorageDao.isExist(start, end, docVerCode, pressBatchNo, docStatus, orgCode);
                if (i == 0) {
                    throw new BusinessException("单证类型代码[" + docVerCode + "]回收段号区间[" + start + "," + end + "]状态["
                            + docStatus + "]不符");
                }
            }
        }

        vcRecycle.setRecycleTime(sysdate);
      
        if (!isBatch) {
            vcRecycle.setDateCreated(sysdate);
            vcRecycle.setDateUpdated(sysdate);
            vcRecycle.setModifyOprCode(vcRecycle.getUpdatedBy());
            vcRecycle.setModifyTime(sysdate);
            vcRecycle.setRecycleTime(sysdate);
            if (id == null) {
                saveVcRecycle(vcRecycle);
            } else {
                updateVcRecycle(vcRecycle, id);
            }

            if (StoreStatusValues.VC_RECYCLE_STATUS_AWAITCONFIRM.equals(vcRecycle.getRecycleStatus())) {
                // saveApprove
                saveApproveWhenRecycleSubmit(vcRecycle);

                // 拆分VC_STORAGE
                splitStorage(vcRecycle, list);
            }
        } else {
            vcRecycle.setRecycleStatus(StoreStatusValues.VC_RECYCLE_STATUS_AWAITCONFIRM);
            vcRecycle.setDateUpdated(sysdate);
            vcRecycle.setRecycleTime(sysdate);
            recycleDao.update(vcRecycle);

            // saveApprove
            saveApproveWhenRecycleSubmit(vcRecycle);

            // 拆分VC_STORAGE
            splitStorage(vcRecycle, list);
        }}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }catch(BusinessException e){
     	   throw new BusinessException(e.getMessage(),e);
        }

        return "操作成功";
    }

    /**
     * 拆分库存表
     * 
     * @param vcRecycle
     *            回收信息
     * @param list
     *            回收明细信息
     */
    @SuppressWarnings("rawtypes")
    private void splitStorage(VcRecycle vcRecycle, List<VcRecycleDet> list) throws BusinessException{
    	try{
        if (list != null && !list.isEmpty()) {
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                VcRecycleDet vcRecycleDet = (VcRecycleDet) iterator.next();

                String start = vcRecycleDet.getStartNum();
                String end = vcRecycleDet.getEndNum();
                String docVerCode = vcRecycleDet.getDocVerCode();
                String pressBatchNo = vcRecycleDet.getPressBatchNo();
                String docStatus = vcRecycleDet.getDocStatus();
                String orgCode = vcRecycle.getRecycleOrgCode();

                vcStorageDao.splitStorage(start, end, docVerCode, pressBatchNo, orgCode, docStatus, "RT", null,vcRecycle.getUpdatedBy());
            }
        }}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 保存公共审批信息当回收申请批量提交时
     * 
     * @param vcRecycle
     */
    private void saveApproveWhenRecycleSubmit(VcRecycle vcRecycle) throws BusinessException{
    	try{
	        VcApprove vcApprove = new VcApprove();
	        vcApprove.setApplyId(vcRecycle.getIdVcRecycle());
	        vcApprove.setApplyType("R");
	        vcApprove.setCheckExpl("提交");
	        vcApprove.setCheckOprId(vcRecycle.getRecycleOprCode());
	        vcApprove.setCheckOrgId(vcRecycle.getRecycleOrgCode());
	        vcApprove.setCheckStatus("0");
	        vcApprove.setCheckTime(new Date());
	
	        vcApprove.setCreatedBy(vcRecycle.getUpdatedBy());
	        vcApprove.setDateCreated(vcRecycle.getDateUpdated());
	        vcApprove.setUpdatedBy(vcRecycle.getUpdatedBy());
	        vcApprove.setDateUpdated(vcRecycle.getDateUpdated());
	
	        approveDao.save(vcApprove);
    	}catch(Exception e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 修改时操作VC_RECYCLE,VC_RECYCLE_DET
     * 
     * @param vcRecycle
     *            修改对象
     * @param id
     *            主键
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private void updateVcRecycle(VcRecycle vcRecycle, Long id) throws BusinessException {
        // 走"修改"分支
        VcRecycle dbObject = recycleDao.get(id);

        dbObject.setRecycleStatus(vcRecycle.getRecycleStatus());
        dbObject.setRecycleReason(vcRecycle.getRecycleReason());
        dbObject.setRecycleTime(vcRecycle.getRecycleTime());
        dbObject.setModifyOprCode(vcRecycle.getModifyOprCode());
        dbObject.setModifyTime(vcRecycle.getModifyTime());
        dbObject.setUpdatedBy(vcRecycle.getUpdatedBy());
        dbObject.setDateUpdated(vcRecycle.getDateUpdated());
        dbObject.setRecycleTime(vcRecycle.getRecycleTime());

        // 将页面上的属性set到dbObject中,update操作
        try {
            recycleDao.update(dbObject);
            // 删除子表中id相关联的记录

            recycleDao.deleteRecycleDetListByRecycleId(id);

            // 将页面收集到的明细集合重新再插入表中
            List<VcRecycleDet> pageList = vcRecycle.getVcRecycleDets();

            if (pageList != null && pageList.size() > 0) {
                for (Iterator it = pageList.iterator(); it.hasNext();) {
                    VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
                    vcRecycleDet.setVcRecycle(null);
                    vcRecycleDet.setIdVcRecycle(id);

                    vcRecycleDet.setCreatedBy(dbObject.getCreatedBy());
                    vcRecycleDet.setDateCreated(dbObject.getDateCreated());
                    vcRecycleDet.setUpdatedBy(dbObject.getUpdatedBy());
                    vcRecycleDet.setDateUpdated(dbObject.getDateUpdated());
                }
                recycleDao.saveAll(pageList);
            }
        }catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }catch(Exception e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /**
     * 新增时操作VC_RECYCLE,VC_RECYCLE_DET
     * 
     * @param vcRecycle
     *            新增对象
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private void saveVcRecycle(VcRecycle vcRecycle) throws BusinessException {
        // 走"新增"分支
        List<VcRecycleDet> vcRecycleDetsPage = vcRecycle.getVcRecycleDets();
        vcRecycle.setVcRecycleDets(null);
        String recycleCode = null;
        try {
            recycleCode = takeNoDao.getNo("RC");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("生成回收单号出错");
        }

        vcRecycle.setRecycleCode(recycleCode);

        try {
            recycleDao.save(vcRecycle);

            if (vcRecycleDetsPage != null && vcRecycleDetsPage.size() > 0) {
                for (Iterator it = vcRecycleDetsPage.iterator(); it.hasNext();) {
                    VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
                    vcRecycleDet.setIdVcRecycle(vcRecycle.getIdVcRecycle());
                    vcRecycleDet.setCreatedBy(vcRecycle.getCreatedBy());
                    vcRecycleDet.setDateCreated(vcRecycle.getDateCreated());
                    vcRecycleDet.setUpdatedBy(vcRecycle.getUpdatedBy());
                    vcRecycleDet.setDateUpdated(vcRecycle.getDateUpdated());
                }
                recycleDao.saveAll(vcRecycleDetsPage);
            }
        } catch(Exception e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#queryRecycleListByPages(
     * java.util.Map, int, int)
     */
    @Override
    public Page queryRecycleListByPages(Map<String, Object> params, int page, int rows) throws BusinessException {
	    try{	
	        return recycleDao.findRecycleListByPages(params, page, rows);
	    }catch(DaoException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#getVcRecycle(java.lang.Long)
     */
    @Override
    public VcRecycle getVcRecycle(Long id) throws BusinessException {
    	try{
           return recycleDao.get(id);
    	}catch(Exception e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#saveRecycleConfirm(com.tapi
     * .tcs.vc.schema.model.VcRecycle)
     */
    @Override
    public String saveRecycleConfirm(VcRecycle pageVcRecycle) throws BusinessException {
        // 获取id
        Long id = pageVcRecycle.getIdVcRecycle();
        // 获取状态
        String recycleStatusPage = pageVcRecycle.getRecycleStatus();
        // 业务校验
        try{
        if (id == null || StringUtils.isEmpty(recycleStatusPage)) {
            throw new BusinessException("回收单对象ID为空或者回收单状态为空 ");
        }
        if (StoreStatusValues.VC_RECYCLE_STATUS_CONFIRMBACK.equals(recycleStatusPage)) {
            String disagreeReason = pageVcRecycle.getDisagreeReason();

            if (StringUtils.isEmpty(disagreeReason)) {
                throw new BusinessException("回收单确认退回时,请确认填写退回原因 ");
            }
        }
        // 并发校验
        VcRecycle dbObject = null;
        try {
            dbObject = recycleDao.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("数据库异常," + e.getMessage());
        }

        String checkStatus = "";
        Date sysdate = new Date();
        if (dbObject != null) {
            dbObject.setConfirmOprCode(pageVcRecycle.getConfirmOprCode());
            // dbObject.setConfirmOrgCode(pageVcRecycle.getConfirmOrgCode());
            dbObject.setUpdatedBy(pageVcRecycle.getUpdatedBy());
            dbObject.setDateUpdated(sysdate);
            if (!StoreStatusValues.VC_RECYCLE_STATUS_AWAITCONFIRM.equals(dbObject.getRecycleStatus())) {
                throw new BusinessException("回收单号[" + dbObject.getRecycleCode() + "]状态已发生改变,审批失败");
            } else {
                List<VcRecycleDet> detList = dbObject.getVcRecycleDets();

                if (StoreStatusValues.VC_RECYCLE_STATUS_CONFIRMBACK.equals(recycleStatusPage)) {
                    saveApproveDisagree(dbObject, detList);
                    checkStatus = StoreStatusValues.VC_APPROVE_DISAGREE; // 审批退回
                    dbObject.setRecycleStatus(StoreStatusValues.VC_RECYCLE_STATUS_CONFIRMBACK);
                }
                if (StoreStatusValues.VC_RECYCLE_STATUS_CONFIRMED.equals(recycleStatusPage)) {
                    saveApproveAgree(dbObject, detList);
                    checkStatus = StoreStatusValues.VC_APPROVE_AGREE; // 审批通过
                    dbObject.setRecycleStatus(StoreStatusValues.VC_RECYCLE_STATUS_CONFIRMED);
                }
                dbObject.setConfirmTime(sysdate);
                // 更新回单收对象
                try {
                    recycleDao.update(dbObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessException("数据库异常," + e.getMessage());
                }
            }
        } else {
            throw new BusinessException("数据异常,未找到回收单号 ");
        }

        // 调用审批公共接口,存储审批轨迹信息
        saveVcApproveWhenRecycleConfirm(pageVcRecycle, checkStatus);
        }catch(DaoException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }catch(BusinessException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }catch(Exception e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
        return "操作成功";
    }

    /**
     * 保存公共审批记录信息
     * 
     * @param pageVcRecycle
     *            审批结果
     * @param checkStatus
     *            审批状态
     * @throws BusinessException
     *             异常
     */
    private void saveVcApproveWhenRecycleConfirm(VcRecycle pageVcRecycle, String checkStatus) throws BusinessException {
        try {
            Date sysdate = new Date();
            // 调用审批公共接口,存储审批轨迹信息
            VcApprove vcApprove = new VcApprove();
            vcApprove.setApplyId(pageVcRecycle.getIdVcRecycle());
            vcApprove.setApplyType("R");
            vcApprove.setCheckExpl(pageVcRecycle.getDisagreeReason());
            vcApprove.setCheckOprId(pageVcRecycle.getConfirmOprCode()); // 
            vcApprove.setCheckOrgId(pageVcRecycle.getConfirmOrgCode());
            vcApprove.setCheckStatus(checkStatus);
            vcApprove.setCheckTime(sysdate);

            vcApprove.setCreatedBy(pageVcRecycle.getUpdatedBy());
            vcApprove.setDateCreated(sysdate);
            vcApprove.setUpdatedBy(pageVcRecycle.getUpdatedBy());
            vcApprove.setDateUpdated(sysdate);

            approveDao.save(vcApprove);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("调用审批公共接口失败");
        }
    }

    /**
     * 回收审批同意处理
     * 
     * @param dbObject
     *            回收对象
     * @param detList
     *            回收明细集合
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private void saveApproveAgree(VcRecycle dbObject, List<VcRecycleDet> detList) throws BusinessException {
        VcRecycleDet vcRecycleDet = null;
        String recycleOrgCode = dbObject.getRecycleOrgCode();
        String confirmOrgCode = dbObject.getConfirmOrgCode();
        Date sysdate = new Date();

         try{
        for (Iterator it = detList.iterator(); it.hasNext();) {
            vcRecycleDet = (VcRecycleDet) it.next();
            String docVerCode = vcRecycleDet.getDocVerCode();
            String pressBatchNo = vcRecycleDet.getPressBatchNo();
            String docStatus = "RT";
            VcStorage vcStorage = vcStorageDao.fullEqual(vcRecycleDet.getStartNum(), vcRecycleDet.getEndNum(),
                    docVerCode, pressBatchNo, docStatus, recycleOrgCode);
            if (vcStorage != null) {
                /**
                 * 更新VC_STORAGE表，更新规则：将VC_STORAGE表回收申请里的回收号段（单证状态=RT）的记录进行更新
                 */
                try {
                    vcStorage.setOrgCode(confirmOrgCode);
                    vcStorage.setModifyTime(sysdate);
                    vcStorage.setInStoreTime(sysdate);
                    vcStorage.setModifyUserCode(dbObject.getConfirmOprCode());

                    vcStorageDao.update(vcStorage);
                    vcStorageDao.mergeStorage(vcRecycleDet.getStartNum(), vcRecycleDet.getEndNum(), docVerCode,
                            pressBatchNo, dbObject.getConfirmOrgCode(), docStatus, "S3", dbObject.getConfirmOprCode(),null);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new BusinessException("回收审批同意合并号段失败");
                }
            } else {
                throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误 ");
            }
        }}catch(DaoException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }catch(BusinessException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
    }

    /**
     * 回收审批退回处理
     * 
     * @param dbObject
     *            回收对象
     * @param detList
     *            回收明细集合
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private void saveApproveDisagree(VcRecycle dbObject, List<VcRecycleDet> detList) throws BusinessException {
        for (Iterator it = detList.iterator(); it.hasNext();) {
            VcRecycleDet vcRecycleDet = (VcRecycleDet) it.next();
     try{
            String docVerCode = vcRecycleDet.getDocVerCode();
            String pressBatchNo = vcRecycleDet.getPressBatchNo();
            String start = vcRecycleDet.getStartNum();
            String end = vcRecycleDet.getEndNum();
            String docStatus = "RT";
            VcStorage vcStorage = vcStorageDao.fullEqual(vcRecycleDet.getStartNum(), vcRecycleDet.getEndNum(),
                    docVerCode, pressBatchNo, docStatus, dbObject.getRecycleOrgCode());
            if (vcStorage != null) {
                // 1.恢复状态
                // vcStorage.setDocStatus(vcCancelDet.getDocStatus());
                // vcStorageDao.update(vcStorage);
                // 步骤1已被包含于步骤2
                // 2.合并号段
                vcStorageDao.mergeStorage(start, end, docVerCode, pressBatchNo, dbObject.getRecycleOrgCode(),
                        docStatus, vcRecycleDet.getDocStatus(), dbObject.getRecycleOprCode().toString(),vcStorage.getDeadline());
            } else {
                throw new BusinessException("单证代码[" + docVerCode + "]流水号段的单证状态有误 ");
            }
        }catch(DaoException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }catch(BusinessException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#deleteByLogic(java.lang.
     * String[], java.lang.String)
     */
    @Override
    public String deleteByLogic(String[] idArray, String currentUser) throws BusinessException {
        List<String> enableDeleteList = new ArrayList<String>() {
            /**
			 * 
			 */
            private static final long serialVersionUID = -9185495353223340168L;

            {
                add("1");
                add("3");
            }
        };
       try{
        StringBuffer sb = new StringBuffer();
        if (idArray != null && idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                Long id = Long.valueOf(idArray[i]);

                VcRecycle vcRecycle = recycleDao.get(id);
                if (enableDeleteList.contains(vcRecycle.getRecycleStatus())) {
                    vcRecycle.setRecycleStatus("0");
                    vcRecycle.setUpdatedBy(currentUser);
                    //begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                    vcRecycle.setDateUpdated(new Date());
                    //begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                    recycleDao.update(vcRecycle);
                    sb.append("回收单号[" + vcRecycle.getRecycleCode() + "]删除成功\n");
                } else {
                    sb.append("回收单号[" + vcRecycle.getRecycleCode() + "]状态发生改变,删除失败\n");
                }
            }
        }

        return sb.toString();
       }catch(Exception e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.RecycleService#getVcApprove(java.lang.Long,
     * java.lang.String)
     */
    @Override
    public List<VcApprove> getVcApprove(Long applyId, String appType) throws BusinessException {
    	try{
        return approveDao.queryApproves(appType, applyId);
    	}catch(DaoException e){
	  	   throw new BusinessException(e.getMessage(),e);
	    }
    }

}
