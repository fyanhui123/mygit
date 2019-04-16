package com.tapi.tcs.vc.common.service;

import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.schema.model.VcApprove;

/**
 * 公共审批Service层
 * <p>
 * Date 2013-03-20
 * </p>
 * <p>
 * Module：公共模块
 * </p>
 * <p>
 * Description：
 * </p>
 * <p>
 * Remark：
 * </p>
 * @author chy
 * @version 1.0
 */
public interface ApproveService {

	/**保存审批记录*/
	public void saveVcApprove(VcApprove vcApprove) throws BusinessException;
	
	/**根据申请编号查询审批记录*/
	public List<VcApprove> queryVcApprove(Long applyId, String appType) throws BusinessException;
	
	/**查询VcApprove对象*/
	public List<VcApprove> queryVcApproveObj(Long applyId, String appType) throws BusinessException ;
	
	/**删除审批记录*/
	public void deleteVcApprove(List<VcApprove> vcApproveList) throws BusinessException;
}
