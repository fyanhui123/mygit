package com.tapi.tcs.vc.newInvoice.service;

import java.io.File;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcDestroy;
import com.tapi.tcs.vc.schema.model.VcDestroyDet;

public interface NewDestroyService {
	public String saveDestroy(VcDestroy vcDestroy,
			List<VcDestroyDet> vcDestroyDets, String actionType,
			String destroyStatus,File file) throws BusinessException;
	public List initDestroyView(Long id,String action,String type) throws BusinessException;
	
	public String executeSubmitDestroy(String ids,String userCode,String org) throws BusinessException;
	public Page queryDestroy(VcDestroy vcDestroy, int pageNo, int pageSize,
			String action) throws BusinessException;
	
	/**
	 * 销毁审批
	 * @param vcApprove
	 * @param id
	 * @param actionType
	 * @return
	 */
	public String executeApprove(VcApprove vcApprove,String id,String actionType) throws BusinessException;

}
