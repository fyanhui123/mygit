package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponse;

public interface VcInsuCardInquiryService {

	/**
	 * 查询激活卡信息并返回响应体
	 * @param requestBody
	 * @return
	 * @throws BusinessException
	 */
	public InsuCardInfoInquiryResponse queryInsuCardInfo(InsuCardInfoInquiryRequestDTO requestBody) throws BusinessException;
}
