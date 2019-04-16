package com.tapi.tcs.vc.visausage.service;

import java.io.File;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;

public interface DestroyService {

	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize,
			String action) throws BusinessException;

	/**
	 * 保存/修改销毁
	 * 
	 * @param vcDestroy
	 * @param actionType
	 *            添加/修改
	 * @return 操作结果 成功/失败原因
	 * @throws BusinessException 
	 */
	public String saveDestroy(VcDestroy vcDestroy,
			List<VcDestroyDet> vcDestroyDets, String actionType,
			String destroyStatus,File file) throws BusinessException;

	/**
	 * 删除销毁申请
	 * 
	 * @param ids
	 * @return
	 */
	public String deleteDestroy(String ids) throws BusinessException;

	/**
	 * 提交销毁申请
	 * 
	 * @param ids
	 * @return
	 * @throws BusinessException 
	 */
	public String executeSubmitDestroy(String ids,String userCode,String org) throws BusinessException;

	/**
	 * 销毁审批
	 * @param vcApprove
	 * @param id
	 * @param actionType
	 * @return
	 */
	public String executeApprove(VcApprove vcApprove,String id,String actionType) throws BusinessException;

	public List initDestroyView(Long id,String action) throws BusinessException;
}
