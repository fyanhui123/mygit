package com.tapi.tcs.vc.newInvoice.service;

import java.io.File;
import java.util.List;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.tf.common.helpers.Page;
import com.tapi.tcs.vc.schema.model.VcAbnormalVerification;
import com.tapi.tcs.vc.schema.model.VcApprove;
import com.tapi.tcs.vc.schema.model.VcAvailableDoc;
import com.tapi.tcs.vc.schema.model.VcLost;
import com.tapi.tcs.vc.schema.model.VcLostDet;

public interface QueryInvoiceService {
	//根据id查询发票使用人是不是存在
	public boolean queryInvoice(String employid) throws BusinessException;
	//保存遗失发票申请
	public String saveLostApply(VcLost vcLost, List<VcLostDet> vcLostDets,
			String actionType,String actionType2,File file) throws BusinessException;
	//遗失申请提交
	public String executeSubmitLostApply(String id,String userCode,String orgCode) throws BusinessException;
	
	/**
	 * 审批
	 * @param id
	 * @return
	 */
	public String executeApprove(VcApprove vcApprove, String id, String actionType) throws BusinessException;

}
