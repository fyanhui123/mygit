package com.tapi.tcs.vc.visausage.service;

import com.tapi.tcs.tf.base.api.exception.BusinessException;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryRequestDTO;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponse;

public interface DocValidQueryService {
	
	public DocValidInquiryResponse docValidInquiry(DocValidInquiryRequestDTO body) throws BusinessException ;
}
