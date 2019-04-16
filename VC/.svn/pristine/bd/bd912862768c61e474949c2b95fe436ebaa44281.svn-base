package com.tapi.tcs.vc.webservice.provider.docNumInquiry.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.docNumInquiry.bean.DocNumInquiryResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/docNumInquiry/intf") 
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DocNumInquiryService {

	/**
	 * 可使用单证流水号查询接口
	 * @param request
	 * @return
	 */
	public DocNumInquiryResponse docNumInquiry(DocNumInquiryRequest request);
}
