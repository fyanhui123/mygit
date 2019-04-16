package com.tapi.tcs.vc.baseinfo.dao;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.vc.baseinfo.vo.VcDocMngRuleShowVo;
import com.tapi.tcs.vc.inquiry.vo.DocDetailInquiryVo;
import com.tapi.tcs.vc.schema.model.VcConstantConfig;
import com.tapi.tcs.vc.schema.model.VcDocMngRule;
import com.tapi.tcs.vc.schema.model.VcDocType;
import com.tapi.tcs.tf.base.api.dao.GenericDao;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;

/**
 *常量配置DAO接口
 * <p>
 * Date: 2013-04-28
 * </p>
 * <p>
 * Module:常量配置
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

public interface VcConstantConfigDao extends GenericDao<VcConstantConfig> {


	/**
	 * 根据ID获取常量配置
	 * 
	 * @author wanghuajian
	 * 
	 * @param contractId
	 * @return
	 */
	public VcConstantConfig getVcConstantConfig(Long idVcConstantConfig);



	/**
	 * 根据给定的条件查询
	 */
	public List queryConstantConfigList(VcConstantConfig queryVo)throws DaoException;
	
	/**
	 * 
	 * 根据级别及常量代码获取其对应的常量配置
	 * @param constantCode 常量代码
	 * @param level 级别等级
	 */	
	public VcConstantConfig getVcConstantConfig(String constantCode ,int level)throws DaoException;
	
	
}
