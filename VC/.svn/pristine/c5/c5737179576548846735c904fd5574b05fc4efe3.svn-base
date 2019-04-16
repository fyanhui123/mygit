/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.InsuKindDao;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.baseinfo.service.InsuKindService;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 * 
 */
public class InsuKindServiceImpl implements InsuKindService {

    private InsuKindDao insuKindDao;

    private InsuTypeDao insuTypeDao;

    public InsuKindDao getInsuKindDao() {
        return insuKindDao;
    }

    public void setInsuKindDao(InsuKindDao insuKindDao) {
        this.insuKindDao = insuKindDao;
    }

    public InsuTypeDao getInsuTypeDao() {
        return insuTypeDao;
    }

    public void setInsuTypeDao(InsuTypeDao insuTypeDao) {
        this.insuTypeDao = insuTypeDao;
    }

    /**
     * 
     * @param insuKindCode
     * @return
     * @throws BusinessException
     */
    private boolean isInsuKindCodeExist(String insuKindCode) throws BusinessException {
    	try{
	        List<VcDocInsuKind> insuKindList = insuKindDao.getInsuKindListByEquals("insuKindCode", insuKindCode);
	        if (insuKindList != null && insuKindList.size() > 0) {
	            return true;
	        }
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
        return false;
    }

    /**
     * 
     * @param insuKindName
     * @return
     * @throws BusinessException
     */
    private boolean isInsuKindNameExist(String insuKindName) throws BusinessException {
    	try{
	        List<VcDocInsuKind> insuKindList = insuKindDao.getInsuKindListByEquals("insuKindName", insuKindName);
	        if (insuKindList != null && insuKindList.size() > 0) {
	            return true;
	        }
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.vcdoc.service.InsuKindService#createInsuKind(com
     * .tapi.tcs.template.vcdoc.vo.InsuKind)
     */
    public void createInsuKind(VcDocInsuKind insuKind) throws BusinessException {

        if (insuKind.getIdVcDocInsuType() == null) {
            throw new BusinessException("险类不可以为空!");
        }
        if (StringUtils.isEmpty(insuKind.getInsuKindCode()) || StringUtils.isEmpty(insuKind.getInsuKindName())) {
            throw new BusinessException("险种代码和险种名称不可以为空");
        }

        // 判断险种代码是否存在
        if (isInsuKindCodeExist(insuKind.getInsuKindCode())) {
            throw new BusinessException("险种代码已存在!");
        }

        // 判断险种名称是否存在
        if (isInsuKindNameExist(insuKind.getInsuKindName())) {
            throw new BusinessException("险种名称已存在!");
        }

        try{
        	insuKindDao.save(insuKind);
        }catch(Exception e){
			throw new BusinessException("保存数据失败！", e);
		}

    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.tapi.tcs.template.vcdoc.service.InsuKindService#
     * queryDocInsuKindListByPages(java.lang.String, java.lang.String, int, int)
     */
    public Page queryDocInsuKindListByPages(Long insuTypeId, String insuKindCode, String insuKindName, int page,
            int rows) throws BusinessException {
    	try{
    		return insuKindDao.queryInsuKindList(insuTypeId, insuKindCode, insuKindName, page, rows);
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.vcdoc.service.InsuKindService#getInsuKind(java.
     * lang.Long)
     */
    public VcDocInsuKind getInsuKind(Long id) throws BusinessException {
    	try{
    		return insuKindDao.get(id);
    	}catch(Exception e){
			throw new BusinessException("获取数据失败！", e);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.vcdoc.service.InsuKindService#updateInsuKind(com
     * .tapi.tcs.template.vcdoc.vo.InsuKind)
     */
    public void updateInsuKind(VcDocInsuKind insuKind) throws BusinessException {

        // 判断险种名称是否存在
        if (isInsuKindNameExist(insuKind.getInsuKindName())) {
            throw new BusinessException("险种名称已存在!");
        }

        VcDocInsuKind upd = getInsuKind(insuKind.getIdVcDocInsuKind());
        upd.setInsuKindName(insuKind.getInsuKindName());
        upd.setDateUpdated(insuKind.getDateUpdated());
        upd.setUpdatedBy(insuKind.getUpdatedBy());
        try{
        	insuKindDao.update(upd);
        }catch(Exception e){
        	throw new BusinessException("更新数据失败！", e);
        }
    }

    public String deleteByLogic(String[] idArray, String currentUser) throws BusinessException {
        String result = "操作成功";
        if (idArray != null && idArray.length > 0) {
            StringBuffer sb = new StringBuffer("");
            Date modifyTime = new Date();
            for (int i = 0; i < idArray.length; i++) {
                VcDocInsuKind insuKind = getInsuKind(Long.valueOf(idArray[i]));
                insuKind.setStatus("0");

                insuKind.setDateUpdated(modifyTime);
                insuKind.setUpdatedBy(currentUser);

                try {
                    insuKindDao.update(insuKind);
                    sb = sb.append("险种[" + insuKind.getInsuKindName() + "]删除成功\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    sb = sb.append("险种[" + insuKind.getInsuKindName() + "]删除失败,数据库异常\n");
                }
            }
            result = sb.toString();
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.vcdoc.service.InsuKindService#getValidInsuTypeList
     * ()
     */
    public List<VcDocInsuType> getValidInsuTypeList() throws BusinessException {
    	try{
	        // 状态为1
	        String status = "1";
	        return insuTypeDao.getInsuTypeListByEquals("status", status);
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /*
     * public List<VcDocInsuKind> getValidInsuKindList() throws
     * BusinessException { // 状态为1 String status = "1"; return
     * insuKindDao.getInsuKindListByEquals("status", status); }
     */
    /**
     * 根据给定的条件查询
     * 
     * @wanghuajian
     */
    public List<VcDocInsuKind> getInsuKindList(Map<String, Object> map) throws BusinessException {
    	try{
    		return insuKindDao.getInsuKindList(map);
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /**
     * 根据险类代码查询险种
     * 
     * @param insuTypeCode
     * @return
     */
    public List<VcDocInsuKind> queryInsuKindListByInsuTypeCode(String insuTypeCode) throws BusinessException {
        try{
        	return insuKindDao.queryInsuKindListByInsuTypeCode(insuTypeCode);
        }catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }
}
