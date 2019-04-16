package com.tapi.tcs.vc.insucard.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcPayFeeAccount;

/**
 * 收付费账户DAO接口
 * <p>
 * Date: 2013-08-06
 * </p>
 * <p>
 * Module: 激活卡
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Remark:
 * </p>
 * 
 * @author wanghuajian
 * @version 1.0
 */
public interface VcPayFeeAccountDao extends GenericDao<VcPayFeeAccount> {

    /**
     * 根据ID获取账户信息
     * 
     * @author wanghuajian
     * 
     * @param id
     *            流水号
     * @return
     */
    public VcPayFeeAccount getVcPayFeeAccount(Long id);
    
    /**
     * 根据业务号流水获取账户信息
     * 
     * @author wanghuajian
     * 
     * @param business_id
     *            业务号流水号
     * @return
     */
    public VcPayFeeAccount getVcPayFeeAccountByBusinessId(Long idBusiness)throws DaoException;

   
    

    
    
    
   
}
