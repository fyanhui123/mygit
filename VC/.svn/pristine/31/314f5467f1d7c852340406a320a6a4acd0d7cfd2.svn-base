/**
 * 
 */
package com.tapi.tcs.vc.store.service;

import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcRecycle;

/**
 * @author hanmiao.diao
 *
 */
public interface RecycleService {
	
	/**
	 * 保存或者提交回收单
	 * @param vcRecycle 页面收集的回收单信息
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String saveRecycle(VcRecycle vcRecycle, boolean isBatch) throws BusinessException;

	/**
	 * 分页查询
	 * @param params 查询条件
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @return 查询结果
	 * @throws BusinessException 异常
	 */
	Page queryRecycleListByPages(Map<String, Object> params, int page, int rows) throws BusinessException;

	/**
	 * 根据ID获取回收信息
	 * @param id 主键
	 * @return 返回对象
	 * @throws BusinessException 异常
	 */
	VcRecycle getVcRecycle(Long id) throws BusinessException;

	/**
	 * 回收确认或者退回调用方法
	 * @param pageVcRecycle 页面封装的回收信息
	 * @return 结果
	 * @throws BusinessException 异常
	 */
	String saveRecycleConfirm(VcRecycle pageVcRecycle)  throws BusinessException;

	/**
	 * 逻辑删除
	 * @param idArray 主键id
	 * @param currentUser user
	 * @return 操作结果
	 * @throws BusinessException 异常
	 */
	String deleteByLogic(String[] idArray, String currentUser)  throws BusinessException;
	
	/**
	 * 获取历史审批信息
	 * @param applyId id
	 * @param appType type
	 * @return 历史审批结果
	 * @throws BusinessException 异常
	 */
	List<VcApprove> getVcApprove(Long applyId, String appType) throws BusinessException;
}
