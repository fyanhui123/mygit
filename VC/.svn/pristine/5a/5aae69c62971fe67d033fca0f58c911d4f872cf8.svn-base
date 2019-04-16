package com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.component;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryRequest;
import com.tapi.tcs.vc.webservice.provider.insuCardInfoInquiry.bean.InsuCardInfoInquiryResponse;

@WebService(targetNamespace = "http://service.tapi.com/vc/insuCardInfoInquiry/intf")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface InsuCardInfoInquiryService {

	/**
	 * 激活卡信息查询接口
	 * @param request
	 * @return
	 */
	public InsuCardInfoInquiryResponse insuCardInfoInquiry(InsuCardInfoInquiryRequest request);
}
