package com.tapi.tcs.vc.insucard.dao.impl;

import com.tapi.tcs.tf.base.api.dao.GenericDaoHibernate;
import com.tapi.tcs.vc.insucard.dao.VcInsuCardImportInfoDao;
import com.tapi.tcs.vc.schema.model.VcInsuCardImportInfo;

/**
 * 激活卡导入信息表DAO实现
 * <p>
 * Date: 2013-03-13
 * </p>
 * <p>
 * Module: 激活卡导入
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

public class VcInsuCardImportInfoDaoImpl extends GenericDaoHibernate<VcInsuCardImportInfo> implements
        VcInsuCardImportInfoDao {

    /**
     * 根据ID获取激活卡导入信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardImportInfo
     *            导入信息流水
     * @return VcInsuCardImportInfo
     */
    public VcInsuCardImportInfo getVcInsuCardImportInfo(Long idVcInsuCardImportInfo) {
        return (VcInsuCardImportInfo) super.get(VcInsuCardImportInfo.class, idVcInsuCardImportInfo);
    }

    /**
     * 保存激活卡导入信息及其激活卡信息
     * 
     * @param vcInsuranceCard
     * @return
     * 
     * @author wanghuajian
     */
    public void saveInsuCardImpInfoAndInsuKard(VcInsuCardImportInfo vcInsuCardImportInfo) {
        super.save(vcInsuCardImportInfo);
    }

}
