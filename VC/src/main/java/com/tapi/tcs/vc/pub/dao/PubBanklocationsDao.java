package com.tapi.tcs.vc.pub.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.PubBanklocations;

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
public interface PubBanklocationsDao extends GenericDao<PubBanklocations>{
    /**
     *根据条件分页查询银行支行
     */   
    public Page queryPubBanklocationsfeByPage(PubBanklocations queryVo, int pageNo,int pageSize) throws DaoException ;
}
