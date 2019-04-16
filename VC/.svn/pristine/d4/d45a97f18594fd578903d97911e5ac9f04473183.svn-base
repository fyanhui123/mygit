package com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumStatusInquiryOld.bean.DocNumStatusInquiryOldResponse;

public interface DocNumStatusQueryService {

	/***
	 * 根据条件查询单证流水号状态
	 * 0：成功 
	 * 1：业务号已核销： 业务号已经用掉，返回的结果列表包含正常核销的流水号（不包括作废） 
	 * 2：流水号不可用：输入的流水号不可使用，返回的结果列表包含不可使用的流水号
	 * 5：输入参数不能为空
	 * 
	 * @param request
	 * @return
	 */
	public DocNumStatusInquiryOldResponse queryDocNumStatus(
			DocNumStatusInquiryOldRequestDTO request) throws BusinessException;

}
