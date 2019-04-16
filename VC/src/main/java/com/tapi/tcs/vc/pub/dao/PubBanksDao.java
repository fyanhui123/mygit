package com.tapi.tcs.vc.pub.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.PubBanks;

/**
 * 银行DAO
 * <p>
 * Date 2013-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public interface PubBanksDao extends GenericDao<PubBanks>{
    /**
     * 根据条件查询银行list
     */   
    public List<PubBanks> queryPubBanksList(PubBanks banksVo) throws DaoException ;
}
