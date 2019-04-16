/**
 * 
 */
package com.tapi.tcs.vc.store.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.base.api.exception.DaoException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApply;
import com.tapi.tcs.vc.schema.model.VcApplyDet;

/**
 * @author hanmiao.diao
 *
 */
public interface ApplyService {
	/**
	 * 
	 * @param vcApply 单证申领信息
	 * @return 返回值
	 * @throws BusinessException 异常
	 */
	String saveApply(VcApply vcApply) throws BusinessException;
	
	/**
	 * 分页查询申领信息
	 * @param params 查询条件
	 * @param pageNo 分页信息
	 * @param pageSize 分页信息
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	public Page queryApplyListByPages(Map<String, Object> params, int pageNo, int pageSize) throws BusinessException;

	/**
	 * 获取申领信息
	 * @param id 主键
	 * @return 申领信息
	 * @throws BusinessException 异常
	 */
	VcApply getVcApply(Long id) throws BusinessException;
	
	/**
	 * 删除申领信息
	 * @param id 申领信息id
	 * @return 处理结果
	 * @throws BusinessException 异常
	 */
	String deleteVcApply(Long id) throws BusinessException;

	/**
	 * 根据ID逻辑删除
	 * @param idArray id
	 * @param currentUser 当前用户
	 * @return 删除结果
	 * @throws BusinessException 异常
	 */
	String deleteByLogic(String[] idArray, String currentUser) throws BusinessException;

	/**
	 * 根据VC_APPLY.ID_VC_APPLY查询获取申领明细信息
	 * @param vcApplyId 表VC_APPLY中主键
	 * @return VC_APPLY_DET明细集合
	 * @throws BusinessException 异常
	 */
	List<VcApplyDet> getApplyDetListByVcApplyId(Long vcApplyId) throws BusinessException;
	
	/**
	 * 获取克隆的申领明细信息
	 * @param vcApplyId 表VC_APPLY中主键
	 * @return VC_APPLY_DET明细集合
	 * @throws BusinessException 异常
	 */
	List<VcApplyDet> getCloneApplyDetListByVcApplyId(Long vcApplyId) throws BusinessException;

	/**
	 * 分页查询当前机构有效库存
	 * @param params 查询参数
	 * @param page 分页条件
	 * @param rows 分页条件
	 * @return 查询结果
	 */
	Page queryValidStorageListByPages(Map<String, Object> params, int page,	int rows)  throws BusinessException;
	
	/**
	 * 分页查询当前机构有效库存
	 * @param params 查询参数
	 * @param page 分页条件
	 * @param rows 分页条件
	 * @return 查询结果
	 */
	Page queryValidStorageListByPagesNew(Map<String, Object> params, int page,	int rows)  throws BusinessException;
	
	/**
	 * 分页查询可使用单证
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 * @throws BusinessException
	 */
	Page queryAvailableListByPages(Map<String, Object> params, int page,	int rows)  throws BusinessException;

	/**
	 * 获取某机构某单证类型的有效库存数量
	 * @param orgCode 机构代码
	 * @param docVerCode 单证类型代码
	 * @return 有效库存数量
	 * @throws BusinessException 异常
	 */
	Long getValidStorageNum(String orgCode, String docVerCode)  throws BusinessException;

	/**
	 * 获取历史审批信息
	 * @param id 业务对象ID
	 * @param appType 业务类型
	 * @return 历史审批信息
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	List getVcApprove(Long id, String appType) throws BusinessException;
	
	/**
	 * @param orgCode 机构代码
	 * @param docVerCode 单证类型代码
	 * 本级机构的有效库存数量
	 * @return
	 * @throws Exception
	 * @author ljin
	 * @date 2013-06-26
	 */
	Long  localValidStorageNum(String orgCode, String docVerCode)  throws BusinessException;
	
	
	public Page queryAbNormalList(Map<String, Object> params, int pageNo, int pageSize) throws BusinessException;
	
}
