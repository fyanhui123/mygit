/**
 * 
 */
package com.tapi.tcs.vc.baseinfo.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;

/**
 * @author Administrator
 *
 */
public interface VcConstantConfigService {
    /**
     * 根据ID获取常量配置
     * 
     * @author wanghuajian
     * 
     * @param contractId
     * @return
     */
    public VcConstantConfig getVcConstantConfig(Long idVcConstantConfig)throws BusinessException;



    /**
     * 根据给定的条件查询
     */
    public List queryConstantConfigList(VcConstantConfig queryVo)throws BusinessException;
    
    /**
     * 
     * 根据级别及常量代码获取其对应的常量配置
     * @param constantCode 常量代码
     * @param level 级别等级
     */ 
    public VcConstantConfig getVcConstantConfig(String constantCode ,int level)throws BusinessException;
    
}
