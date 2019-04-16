package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.Date;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.VcDocVerMaxNumDao;
import com.tapi.tcs.vc.schema.model.VcDocVerMaxNum;

/**
 * 单证版本流水号DAO实现类
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
 * @author chy
 * @version 1.0
 */
public class VcDocVerMaxNumDaoImpl extends GenericDaoHibernate<VcDocVerMaxNum> implements VcDocVerMaxNumDao {
    /**
     * 根据单证类型查找最大流水号
     * @return Integer
     */
    public Integer getMaxNum(String versionCode, String userCode) throws DaoException {
        Integer maxNum = 0;
        try{
	        QueryRule queryRule = QueryRule.getInstance();
	        queryRule.addEqual("versionCode", versionCode);
	        VcDocVerMaxNum vcDocVerMaxNum = (VcDocVerMaxNum)this.findUnique(VcDocVerMaxNum.class, queryRule);
	        //如果当前单证类型在库中无记录，则插入一条
	        if (vcDocVerMaxNum == null) {
	            vcDocVerMaxNum = new VcDocVerMaxNum();
	            vcDocVerMaxNum.setMaxNum(maxNum);
	            vcDocVerMaxNum.setVersionCode(versionCode);
	            
	            vcDocVerMaxNum.setCreatedBy(userCode);
	            vcDocVerMaxNum.setDateCreated(new Date());
	            vcDocVerMaxNum.setUpdatedBy(userCode);
	            vcDocVerMaxNum.setDateUpdated(new Date());
	            this.save(vcDocVerMaxNum);
	        }
	        else {
	            maxNum = vcDocVerMaxNum.getMaxNum();
	        }
        }catch(Exception e){
			throw new DaoException("查询数据库出错！", e);
		}
        return maxNum;
    }
    /**
     * 插入一条数据
     */
    public void saveVcDocVerMaxNum(VcDocVerMaxNum vcDocVerMaxNum) throws Exception {
        this.save(vcDocVerMaxNum);
    }
    /**
     * 更新最大流水号表
     */
    public void updateVcDocVerMaxNum(String versionCode, Integer maxNo, String userCode) throws DaoException {
        try{
	    	VcDocVerMaxNum vcDocVerMaxNum = this.findVcDocVerMaxNumByDocType(versionCode);
	        vcDocVerMaxNum.setMaxNum(maxNo);
	        vcDocVerMaxNum.setUpdatedBy(userCode);
	        vcDocVerMaxNum.setDateUpdated(new Date());
	        this.update(vcDocVerMaxNum);
        }catch(Exception e){
        	throw new DaoException("更新最大流水号出错！", e);
        }
    }
    
    /**
     * 根据单证类型查找记录
     */
    public VcDocVerMaxNum findVcDocVerMaxNumByDocType(String docType) throws DaoException {
    	VcDocVerMaxNum vcDocVerMaxNum = null;
    	try{
	        QueryRule queryRule = QueryRule.getInstance();
	        queryRule.addEqual("versionCode", docType);
	        vcDocVerMaxNum =  (VcDocVerMaxNum)this.findUnique(VcDocVerMaxNum.class, queryRule);
    	}catch(Exception e){
    		throw new DaoException("查询数据库失败！", e);
    	}
    	return vcDocVerMaxNum;
    }
}
