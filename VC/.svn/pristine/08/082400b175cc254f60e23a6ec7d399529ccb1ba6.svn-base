/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.baseinfo.dao.InsuTypeDao;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;

/**
 * @author Administrator
 * 
 */
public class InsuTypeDaoImpl extends GenericDaoHibernate<VcDocInsuType> implements InsuTypeDao {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.dz.dao.DocInsuTypeDao#queryContracts(java.lang.
     * String, java.lang.String, int, int)
     */
    public Page queryInsuTypeList(String insuTypeCode, String insuTypeName, int pageNo, int pageSize) throws DaoException{
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
	        if (StringUtils.isNotEmpty(insuTypeCode)) {
	            queryRule.addEqual("insuTypeCode", insuTypeCode);
	        }
	
	        if (StringUtils.isNotEmpty(insuTypeName)) {
	            queryRule.addLike("insuTypeName", "%" + insuTypeName + "%");
	        }
	
	        queryRule.addEqual("status", "1");
        } catch (Exception e) {
            throw new DaoException("查询数据失败！", e);
        }

        return this.find(VcDocInsuType.class, queryRule, pageNo, pageSize);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.tapi.tcs.template.dz.dao.DocInsuTypeDao#getInsuTypeListByCode(java
     * .lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<VcDocInsuType> getInsuTypeListByEquals(String key, String value) throws DaoException {
    	try{
	        String hql = "from VcDocInsuType v where v." + key + "=?";
	
	        Object[] values = new Object[] { value };

	        return this.findByHql(hql, values);
    	} catch (Exception e) {
            throw new DaoException("查询数据失败！", e);
        }
    }

    /**
	 * 
	 */
    public void updateInsuTypeName(VcDocInsuType vo) throws DaoException {
    	try{
    		update(vo);
    	} catch (Exception e) {
            throw new DaoException("更新数据失败！", e);
        }
    }

}
