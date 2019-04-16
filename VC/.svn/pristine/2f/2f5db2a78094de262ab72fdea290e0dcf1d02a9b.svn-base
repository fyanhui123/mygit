package com.tapi.tcs.vc.pub.service.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.pub.dao.PubRiskDao;
import com.tapi.tcs.vc.pub.service.PubRiskService;
import com.tapi.tcs.vc.schema.model.PubRisk;
import com.tapi.tcs.vc.schema.model.PubRiskClass;

/**
 * 险类险种Service实现
 * <p>
 * Date: 2013-03-20
 * </p>
 * <p>
 * Module: 险类险种维护
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
public class PubRiskServiceImpl implements PubRiskService {
    private PubRiskDao pubRiskDao;

    /**
     * @param pubRiskDao
     *            the pubRiskDao to set
     */
    public void setPubRiskDao(PubRiskDao pubRiskDao) {
        this.pubRiskDao = pubRiskDao;
    }

    /**
     * 根据条件查询险类list
     */
    public List<PubRisk> queryPubRiskList(PubRisk pubRiskVo) throws BusinessException {
        try {
            return pubRiskDao.queryPubRiskList(pubRiskVo);
        } catch (DaoException e) {
            throw new BusinessException("查询异常！", e);
        }
    }

    /**
     * 根据条件查询险种list
     */
    public List<PubRiskClass> queryPubRiskClassList(PubRiskClass pubRiskClassVo) throws BusinessException {
        try {
            return pubRiskDao.queryPubRiskClassList(pubRiskClassVo);
        } catch (DaoException e) {
            throw new BusinessException("查询异常！", e);
        }
    }

}
