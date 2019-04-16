package com.tapi.tcs.vc.pub.dao;

import java.util.List;

import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.PubRisk;
import com.tapi.tcs.vc.schema.model.PubRiskClass;

/**
 * 险类DAO
 * <p>
 * Date 2014-03-20
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
 * @author whj
 * @version 1.0
 */
public interface PubRiskDao extends GenericDao<PubRisk>{
    /**
     * 根据条件查询险类list
     */   
    public List<PubRisk> queryPubRiskList(PubRisk pubRiskVo) throws DaoException ;
    
    /**
     * 根据条件查询险种list
     */   
    public List<PubRiskClass> queryPubRiskClassList(PubRiskClass pubRiskClassVo) throws DaoException ;
}
