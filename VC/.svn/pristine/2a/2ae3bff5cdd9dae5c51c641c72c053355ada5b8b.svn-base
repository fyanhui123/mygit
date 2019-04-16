package com.tapi.tcs.vc.visausage.service;

import java.io.File;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;

public interface LostApplyService {

	/**
	 * 查询遗失申请信息
	 * 
	 * @param vcLost
	 * @return
	 */
	public Page queryLostApply(VcLost vcLost, int pageNo, int pageSize)throws BusinessException;

	/**
	 * 遗失申请新增
	 * @param vcLost
	 * @param vcLostDets
	 * @param actionType 保存/提交
	 * @param actionType2 新增/修改
	 * @return
	 * @throws BusinessException 
	 */
	public String saveLostApply(VcLost vcLost, List<VcLostDet> vcLostDets,
			String actionType,String actionType2,File file) throws BusinessException;
	
	/**
	 * 查看遗失
	 * @param id
	 * @return
	 * @throws BusinessException 
	 */
	public List viewLostApply(String id) throws BusinessException;
	
	/**
	 * 提交遗失申请
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String executeSubmitLostApply(String id,String userCode,String orgCode) throws BusinessException;
	
	/**
	 * 删除遗失申请
	 * @param id
	 * @return
	 */
	public String deleteLostApply(String id) throws BusinessException;
	
	/**
	 * 查询遗失审批
	 * @param vcLost
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page queryLostApprove(VcLost vcLost, int pageNo, int pageSize)throws BusinessException;
	
	/**
	 * 审批
	 * @param id
	 * @return
	 */
	public String executeApprove(VcApprove vcApprove, String id, String actionType) throws BusinessException;

}
