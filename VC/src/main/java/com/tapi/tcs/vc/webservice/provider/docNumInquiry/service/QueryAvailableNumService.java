package com.tapi.tcs.vc.webservice.provider.docNumInquiry.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryResponseDTO;

public interface QueryAvailableNumService {

	/**
	 * 查询可使用流水号
	 * @param requestBody
	 * @return
	 * @throws BusinessException
	 */
	public DocNumInquiryResponseDTO availableNumInquiry(DocNumInquiryRequestDTO requestBody) throws BusinessException;
}
