package com.tapi.tcs.vc.insucard.dao;

import java.util.List;

import com.tapi.tcs.vc.schema.model.VcInsuCardImportInfo;
import com.tapi.tcs.vc.schema.model.VcInsuranceCard;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 * 激活卡导入信息表DAO接口
 * <p>
 * Date: 2013-06-18
 * </p>
 * <p>
 * Module: 激活卡导入信息表
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
public interface VcInsuCardImportInfoDao extends GenericDao<VcInsuCardImportInfo> {

   
    /**
     * 根据ID获取激活卡导入信息
     * 
     * @author wanghuajian
     * 
     * @param idVcInsuCardImportInfo
     *            导入信息流水
     * @return VcInsuCardImportInfo
     */
    public VcInsuCardImportInfo getVcInsuCardImportInfo(Long idVcInsuCardImportInfo);

   
    /**
     * 保存激活卡导入信息及其激活卡信息
     * 
     * @param vcInsuranceCard
     * @return
     * 
     * @author wanghuajian
     */
    public void saveInsuCardImpInfoAndInsuKard(VcInsuCardImportInfo vcInsuCardImportInfo);

}
