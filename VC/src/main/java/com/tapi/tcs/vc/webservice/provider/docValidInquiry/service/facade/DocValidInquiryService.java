package com.tapi.tcs.vc.webservice.provider.docValidInquiry.service.facade;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docValidInquiry.bean.DocValidInquiryResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docValidInquiry")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocValidInquiryService {
	
	/**
	 * 单证是否可用查询
	 * @param request
	 * @return
	 */
	public DocValidInquiryResponse docValidInquiry(DocValidInquiryRequest request);
}
