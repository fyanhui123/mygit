/**
 * 
 */
package com.tapi.tcs.vc.store.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcDocVersionInfo;


/**
 * @author hanmiao.diao
 *
 */
public interface ProvideService {

	/**
	 * 提交发放操作
	 * @param provide 发放单信息
	 * @throws BusinessException 异常
	 */
	void saveProvide(VcApply provide) throws BusinessException;

	/**
	 * 领用确认操作,包含领用退回功能
	 * @param vcProvideConfrim 发放确认信息
	 * @throws BusinessException 异常
	 */
	void saveProvideConfirm(VcApply vcProvideConfrim) throws BusinessException;
	
	/**
     * 根据单证类型代码、对象类别、对象代码获取最大单证保存天数
     * <p>
     * 按以下三步查找，直至查询到为止
     * 1、查询单证管理控制规则表 VC_DOC_MNG_RULE
     * 2、查询单证类型管控信息表VC_DOC_VERSION_INFO_MNG
     * 3、查询常量表VC_CONSTANT_CONFIG
     * <p>
     * @param docVerCode
     * @param mngType  0-机构  1-单证使用人
     * @param mngObjectCode 机构、人员代码
     * @return
     * @throws DaoException
     *@author whj
     *@since Apr 9, 2014
     */
	int getMaxStoreTime(String docVerCode, String mngType, String mngObjectCode) throws BusinessException;
	
	
	/**
	 * 获取最大保留时间
	 * @param 
	 * @param   maxStoreType  类型
	 * @param   mngObjectCode 申请机构
	 * @return 最大存在时间
	 * @throws BusinessException 异常
	 */
	int getMaxExistTime(String maxStoreType,String mngObjectCode) throws BusinessException;
	
	/**
	 * 根据单证申领id，查询出该申领单证中的单证类型列表
	 * @param applyId
	 * @param docVerCode
	 * @return
	 * @throws BusinessException
	 * @author chy
	 * @date 2013-07-24
	 */
	public List<VcDocVersionInfo> getVcDocVersionInfoList(Long applyId, String docVerCode) throws BusinessException;
	
	/**
	 * 根据申领单ID和单证类型申请数量 
	 * @param applyId
	 * @param docVerCode
	 * @return
	 * @throws BusinessException
	 */
	public Integer getApplyNum(Long applyId, String docVerCode) throws BusinessException;
}
