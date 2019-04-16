package com.tapi.tcs.vc.baseinfo.service.impl;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.baseinfo.dao.VcConstantConfigDao;
import com.tapi.tcs.vc.baseinfo.service.VcConstantConfigService;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;

/**
 * @author Administrator
 * 
 */
public class VcConstantConfigServiceImpl implements VcConstantConfigService {

    private VcConstantConfigDao vcConstantConfigDao;
    
    

    public void setVcConstantConfigDao(VcConstantConfigDao vcConstantConfigDao) {
        this.vcConstantConfigDao = vcConstantConfigDao;
    }

    /**
     * 根据ID获取常量配置
     * 
     * @author wanghuajian
     * 
     * @param contractId
     * @return
     */
    public VcConstantConfig getVcConstantConfig(Long idVcConstantConfig) throws BusinessException {
        return vcConstantConfigDao.getVcConstantConfig(idVcConstantConfig);
    }

    /**
     * 根据给定的条件查询
     */
    public List queryConstantConfigList(VcConstantConfig queryVo) throws BusinessException {
        try {
            return vcConstantConfigDao.queryConstantConfigList(queryVo);
        } catch (DaoException de) {
            throw new BusinessException("查询数据库异常：" + de.getMessage(), de);
        }

    }

    /**
     * 
     * 根据级别及常量代码获取其对应的常量配置
     * 
     * @param constantCode
     *            常量代码
     * @param level
     *            级别等级
     */
    public VcConstantConfig getVcConstantConfig(String constantCode, int level) throws BusinessException {
        try {
            return vcConstantConfigDao.getVcConstantConfig(constantCode, level);
        } catch (DaoException de) {
            throw new BusinessException("查询数据库异常：" + de.getMessage(), de);
        }
    }

}
