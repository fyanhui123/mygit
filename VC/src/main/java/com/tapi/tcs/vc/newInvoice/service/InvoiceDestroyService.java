package com.tapi.tcs.vc.newInvoice.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;

public interface InvoiceDestroyService {
	public String saveDestroy(VcDestroy vcDestroy,
			List<VcDestroyDet> vcDestroyDets, String actionType,
			String destroyStatus,File file) throws BusinessException;
	//点击查看的时候查询库存内已经缴销的数据
	public Page queryAbNormalList(Map<String, Object> params, int pageNo, int pageSize) throws BusinessException;
	public String executeSubmitDestroy(String ids,String userCode,String org) throws BusinessException;
	/**
	 * 删除销毁申请
	 * 
	 * @param ids
	 * @return
	 */
	public String deleteDestroy(String ids) throws BusinessException;
	/**
	 * 销毁审批
	 * @param vcApprove
	 * @param id
	 * @param actionType
	 * @return
	 */
	public String executeApprove(VcApprove vcApprove,String id,String actionType) throws BusinessException;
}
