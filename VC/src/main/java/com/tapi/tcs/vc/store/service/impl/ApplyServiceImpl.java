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
import com.tapi.tcs.vc.common.dao.ApproveDao;
import com.tapi.tcs.vc.common.dao.TakeNoDao;
import com.tapi.tcs.vc.common.util.Beans;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.store.dao.ApplyDao;
import com.tapi.tcs.vc.store.service.ApplyService;
import com.tapi.tcs.vc.store.vo.StoreStatusValues;

/**
 * @author hanmiao.diao
 * 
 */
public class ApplyServiceImpl implements ApplyService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ApplyServiceImpl.class);

    private ApplyDao applyDao;

    private ApproveDao approveDao;

    private TakeNoDao takeNoDao;

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
     * com.tapi.tcs.vc.apply.service.ApplyService#saveApply(com.tapi.tcs.vc.
     * schema.model.VcApply)
     */
    @Override
    public String saveApply(VcApply pageObject) throws BusinessException {
        if (logger.isInfoEnabled()) {
            logger.info("saveApply(VcApply) - start"); //$NON-NLS-1$
        }
        StringBuffer sb = new StringBuffer();
        Date sysdate = new Date();
        try {
            Long id = pageObject.getId();

            if (id == null) {
                pageObject.setDateCreated(sysdate);
                pageObject.setDateUpdated(sysdate);
                pageObject.setModifyTime(sysdate);
                pageObject.setApplyTime(sysdate);

                pageObject.setModifyOprCode(pageObject.getCreatedBy());

                String applyCode = saveVcApply(pageObject);

                sb.append("申领单号[" + applyCode + "]创建成功");
            } else {
                logger.info("update Id " + id);
                pageObject.setDateUpdated(sysdate);
                pageObject.setApplyTime(sysdate);

                VcApply dbObject = updateVcApply(pageObject, id);

                sb.append("申领单号[" + dbObject.getApplyCode() + "]修改成功");
            }

            // 如果是申领提交,往公共审批表里写数据
            if (StoreStatusValues.VC_APPLY_STATUS_APPLY_AWAITPROVIDE.equals(pageObject.getApplyStatus())) {
                VcApprove vcApprove = new VcApprove();
                vcApprove.setApplyId(pageObject.getId());
                vcApprove.setApplyType("A");
                vcApprove.setCheckExpl("提交");
                vcApprove.setCheckOprId(pageObject.getApplyOprCode());
                vcApprove.setCheckOrgId(pageObject.getApplyOrgCode());
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
            logger.info("saveApply(VcApply) - end"); //$NON-NLS-1$
        }

        return sb.toString();
    }

    /**
     * 保存VC_APPLY,VC_APPLY_DET
     * 
     * @param pageObject
     *            页面封装的数据
     * @return 申领单号
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private String saveVcApply(VcApply pageObject) throws BusinessException {
        String applyCode = null;
        try {
            applyCode = takeNoDao.getNo("DA");
        pageObject.setApplyCode(applyCode);

        applyDao.save(pageObject);

        List<VcApplyDet> vcApplyDetList = pageObject.getApplyDetList();
        for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
            VcApplyDet vcApplyDet = (VcApplyDet) it.next();
            vcApplyDet.setIdVcApply(pageObject.getId());
            vcApplyDet.setVcApply(null);
            vcApplyDet.setCreatedBy(pageObject.getCreatedBy());
            vcApplyDet.setUpdatedBy(pageObject.getUpdatedBy());
            vcApplyDet.setDateCreated(pageObject.getDateCreated());
            vcApplyDet.setDateUpdated(pageObject.getDateUpdated());

            applyDao.save(vcApplyDet);
        }
        } catch (DaoException e) {

        	throw new BusinessException(e.getMessage(),e);
        }
        return applyCode;
    }

    /**
     * 修改VC_APPLY,VC_APPLY_DET
     * 
     * @param pageObject
     *            页面封装的数据
     * @param id
     *            主键
     * @return 数据库持久对象
     * @throws BusinessException
     *             异常
     */
    @SuppressWarnings("rawtypes")
    private VcApply updateVcApply(VcApply pageObject, Long id) throws BusinessException {
        // 先删除子表内容,再重新插入一遍
    	VcApply dbObject=null;
       try{
        applyDao.deleteApplyDetByVcApplyId(id);

        // 修改主表信息
        dbObject = applyDao.get(id);

        List<String> enabledUpdateList = new ArrayList<String>() {
            /**
			 * 
			 */
            private static final long serialVersionUID = -8302230466427965528L;

            {
                add(StoreStatusValues.VC_APPLY_STATUS_APPLY_TEMPSAVE);
                add(StoreStatusValues.VC_APPLY_STATUS_PROVIDE_BACK);
            }
        };

        if (!enabledUpdateList.contains(dbObject.getApplyStatus())) {
            throw new BusinessException("申领单号[" + dbObject.getApplyCode() + "]状态已发生变化,请重新查询后继续操作");
        }

        dbObject.setModifyOprCode(pageObject.getUpdatedBy());
        dbObject.setModifyTime(pageObject.getDateUpdated());
        dbObject.setApplyTime(pageObject.getApplyTime());
        dbObject.setApplyReason(pageObject.getApplyReason());
        dbObject.setApplyStatus(pageObject.getApplyStatus());
        dbObject.setDateUpdated(pageObject.getDateUpdated());
        dbObject.setUpdatedBy(pageObject.getUpdatedBy());

        try {
            applyDao.update(dbObject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("修改VC_APPLY记录失败,数据库异常 ",e);
        }

        List<VcApplyDet> vcApplyDetList = pageObject.getApplyDetList();

        for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
            VcApplyDet vcApplyDet = (VcApplyDet) it.next();
            vcApplyDet.setIdVcApply(dbObject.getId());
            vcApplyDet.setVcApply(null);
            vcApplyDet.setCreatedBy(dbObject.getCreatedBy());
            vcApplyDet.setDateCreated(dbObject.getDateCreated());
            vcApplyDet.setUpdatedBy(pageObject.getUpdatedBy());
            vcApplyDet.setDateUpdated(pageObject.getDateUpdated());
        }

        try {
            applyDao.saveAll(vcApplyDetList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("修改VC_APPLY_DET记录失败,数据库异常 ",e);
        }
       }catch(DaoException e){
    	   throw new BusinessException(e.getMessage(),e);
       }catch(BusinessException e){
    	   throw new BusinessException(e.getMessage(),e);
       }
        return dbObject;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.service.ApplyService#queryApplyListByPages(java
     * .util.Map, int, int)
     */
    public Page queryApplyListByPages(Map<String, Object> params, int pageNo, int pageSize) throws BusinessException {
    	try{
          return applyDao.queryApplyList(params, pageNo, pageSize);
    	}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.service.ApplyService#getVcApply(java.lang.Long)
     */
    public VcApply getVcApply(Long id) throws BusinessException {
    	try{
          return applyDao.get(id);
    	}catch(Exception e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.service.ApplyService#deleteVcApply(java.lang.Long)
     */
    public String deleteVcApply(Long id) throws BusinessException {
    	try{
        applyDao.deleteApplyDetByVcApplyId(id);
        VcApply vcApply = applyDao.get(id);
        applyDao.delete(vcApply);
    	}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        } return "数据删除成功";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.service.ApplyService#deleteByLogic(java.lang.String
     * [], java.lang.String)
     */
    public String deleteByLogic(String[] idArray, String currentUser) throws BusinessException {

        StringBuffer sb = new StringBuffer();
        try{
        List<String> enabledDeleteList = new ArrayList<String>() {
            /**
			 * 
			 */
            private static final long serialVersionUID = -8302230466427965528L;

            {
                add(StoreStatusValues.VC_APPLY_STATUS_APPLY_TEMPSAVE);
                add(StoreStatusValues.VC_APPLY_STATUS_PROVIDE_BACK);
            }
        };
        if (idArray != null && idArray.length > 0) {
            for (int i = 0; i < idArray.length; i++) {
                Long id = Long.valueOf(idArray[i]);
                VcApply vcApply = applyDao.get(id);

                if (!enabledDeleteList.contains(vcApply.getApplyStatus())) {
                    sb.append("申领单号[" + vcApply.getApplyCode() + "]状态已发生变化,删除失败\n");
                    continue;
                }

                vcApply.setApplyStatus("0");
                //begin 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                vcApply.setDateUpdated(new Date());
                //end 审计字段（date_updated）未做更新 by zhxiao3 2014-6-18
                applyDao.update(vcApply);
                sb.append("申领单号[" + vcApply.getApplyCode() + "]删除成功\n");
            }
        }}catch(Exception e){
     	   throw new BusinessException(e.getMessage(),e);
        } 
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.apply.service.ApplyService#getApplyDetListByVcApplyId
     * (java.lang.Long)
     */
    public List<VcApplyDet> getApplyDetListByVcApplyId(Long vcApplyId) throws BusinessException {
        // VcApply vcApply = getVcApply(vcApplyId);
    	try{
        List<VcApplyDet> vcApplyDetList = applyDao.getApplyDetListByVcApplyId(vcApplyId);
        return vcApplyDetList;
    	}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.ApplyService#queryValidStorageListByPages
     * (java.util.Map, int, int)
     */
    public Page queryValidStorageListByPages(Map<String, Object> params, int page, int rows) throws BusinessException {
    	try{
        return applyDao.queryValidStorageList(params, page, rows);
    	}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }
    
    @Override
    public Page queryValidStorageListByPagesNew(Map<String, Object> params, int page, int rows) throws BusinessException {
    	try{
    		return applyDao.queryValidStorageListNew(params, page, rows);
    	}catch(DaoException e){
     	   throw new BusinessException(e.getMessage(),e);
        }
    }
    
    @Override
    public Page queryAvailableListByPages(Map<String, Object> params, int page,	int rows)  throws BusinessException{
    	try{
    		return applyDao.queryAvailableList(params, page, rows);
    	}catch(DaoException e){
    		throw new BusinessException(e.getMessage(), e);
    	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.ApplyService#getCloneApplyDetListByVcApplyId
     * (java.lang.Long)
     */
    @SuppressWarnings("rawtypes")
    public List<VcApplyDet> getCloneApplyDetListByVcApplyId(Long vcApplyId) throws BusinessException {
        List<VcApplyDet> vcApplyDetList = getApplyDetListByVcApplyId(vcApplyId);
        List<VcApplyDet> cloneList = new ArrayList<VcApplyDet>();
      try{
        for (Iterator it = vcApplyDetList.iterator(); it.hasNext();) {
            VcApplyDet vcApplyDet = (VcApplyDet) it.next();

            VcApplyDet cloneDet = new VcApplyDet();

            Beans.copy(cloneDet, vcApplyDet);
            cloneDet.setIdVcApplyDet(null);

            cloneList.add(cloneDet);
        }
      }catch(Exception e){
    	   throw new BusinessException(e.getMessage(),e);
      }
        return cloneList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.ApplyService#getValidStorageNum(java.lang
     * .String, java.lang.String)
     */
    public Long getValidStorageNum(String orgCode, String docVerCode) throws BusinessException {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.vc.store.service.ApplyService#getVcApprove(java.lang.Long,
     * java.lang.String)
     */
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
	public Page queryAbNormalList(Map<String, Object> params, int pageNo,
			int pageSize) throws BusinessException {
		try{
	        return applyDao.queryAbNormalList(params, pageNo, pageSize);	 
	        }catch(DaoException e){
	     	   throw new BusinessException(e.getMessage(),e);
	        }
	}
}
