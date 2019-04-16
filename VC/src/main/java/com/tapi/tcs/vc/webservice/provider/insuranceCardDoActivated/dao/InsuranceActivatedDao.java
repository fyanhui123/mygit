package com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.dao;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.vc.webservice.provider.insuranceCardDoActivated.bean.InsuranceCardInfoDTO;

public interface InsuranceActivatedDao extends GenericDao<VcInsuranceCard>{
	
   /****
    *   获取激活卡/可使用表信息
    * @return
    * @throws DaoException
    */
	public  Object[]  findInsuranceCardInfo(InsuranceCardInfoDTO card) throws DaoException;
	
	
	/**
     * 激活卡信息表锁记录操作
     * 
     * @param InsuranceCardInfoDTO 激活卡信息（单证类型、卡号）
     */   
    public void lockInsuranceCard(InsuranceCardInfoDTO card) throws DaoException;
	 
}
