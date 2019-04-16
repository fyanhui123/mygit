package com.tapi.tcs.vc.baseinfo.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocInsuType;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;
import com.tapi.tcs.vc.baseinfo.dao.VcConstantConfigDao;
import com.tapi.tcs.vc.baseinfo.dao.VcDocMngRuleDao;
import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.tf.common.helpers.QueryRule;
import com.tapi.tcs.tf.common.utils.bean.TFBeanUtils;

/**
 * 常量配置DAO实现
 * <p>
 * Date: 2013-04-28
 * </p>
 * <p>
 * Module: 常量配置
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
public class VcConstantConfigDaoImpl extends GenericDaoHibernate<VcConstantConfig> implements VcConstantConfigDao {

    /**
     * 根据ID获取常量配置
     * 
     * @author wanghuajian
     * 
     * @param contractId
     * @return
     */
    public VcConstantConfig getVcConstantConfig(Long idVcConstantConfig) {
        return (VcConstantConfig) super.get(VcConstantConfig.class, idVcConstantConfig);
    }

    /**
     * 根据给定的条件查询
     */
    public List queryConstantConfigList(VcConstantConfig queryVo) throws DaoException{
        QueryRule queryRule = null;
        try {
            queryRule = QueryRule.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("QueryRule拼接异常!", e);
        }
        if (queryVo != null) {
            if (queryVo.getLevelNo() > -1) {
                queryRule.addEqual("levelNo", queryVo.getLevelNo());
            }
            if (StringUtils.isNotEmpty(queryVo.getConstantCode())) {
                queryRule.addLike("constantCode", queryVo.getConstantCode() + "%");
            }

            if (StringUtils.isNotEmpty(queryVo.getConstantValue())) {
                queryRule.addLike("constantValue", queryVo.getConstantValue() + "%");
            }
            if (StringUtils.isNotEmpty(queryVo.getStatus())) {
                queryRule.addEqual("status", queryVo.getStatus());
            }
        }
        return this.find(VcConstantConfig.class, queryRule);
    }

    /**
     * 
     * 根据级别及常量代码获取其对应的常量值
     * 
     * @param constantCode
     *            常量代码
     * @param level
     *            级别等级
     */
    public VcConstantConfig getVcConstantConfig(String constantCode, int level) throws DaoException{
        VcConstantConfig resultVo = null;
        VcConstantConfig queryVo = new VcConstantConfig();
        queryVo.setLevelNo(level);
        queryVo.setConstantCode(constantCode);
        queryVo.setStatus("1");
        List list = this.queryConstantConfigList(queryVo);
        if (list != null && list.size() > 0) {
            resultVo = (VcConstantConfig) list.get(0);
        }
        return resultVo;
    }

}
