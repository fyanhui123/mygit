package com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docVerInfoInquiry.bean.DocVerInfoInquiryResponse;

public interface DocVerInfoInquiryQueryService {

	public  DocVerInfoInquiryResponse  handleQuery(DocVerInfoInquiryRequest request) throws BusinessException;
	 
}
 