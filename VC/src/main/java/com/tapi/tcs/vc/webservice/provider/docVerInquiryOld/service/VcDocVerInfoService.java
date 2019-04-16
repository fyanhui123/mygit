package com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInquiryOld.bean.DocVerInquiryOldResponseDTO;

public interface VcDocVerInfoService {

	/**
	 * 根据请求体进行单证类型查询，返回响应体
	 * @param requestBody
	 * @return
	 * @throws BusinessException
	 */
	public DocVerInquiryOldResponseDTO docVerInquiry(DocVerInquiryOldRequestDTO requestBody) throws BusinessException;
}
