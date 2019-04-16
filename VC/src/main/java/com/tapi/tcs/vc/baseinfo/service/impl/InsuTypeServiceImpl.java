/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.baseinfo.dao.InsuKindDao;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.baseinfo.service.InsuTypeService;
import com.tapi.tcs.vc.schema.model.VcDocInsuKind;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 * 
 */
/**
 * @author whj
 * 
 */
public class InsuTypeServiceImpl implements InsuTypeService {

    private InsuTypeDao insuTypeDao;

    private InsuKindDao insuKindDao;

    public InsuTypeDao getInsuTypeDao() {
        return insuTypeDao;
    }

    public void setInsuTypeDao(InsuTypeDao insuTypeDao) {
        this.insuTypeDao = insuTypeDao;
    }

    public InsuKindDao getInsuKindDao() {
        return insuKindDao;
    }

    public void setInsuKindDao(InsuKindDao insuKindDao) {
        this.insuKindDao = insuKindDao;
    }

    /**
     * 
     * @param insuTypeCode
     * @return
     * @throws BusinessException
     */
    @SuppressWarnings("rawtypes")
    private boolean isInsuTypeCodeExist(String insuTypeCode) throws BusinessException {
       try{
			List list = insuTypeDao.getInsuTypeListByEquals("insuTypeCode", insuTypeCode);
			
			if (list != null && list.size() > 0) {
			    return true;
			} else {
			    return false;
			}
       }catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /**
     * 
     * @param insuTypeName
     * @return
     * @throws BusinessException
     */
    @SuppressWarnings("rawtypes")
    private boolean isInsuTypeNameExist(String insuTypeName) throws BusinessException {
    	try{
	        List list = insuTypeDao.getInsuTypeListByEquals("insuTypeName", insuTypeName);
	
	        if (list != null && list.size() > 0) {
	            return true;
	        } else {
	            return false;
	        }
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.dz.service.DocInsuTypeService#addNewInsuType(com
     * .tapi.tcs.template.dz.vo.DocInsuTypeVO)
     */
    public void createInsuType(VcDocInsuType docInsuType) throws BusinessException {
    	try{
	        Assert.notNull(docInsuType);
	
	        if (isInsuTypeCodeExist(docInsuType.getInsuTypeCode())) {
	            throw new BusinessException("险类代码已存在,请重新设置 ");
	        }
	
	        if (isInsuTypeNameExist(docInsuType.getInsuTypeName())) {
	            throw new BusinessException("险类名称已存在,请重新设置 ");
	        }
	
	        insuTypeDao.save(docInsuType);
    	}catch(Exception e){
			throw new BusinessException("保存数据失败！", e);
		}
    }

    /**
     * 
     * @param insuTypeCode
     *            险类code
     * @param insuTypeName
     *            险类名称
     * @param pageNo
     *            页数
     * @param pageSize
     *            每页显示数目
     * @return Page
     * @throws BusinessException
     *             异常
     */
    public Page queryDocInsuTypeListByPages(String insuTypeCode, String insuTypeName, int pageNo, int pageSize)
            throws BusinessException {
    	try{
    		return insuTypeDao.queryInsuTypeList(insuTypeCode, insuTypeName, pageNo, pageSize);
    	}catch(DaoException e){
			throw new BusinessException(e.getMessage(), e);
		}
    }

    /**
     * 
     * @param id 主键id
     * @return VcDocInsuType
     * @throws BusinessException 异常
     */
    public VcDocInsuType getInsuType(Long id) throws BusinessException {
    	try{
    		return insuTypeDao.get(id);
    	}catch(Exception e){
			throw new BusinessException("获取数据失败！",e);
		}
    }

    /**
     * 
     * @param docInsuType 带更新vo
     * @throws BusinessException 异常
     */
    public void updateInsuType(VcDocInsuType docInsuType) throws BusinessException {
    	
	        Assert.notNull(docInsuType);
	
	        if (isInsuTypeNameExist(docInsuType.getInsuTypeName())) {
	            throw new BusinessException("险类名称已存在,请重新设置 ");
	        }
	      try{
	        VcDocInsuType vo = insuTypeDao.get(docInsuType.getIdVcDocInsuType());
	
	        vo.setDateUpdated(docInsuType.getDateUpdated());
	        vo.setUpdatedBy(docInsuType.getUpdatedBy());
	        vo.setInsuTypeName(docInsuType.getInsuTypeName());
	
	        insuTypeDao.update(vo);
    	}catch(Exception e){
			throw new BusinessException("更新数据失败！", e);
		}
    }

    /**
     * 
     * @param id 主键id
     * @throws BusinessException 异常
     */
    public void deleteByLogic(Long id) throws BusinessException {
    	try{
	        VcDocInsuType insuType = insuTypeDao.get(id);
	        insuType.setStatus("0");
	        insuTypeDao.update(insuType);
    	}catch(Exception e){
			throw new BusinessException("删除数据失败！", e);
		}
    }

    /**
     * 
     * @param idArray  待删除记录ids
     * @param currentUser 当前用户
     * @return String
     * @throws BusinessException 异常
     */
    public String deleteByLogic(String[] idArray, String currentUser) throws BusinessException {
        String result = "操作成功";
        if (idArray != null && idArray.length > 0) {
            Date modifyTime = new Date();
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < idArray.length; i++) {
                Long insuTypeId = Long.valueOf(idArray[i]);
                VcDocInsuType insuType = insuTypeDao.get(insuTypeId);
                // 先判断该险类下面是否有可用的险种,如果有则不可以删除,虽然是逻辑删除
                List < VcDocInsuKind > insuKindList = insuKindDao.queryInsuKindListByInsuTypeId(insuTypeId);
                if (insuKindList.size() > 0) {
                    sb = sb.append("险类[" + insuType.getInsuTypeName() + "]下关联有单证险种,不允许删除\n");
                    continue;
                }
                insuType.setStatus("0");
                insuType.setDateUpdated(modifyTime);
                insuType.setUpdatedBy(currentUser);

                try {
                    insuTypeDao.update(insuType);
                    sb = sb.append("险类[" + insuType.getInsuTypeName() + "]删除成功\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    sb = sb.append("险类[" + insuType.getInsuTypeName() + "]删除失败,数据库异常\n");
                }
            }

            result = sb.toString();
        }
        return result;
    }

}
