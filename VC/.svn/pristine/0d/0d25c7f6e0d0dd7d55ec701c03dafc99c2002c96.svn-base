package com.tapi.tcs.vc.baseinfo.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcDocVerMaxNum;

/**
 * 单证版本流水号DAO
 * <p>
 * Date 2013-04-24
 * </p>
 * <p>
 * Module：基本信息维护
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：实体类，不允许修改
 * </p>
 * 
 * @author chy
 * @version 1.0
 */
public interface VcDocVerMaxNumDao extends GenericDao<VcDocVerMaxNum> {
    
    /**
     * 根据单证类型查找最大流水号
     * @param versionCode 单证code
     * @param userCode 用户
     * @return Integer
     * @throws Exception 异常
     */
    public Integer getMaxNum(String versionCode, String userCode) throws DaoException;

   
    
    /**
     * 插入一条数据
     * @param vcDocVerMaxNum
     * @throws Exception
     */
    public void saveVcDocVerMaxNum(VcDocVerMaxNum vcDocVerMaxNum) throws Exception;

    /** 更新最大流水号表 */
    public void updateVcDocVerMaxNum(String versionCode, Integer maxNo, String userCode) throws DaoException;

    /** 根据单证类型查找记录 */
    public VcDocVerMaxNum findVcDocVerMaxNumByDocType(String docType) throws Exception;
}
