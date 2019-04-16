/**
 * 
 */
package com.tapi.tcs.vc.visausage.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcCancel;

/**
 * @author hanmiao.diao
 *
 */
public interface CancelService {
	/**
	 * 存储库存作废申请
	 * @param cancel 作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String saveCancelS(VcCancel cancel) throws BusinessException;

	/**
	 * 分页查询作废申请
	 * @param params 查询条件
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	Page queryCancelListByPages(Map<String, Object> params, int page, int rows) throws BusinessException;

	/**
	 * 根据主键获取对象
	 * @param id 主键 
	 * @return 对象
	 * @throws BusinessException 异常
	 */
	VcCancel getVcCancel(Long id)  throws BusinessException;

	/**
	 * 逻辑删除
	 * @param idArray 主键数组
	 * @param currentUser 操作用户
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String deleteByLogic(String[] idArray, String currentUser) throws BusinessException;

	/**
	 * 存储已使用作废申请
	 * @param cancelUsage 作废申请
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String saveCancelUsage(VcCancel cancelUsage) throws BusinessException;

	/**
	 * 审批作废申请
	 * @param vcCancel 审批结果
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String saveCancelApprove(VcCancel vcCancel) throws BusinessException;

	/**
	 * 获取历史审批信息
	 * @param id 业务对象ID
	 * @param applyType 业务类型
	 * @return 历史审批集合
	 * @throws BusinessException 异常
	 */
	@SuppressWarnings("rawtypes")
	List getVcApprove(Long id, String applyType)  throws BusinessException;

}
