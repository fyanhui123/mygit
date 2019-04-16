package com.tapi.tcs.vc.insucard.dao.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.vc.insucard.dao.VcPayFeeAccountDao;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.schema.model.VcPayFeeAccount;

/**
 * 收付费账户DAO实现
 * <p>
 * Date: 2013-03-13
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

public class VcPayFeeAccountDaoImpl extends GenericDaoHibernate<VcPayFeeAccount> implements VcPayFeeAccountDao {

   
    /**
     * 根据ID获取账户信息
     * 
     * @author wanghuajian
     * 
     * @param id
     *            流水号
     * @return
     */
    public VcPayFeeAccount getVcPayFeeAccount(Long id){
        return (VcPayFeeAccount) super.get(VcPayFeeAccount.class, id);
    }
    
    /**
     * 根据业务号流水获取账户信息
     * 
     * @author wanghuajian
     * 
     * @param business_id
     *            业务号流水号
     * @return
     */
    public VcPayFeeAccount getVcPayFeeAccountByBusinessId(Long idBusiness)throws DaoException{
        QueryRule queryRule=  QueryRule.getInstance();
        queryRule.addEqual("idBusiness", idBusiness);
      List<VcPayFeeAccount> list= super.find(VcPayFeeAccount.class, queryRule);
      if(list==null || list.size()==0){
         return null;
      }
      if(list.size()>1){
          throw new DaoException("业务号对应多条条账户信息！");
      }
      return list.get(0);
    }
   
}
